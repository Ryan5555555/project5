package com.example.project5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.project5.ml.Model;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class photo extends AppCompatActivity {
    ImageView photo,imageView,choose;

    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        choose = findViewById(R.id.choose);
        photo = findViewById(R.id.photo);
        imageView =findViewById(R.id.imageView);


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photo.setEnabled(false);
//                 Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
//                    Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
                // 在点击后设置按钮为灰色并延迟一段时间后恢复
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        photo.setEnabled(true);
                    }
                }, 100); // 1000毫秒 = 1秒，可以根据需要调整延迟的时间
            }
        });


        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(photo.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });
    }


    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 3);
    }

    public void classifyImage(Bitmap image){
        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer bytebuffer =ByteBuffer.allocate(4 * imageSize*imageSize*3);
            bytebuffer.order(ByteOrder.nativeOrder());

            int[] intvalues = new int[imageSize * imageSize];
            image.getPixels(intvalues,0,image.getWidth(),0,0,image.getWidth(),image.getHeight());
            int pixel = 0;
            for(int i=0;i<imageSize;i++){
                for(int j=0;j<imageSize;j++) {
                    int val =intvalues[pixel++];//RGB
                    bytebuffer.putFloat(((val >>16)&0xff)*(1.f /255.f));
                    bytebuffer.putFloat(((val >>8)&0xff)*(1.f /255.f));
                    bytebuffer.putFloat((val & 0xff)*(1.f /255.f));

                }
            }
            inputFeature0.loadBuffer(bytebuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[]confidences= outputFeature0.getFloatArray();
            int maxPos =0;
            float maxConfidence = 0;
            for(int i=0;i<confidences.length;i++){
                if(confidences[i]>maxConfidence){
                    maxConfidence =confidences[i];
                    maxPos =i;
                }
            }
            String[] classes ={"正常葉片","稻熱病","胡麻葉枯病","白葉枯病","褐條葉枯病"};

            // 获取最大概率的类别和对应的概率
            String recognizedClass = classes[maxPos];
            float confidence =  Math.round(confidences[maxPos] * 100);

            //String resultText = String.format("辨識結果: %s \n "+"信賴度: %.1f%%\n", recognizedClass, confidence);
            String resultText = recognizedClass;
            String result_confidence =  String.valueOf(confidence);
            Intent intent = new Intent();
            intent.setClass(photo.this, result.class);//目前Activity與目標Activity
            intent.putExtra("result", resultText);//辨識結果
            intent.putExtra("result_confidence",result_confidence);//辨識信心度
            startActivity(intent);

            model.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "辨識跳轉出錯，請填系工作團隊", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap image=(Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(),image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image,dimension,dimension);
            imageView.setImageBitmap(image);
            image = Bitmap.createScaledBitmap(image,imageSize,imageSize,false);
            classifyImage(image);
        }

        if (requestCode == 2){
            Log.e(this.getClass().getName(),"Result:" + data.toString());
            if (data != null){
                Uri uri = data.getData();
                imageView.setImageURI(uri);
                Log.e(this.getClass().getName(),"Uri:"+String.valueOf(uri));
            }
        }

        if (requestCode == 3 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);

                int dimension = Math.min(bitmap.getWidth(),bitmap.getHeight());
                bitmap = ThumbnailUtils.extractThumbnail(bitmap,dimension,dimension);
                imageView.setImageBitmap(bitmap);
                bitmap = Bitmap.createScaledBitmap(bitmap,imageSize,imageSize,false);
                classifyImage(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}