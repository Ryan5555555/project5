package com.example.project5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project5.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class photo extends AppCompatActivity {
    Button btHigh = findViewById(R.id.buttonHigh);
    TextView result = findViewById(R.id.result);
    ImageView imageHigh = findViewById(R.id.imageViewHigh);

    int imageSize = 224;

    /*
    public static final String TAG = MainActivity.class.getSimpleName()+"My";

    private String mPath = "";//設置高畫質的照片位址

    public static final int CAMERA_PERMISSION = 100;//檢測相機權限用
    public static final int REQUEST_HIGH_IMAGE = 101;//檢測高畫質相機回傳
    private ActivityResultLauncher<Uri> launcher;
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        btHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    //Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(photo.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        /*取得相機權限*/

        /*

        if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                // 处理相机拍照结果
                Intent data = result.getData();
                // 在此处处理相机拍照返回的数据
            }
        });
        /*按下高畫質照相之拍攝按鈕*/

        /*

        btHigh.setOnClickListener(v->{
            Intent highIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //檢查是否已取得權限
            if (highIntent.resolveActivity(getPackageManager()) == null) return;
            //取得相片檔案的URI位址及設定檔案名稱
            File imageFile = getImageFile();
            if (imageFile == null) return;
            //取得相片檔案的URI位址
            Uri imageUri = FileProvider.getUriForFile(
                    this,
                    "com.example.project1.CameraEx",//記得要跟AndroidManifest.xml中的authorities 一致
                    imageFile
            );
            highIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
            launcher.launch(imageUri);//開啟相機
        });

    }

    private ActivityResultLauncher<Uri> registerForActivityResult(ActivityResultContracts.StartActivityForResult startActivityForResult, ActivityResultCallback<ActivityResult> callback) {
        return null;
    }

    /**取得相片檔案的URI位址及設定檔案名稱*/

        /*
    private File getImageFile()  {
        String time = new SimpleDateFormat("yyMMdd").format(new Date());
        String fileName = time+"_";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            //給予檔案命名及檔案格式
            File imageFile = File.createTempFile(fileName,".jpg",dir);
            //給予全域變數中的照片檔案位置，方便後面取得
            mPath = imageFile.getAbsolutePath();
            return imageFile;
        } catch (IOException e) {
            return null;
        }
    }
    /**取得照片回傳*/

        /*

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*可在此檢視回傳為哪個相片，requestCode為上述自定義，resultCode為-1就是有拍照，0則是使用者沒拍照*/

        /*

        Log.d(TAG, "onActivityResult: requestCode: " + requestCode + ", resultCode " + resultCode);
        /*如果是高畫質的相片回傳*/

        /*

        if (requestCode == REQUEST_HIGH_IMAGE && resultCode == -1) {
            ImageView imageHigh = findViewById(R.id.imageViewHigh);
            new Thread(() -> {
                //在BitmapFactory中以檔案URI路徑取得相片檔案，並處理為AtomicReference<Bitmap>，方便後續旋轉圖片
                AtomicReference<Bitmap> getHighImage = new AtomicReference<>(BitmapFactory.decodeFile(mPath));
                Matrix matrix = new Matrix();
                matrix.setRotate(90f);//轉90度
                getHighImage.set(Bitmap.createBitmap(getHighImage.get()
                        , 0, 0
                        , getHighImage.get().getWidth()
                        , getHighImage.get().getHeight()
                        , matrix, true));
                runOnUiThread(() -> {
                    //以Glide設置圖片(因為旋轉圖片屬於耗時處理，故會LAG一下，且必須使用Thread執行緒)
                    Glide.with(this)
                            .load(getHighImage.get())
                            .centerCrop()
                            .into(imageHigh);
                });
            }).start();
        } else {
            Toast.makeText(this, "未作任何拍攝", Toast.LENGTH_SHORT).show();
        }
        */
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
            String[] classes ={"正常葉片","稻熱病","胡麻葉枯病","白葉枯病","褐調葉枯病"};

            String s = "";
            for (int i = 0;i<classes.length;i++) {
                if (classes[maxPos] == classes[i])
                {
                    s = String.format("%s: %.1f%%\n", classes[i], confidences[i] * 100);
                }
            }
            result.setText("辨識結果: \n"+String.format("%s(%.1f%%)\n", classes[maxPos], confidences[maxPos] * 100));

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap image=(Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(),image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image,dimension,dimension);
            imageHigh.setImageBitmap(image);
            image = Bitmap.createScaledBitmap(image,imageSize,imageSize,false);
            classifyImage(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}