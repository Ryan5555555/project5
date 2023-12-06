package com.example.project5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class method extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method);
        String title="";

        SharedPreferences sharedPreferences = getSharedPreferences("method", Context.MODE_PRIVATE);
        int m = sharedPreferences.getInt("method", 0);
        for(int i = m; i>0; i--)
        {
            title = sharedPreferences.getString(Integer.toString(i), "");
            createmethod(i, title);
        }


        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(method.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });
    }

    public void createmethod(int m, String title) {
        SharedPreferences sharedPreferences = getSharedPreferences("method", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        RelativeLayout relativeLayout = findViewById(R.id.relativelayout);
        TextView nomethod = (TextView) findViewById(R.id.nomethod);

        if(m == 1)
        {
        CardView cardView1 = new CardView(this);
        RelativeLayout.LayoutParams cardParams1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                200 // 設置高度為 100dp
        );
        cardParams1.setMargins(20, 30, 20, 30); // 設置 margin
        cardView1.setLayoutParams(cardParams1);
        cardView1.setRadius(20f);
        cardView1.setId(R.id.C1);

        // 在第一個 CardView 中動態創建 RelativeLayout
        RelativeLayout relativeLayout1 = new RelativeLayout(this);
        RelativeLayout.LayoutParams relativeParams1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        relativeLayout1.setPadding(20, 20, 20, 20);
        relativeLayout1.setLayoutParams(relativeParams1);

        // 在 RelativeLayout1 中添加 ImageView
        ImageView imageView1 = new ImageView(this);
        RelativeLayout.LayoutParams imageParams1 = new RelativeLayout.LayoutParams(
                200, // 寬度為 200dp
                200  // 高度為 200dp
        );
        imageView1.setId(View.generateViewId()); // 設置唯一的 ID
        relativeLayout1.addView(imageView1, imageParams1);

        // 在 RelativeLayout1 中添加 TextView
        TextView textView1 = new TextView(this);
        RelativeLayout.LayoutParams textParams1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textParams1.addRule(RelativeLayout.RIGHT_OF, imageView1.getId());
        textParams1.addRule(RelativeLayout.CENTER_VERTICAL);
        textParams1.leftMargin = 30; // 添加額外的左邊距
        textView1.setText(title);
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        textView1.setTypeface(null, Typeface.BOLD);
        relativeLayout1.addView(textView1, textParams1);

        // 在 RelativeLayout1 中添加 ImageButton
        ImageButton imageButton1 = new ImageButton(this);
        RelativeLayout.LayoutParams buttonParams1 = new RelativeLayout.LayoutParams(
                75, // 寬度為75dp
                75 // 高度為75dp
        );
        buttonParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        buttonParams1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        imageButton1.setImageResource(R.drawable.cancel);
        relativeLayout1.addView(imageButton1, buttonParams1);

        // 將 RelativeLayout1 添加到 CardView1
        cardView1.addView(relativeLayout1);
        relativeLayout.addView(cardView1);

        if ("正常葉片".equals(title)) {
            imageView1.setImageResource(R.drawable.cancel);
        } else if ("稻熱病".equals(title)) {
            imageView1.setImageResource(R.drawable.rice);
        } else if ("胡麻葉枯病".equals(title)) {
            imageView1.setImageResource(R.drawable.brownspot);
        } else if ("紋枯病".equals(title)) {
            imageView1.setImageResource(R.drawable.sheathblight);
        } else if ("白葉枯病".equals(title)) {
            imageView1.setImageResource(R.drawable.bacterialleafblight);
        } else if ("褐條葉枯病".equals(title)) {
            imageView1.setImageResource(R.drawable.narrowbrownleafspot);
        }


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(method.this,method_enter.class);
            intent.putExtra("Name",title);
            startActivity(intent);
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = (View) v.getParent();
                View grandparent = (View) parent.getParent();
                relativeLayout.removeView(grandparent);

                for(int i =global.method;i>0;i-- )
                {
                    editor.putString(Integer.toString(i - 1), sharedPreferences.getString(Integer.toString(i), ""));
                }

                global.method -= 1;

                editor.putInt("method", global.method);
                // 提交更改
                editor.apply();

                if (global.method == 0)
                {
                    nomethod.setVisibility(View.VISIBLE);
                }
            }
        });
        nomethod.setVisibility(View.INVISIBLE);
    }
        else if(m == 2){
        CardView cardView2 = new CardView(this);
        RelativeLayout.LayoutParams cardParams2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                200 // 設置高度為 200dp
        );
        cardParams2.setMargins(20, 30, 20, 30); // 設置 margin
        cardParams2.addRule(RelativeLayout.BELOW, R.id.C1);
        cardView2.setLayoutParams(cardParams2);
        cardView2.setRadius(20f);
        cardView2.setId(R.id.C2);

        // 動態創建 RelativeLayout
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        relativeLayout2.setPadding(20, 20, 20, 20);
        relativeLayout2.setLayoutParams(relativeParams2);

        // 在 RelativeLayout2 中添加 ImageView
        ImageView imageView2 = new ImageView(this);
        RelativeLayout.LayoutParams imageParams2 = new RelativeLayout.LayoutParams(
                200, // 寬度為 200dp
                200  // 高度為 200dp
        );
        imageView2.setId(View.generateViewId()); // 設置唯一的 ID
        relativeLayout2.addView(imageView2, imageParams2);

        // 在 RelativeLayout2 中添加 TextView
        TextView textView2 = new TextView(this);
        RelativeLayout.LayoutParams textParams2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textParams2.addRule(RelativeLayout.RIGHT_OF, imageView2.getId());
        textParams2.addRule(RelativeLayout.CENTER_VERTICAL);
        textParams2.leftMargin = 30; // 添加額外的左邊距
        textView2.setText(title);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        textView2.setTypeface(null, Typeface.BOLD);
        relativeLayout2.addView(textView2, textParams2);

        // 在 RelativeLayout2 中添加 ImageButton
        ImageButton imageButton2 = new ImageButton(this);
        RelativeLayout.LayoutParams buttonParams2 = new RelativeLayout.LayoutParams(
                75, // 寬度為 75dp
                75  // 高度為 75dp
        );
        buttonParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        buttonParams2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        imageButton2.setImageResource(R.drawable.cancel);
        relativeLayout2.addView(imageButton2, buttonParams2);

        // 將 RelativeLayout2 添加到 CardView2
        cardView2.addView(relativeLayout2);
        relativeLayout.addView(cardView2);

        if ("正常葉片".equals(title))
        {imageView2.setImageResource(R.drawable.cancel);}
        else if ("稻熱病".equals(title)) {imageView2.setImageResource(R.drawable.rice);}
        else if ("胡麻葉枯病".equals(title)){imageView2.setImageResource(R.drawable.brownspot);}
        else if ("紋枯病".equals(title)) {imageView2.setImageResource(R.drawable.sheathblight);}
        else if ("白葉枯病".equals(title)){imageView2.setImageResource(R.drawable.bacterialleafblight);}
        else if ("褐條葉枯病".equals(title)){imageView2.setImageResource(R.drawable.narrowbrownleafspot);}

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(method.this,method_enter.class);
            intent.putExtra("Name",title);
            startActivity(intent);
        }
            });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = (View) v.getParent();
                View grandparent = (View) parent.getParent();
                relativeLayout.removeView(grandparent);

                for(int i =global.method;i>1;i-- )
                {
                    editor.putString(Integer.toString(i - 1), sharedPreferences.getString(Integer.toString(i), ""));
                }

                global.method-=1;

                editor.putInt("method",global.method);
                // 提交更改
                editor.apply();

                if (global.method == 0)
                {
                    nomethod.setVisibility(View.VISIBLE);
                }
            }
        });

        nomethod.setVisibility(View.INVISIBLE);
    }
        else if(m == 3){
            CardView cardView3 = new CardView(this);
            RelativeLayout.LayoutParams cardParams3 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    200 // 設置高度為 200dp
            );
            cardParams3.setMargins(20, 30, 20, 30); // 設置 margin
            cardParams3.addRule(RelativeLayout.BELOW, R.id.C2);
            cardView3.setLayoutParams(cardParams3);
            cardView3.setRadius(20f);
            cardView3.setId(R.id.C3);

            // 動態創建 RelativeLayout
            RelativeLayout relativeLayout3 = new RelativeLayout(this);
            RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            relativeLayout3.setPadding(20, 20, 20, 20);
            relativeLayout3.setLayoutParams(relativeParams3);

            // 在 RelativeLayout3 中添加 ImageView
            ImageView imageView3 = new ImageView(this);
            RelativeLayout.LayoutParams imageParams3 = new RelativeLayout.LayoutParams(
                    200, // 寬度為 200dp
                    200  // 高度為 200dp
            );
            imageView3.setId(View.generateViewId()); // 設置唯一的 ID
            relativeLayout3.addView(imageView3, imageParams3);

            // 在 RelativeLayout3 中添加 TextView
            TextView textView3 = new TextView(this);
            RelativeLayout.LayoutParams textParams3 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            textParams3.addRule(RelativeLayout.RIGHT_OF, imageView3.getId());
            textParams3.addRule(RelativeLayout.CENTER_VERTICAL);
            textParams3.leftMargin = 30; // 添加額外的左邊距
            textView3.setText(title);
            textView3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            textView3.setTypeface(null, Typeface.BOLD);
            relativeLayout3.addView(textView3, textParams3);

            // 在 RelativeLayout3 中添加 ImageButton
            ImageButton imageButton3 = new ImageButton(this);
            RelativeLayout.LayoutParams buttonParams3 = new RelativeLayout.LayoutParams(
                    75, // 寬度為 75dp
                    75  // 高度為 75dp
            );
            buttonParams3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            buttonParams3.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            imageButton3.setImageResource(R.drawable.cancel);
            relativeLayout3.addView(imageButton3, buttonParams3);

            // 將 RelativeLayout3 添加到 CardView3
            cardView3.addView(relativeLayout3);
            relativeLayout.addView(cardView3);

            if ("正常葉片".equals(title))
            {imageView3.setImageResource(R.drawable.cancel);}
            else if ("稻熱病".equals(title)) {imageView3.setImageResource(R.drawable.rice);}
            else if ("胡麻葉枯病".equals(title)){imageView3.setImageResource(R.drawable.brownspot);}
            else if ("紋枯病".equals(title)) {imageView3.setImageResource(R.drawable.sheathblight);}
            else if ("白葉枯病".equals(title)){imageView3.setImageResource(R.drawable.bacterialleafblight);}
            else if ("褐條葉枯病".equals(title)){imageView3.setImageResource(R.drawable.narrowbrownleafspot);}

            cardView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(method.this,method_enter.class);
                    intent.putExtra("Name",title);
                    startActivity(intent);
                }
            });

            imageButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View parent = (View) v.getParent();
                    View grandparent = (View) parent.getParent();
                    relativeLayout.removeView(grandparent);

                    for(int i =global.method;i>2;i-- )
                    {
                        editor.putString(Integer.toString(i - 1), sharedPreferences.getString(Integer.toString(i), ""));
                    }

                    global.method-=1;

                    editor.putInt("method",global.method);
                    // 提交更改
                    editor.apply();

                    if (global.method == 0)
                    {
                        nomethod.setVisibility(View.VISIBLE);
                    }
                }
            });

            nomethod.setVisibility(View.INVISIBLE);
        }
        else if(m == 4){
            CardView cardView4 = new CardView(this);
            RelativeLayout.LayoutParams cardParams4 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    200 // 設置高度為 200dp
            );
            cardParams4.setMargins(20, 30, 20, 30); // 設置 margin
            cardParams4.addRule(RelativeLayout.BELOW, R.id.C3);
            cardView4.setLayoutParams(cardParams4);
            cardView4.setRadius(20f);
            cardView4.setId(R.id.C4);

            // 動態創建 RelativeLayout
            RelativeLayout relativeLayout4 = new RelativeLayout(this);
            RelativeLayout.LayoutParams relativeParams4 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            relativeLayout4.setPadding(20, 20, 20, 20);
            relativeLayout4.setLayoutParams(relativeParams4);

            // 在 RelativeLayout4 中添加 ImageView
            ImageView imageView4 = new ImageView(this);
            RelativeLayout.LayoutParams imageParams4 = new RelativeLayout.LayoutParams(
                    200, // 寬度為 200dp
                    200  // 高度為 200dp
            );
            imageView4.setId(View.generateViewId()); // 設置唯一的 ID
            relativeLayout4.addView(imageView4, imageParams4);

            // 在 RelativeLayout4 中添加 TextView
            TextView textView4 = new TextView(this);
            RelativeLayout.LayoutParams textParams4 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            textParams4.addRule(RelativeLayout.RIGHT_OF, imageView4.getId());
            textParams4.addRule(RelativeLayout.CENTER_VERTICAL);
            textParams4.leftMargin = 30; // 添加額外的左邊距
            textView4.setText(title);
            textView4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            textView4.setTypeface(null, Typeface.BOLD);
            relativeLayout4.addView(textView4, textParams4);

            // 在 RelativeLayout4 中添加 ImageButton
            ImageButton imageButton4 = new ImageButton(this);
            RelativeLayout.LayoutParams buttonParams4 = new RelativeLayout.LayoutParams(
                    75, // 寬度為 75dp
                    75  // 高度為 75dp
            );
            buttonParams4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            buttonParams4.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            imageButton4.setImageResource(R.drawable.cancel);
            relativeLayout4.addView(imageButton4, buttonParams4);

            // 將 RelativeLayout4 添加到 CardView4
            cardView4.addView(relativeLayout4);
            relativeLayout.addView(cardView4);

            if ("正常葉片".equals(title))
            {imageView4.setImageResource(R.drawable.cancel);}
            else if ("稻熱病".equals(title)) {imageView4.setImageResource(R.drawable.rice);}
            else if ("胡麻葉枯病".equals(title)){imageView4.setImageResource(R.drawable.brownspot);}
            else if ("紋枯病".equals(title)) {imageView4.setImageResource(R.drawable.sheathblight);}
            else if ("白葉枯病".equals(title)){imageView4.setImageResource(R.drawable.bacterialleafblight);}
            else if ("褐條葉枯病".equals(title)){imageView4.setImageResource(R.drawable.narrowbrownleafspot);}

            cardView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(method.this,method_enter.class);
                    intent.putExtra("Name",title);
                    startActivity(intent);
                }
            });

            imageButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View parent = (View) v.getParent();
                    View grandparent = (View) parent.getParent();
                    relativeLayout.removeView(grandparent);

                    for(int i =global.method;i>3;i-- )
                    {
                        editor.putString(Integer.toString(i - 1), sharedPreferences.getString(Integer.toString(i), ""));
                    }

                    global.method-=1;

                    editor.putInt("method",global.method);
                    // 提交更改
                    editor.apply();

                    if (global.method == 0)
                    {
                        nomethod.setVisibility(View.VISIBLE);
                    }
                }
            });

            nomethod.setVisibility(View.INVISIBLE);
        }
        else if(m == 5){
            CardView cardView5 = new CardView(this);
            RelativeLayout.LayoutParams cardParams5 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    200 // 設置高度為 200dp
            );
            cardParams5.setMargins(20, 30, 20, 30); // 設置 margin
            cardParams5.addRule(RelativeLayout.BELOW, R.id.C4);
            cardView5.setLayoutParams(cardParams5);
            cardView5.setRadius(20f);
            cardView5.setId(R.id.C5);

            // 動態創建 RelativeLayout
            RelativeLayout relativeLayout5 = new RelativeLayout(this);
            RelativeLayout.LayoutParams relativeParams5 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            relativeLayout5.setPadding(20, 20, 20, 20);
            relativeLayout5.setLayoutParams(relativeParams5);

            // 在 RelativeLayout5 中添加 ImageView
            ImageView imageView5 = new ImageView(this);
            RelativeLayout.LayoutParams imageParams5 = new RelativeLayout.LayoutParams(
                    200, // 寬度為 200dp
                    200  // 高度為 200dp
            );
            imageView5.setId(View.generateViewId()); // 設置唯一的 ID
            relativeLayout5.addView(imageView5, imageParams5);

            // 在 RelativeLayout5 中添加 TextView
            TextView textView5 = new TextView(this);
            RelativeLayout.LayoutParams textParams5 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            textParams5.addRule(RelativeLayout.RIGHT_OF, imageView5.getId());
            textParams5.addRule(RelativeLayout.CENTER_VERTICAL);
            textParams5.leftMargin = 30; // 添加額外的左邊距
            textView5.setText(title);
            textView5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            textView5.setTypeface(null, Typeface.BOLD);
            relativeLayout5.addView(textView5, textParams5);

            // 在 RelativeLayout5 中添加 ImageButton
            ImageButton imageButton5 = new ImageButton(this);
            RelativeLayout.LayoutParams buttonParams5 = new RelativeLayout.LayoutParams(
                    75, // 寬度為 75dp
                    75  // 高度為 75dp
            );
            buttonParams5.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            buttonParams5.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            imageButton5.setImageResource(R.drawable.cancel);
            relativeLayout5.addView(imageButton5, buttonParams5);

            // 將 RelativeLayout5 添加到 CardView5
            cardView5.addView(relativeLayout5);
            relativeLayout.addView(cardView5);

            if ("正常葉片".equals(title))
            {imageView5.setImageResource(R.drawable.cancel);}
            else if ("稻熱病".equals(title)) {imageView5.setImageResource(R.drawable.rice);}
            else if ("胡麻葉枯病".equals(title)){imageView5.setImageResource(R.drawable.brownspot);}
            else if ("紋枯病".equals(title)) {imageView5.setImageResource(R.drawable.sheathblight);}
            else if ("白葉枯病".equals(title)){imageView5.setImageResource(R.drawable.bacterialleafblight);}
            else if ("褐條葉枯病".equals(title)){imageView5.setImageResource(R.drawable.narrowbrownleafspot);}

            cardView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(method.this,method_enter.class);
                    intent.putExtra("Name",title);
                    startActivity(intent);
                }
            });

            imageButton5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View parent = (View) v.getParent();
                    View grandparent = (View) parent.getParent();
                    relativeLayout.removeView(grandparent);

                    for(int i =global.method;i>4;i-- )
                    {
                        editor.putString(Integer.toString(i - 1), sharedPreferences.getString(Integer.toString(i), ""));
                    }

                    global.method-=1;

                    editor.putInt("method",global.method);
                    // 提交更改
                    editor.apply();

                    if (global.method == 0)
                    {
                        nomethod.setVisibility(View.VISIBLE);
                    }
                }
            });

            nomethod.setVisibility(View.INVISIBLE);
        }



    }

    public void onBackPressed() {}


}