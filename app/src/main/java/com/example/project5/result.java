package com.example.project5;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class result extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView result = findViewById(R.id.result);
        TextView confidence = findViewById(R.id.confidence);
        TextView describe = findViewById(R.id.describe);
        TextView describe2 = findViewById(R.id.describe2);
        TextView table1 = findViewById(R.id.table1);
        TextView talbe2 = findViewById(R.id.table2);
        TextView table3 = findViewById(R.id.table3);
        TextView table4 = findViewById(R.id.table4);
        TextView table5 = findViewById(R.id.table5);
        TextView table6 = findViewById(R.id.table6);


        ImageView img =findViewById(R.id.img);

        SharedPreferences sharedPreferences = getSharedPreferences("method", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Boolean check = true;
        Button method = findViewById(R.id.method);
        method.setEnabled(true);


        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(result.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        Intent intent = getIntent();
        String result_name = intent.getStringExtra("result");
        String result_confidence = intent.getStringExtra("result_confidence");

        // 取得全域變數看是否有機
        GlobalVariable globalVariable = (GlobalVariable) getApplication();
        globalVariable.loadSwitchState(this);
        String planet_method = globalVariable.planet_method;


        if ("慣行".equals(planet_method)) {
            // 根据传递的result
            if ("正常葉片".equals(result_name)) {
                result.setText("健康葉片");
                confidence.setText("");
                describe.setText("病害辨識為健康葉片");
                describe2.setText("");
                table1.setText("");
                talbe2.setText("");
                table3.setText("");
                table4.setText("");
                table5.setText("");
                table6.setText("");
            } else if ("稻熱病".equals(result_name)) {
                result.setText("稻熱病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.rice);
                describe.setText(Html.fromHtml(getString(R.string.rice_blast)));
                describe2.setText("本田部分：本病於第一期作較易發生。插秧後35～50天，田間如有葉稻熱病發生應即施藥一次，若經7天後繼續蔓延時再施藥一次。再於抽穗前7天左右及齊穗期各施藥一次，以預防穗稻熱病發生。");
                table1.setText("75% 三賽唑可溼性粉劑\n" +
                        "(tricyclazole)\n");
                talbe2.setText("FRAC 16.1;I1 系統性");
                table3.setText("0.33公斤");
                table4.setText("3,000");
                table5.setText("插秧後30-45天施藥，施藥二次。");
                table6.setText("1.防治葉稻熱病。\n" +
                        "2.採收前25天停止施藥。\n");
            } else if ("胡麻葉枯病".equals(result_name)) {
                result.setText("胡麻葉枯病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.brownspot);
                describe.setText(Html.fromHtml(getString(R.string.brown_spot)));
                describe2.setText("1.稻種消毒：\n" +
                        "\n" +
                        "2.注意改善耕種與田間衛生：\n" +
                        "\n" +
                        "(1)多施堆肥、綠肥等有機質肥料並行深耕。\n" +
                        "(2)勿用罹病老熟秧苗，以免傳播病原菌。\n" +
                        "(3)氮肥務須分次施用，抽穗前缺氮肥時，應補施穗肥(每十公畝二公斤左右)，更應充分施用鉀肥或矽素肥料。\n" +
                        "(4)常發病田應行客土並注意排水。\n" +
                        "(5)被害之稻藁，不可置放田間。\n");
                table1.setText("250 g/L (25% w/v) \n" +
                        "普克利乳劑\n" +
                        "(propiconazole)\n");
                talbe2.setText("FRAC 3;G1 系統性");
                table3.setText("0.8公升");
                table4.setText("1,300");
                table5.setText("分蘗盛期病斑出現時開始施藥，每隔14天再施藥一次，連續四次。");
                table6.setText("1.抽穗期避免用藥。\n2.採收前21天停止施藥。");
            } else if ("紋枯病".equals(result_name)) {
                result.setText("紋枯病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.sheathblight);
                describe.setText(Html.fromHtml(getString(R.string.sheath_blight)));
                describe2.setText("1.第一次施藥時，應噴施於稻株葉鞘部，第二次、三次施藥時，應噴施於全株。\n" +
                        "2.第一期作，如發生本病時，亦須施藥。\n");
                table1.setText("500 g/L (50% w/v) 待普克利乳劑\n" +
                        "(difenoconazole + propiconazole)\n");
                talbe2.setText("FRAC 3;G1");
                table3.setText("0.333公升");
                table4.setText("3,000");
                table5.setText("病害發生初期開始施藥，每隔14天施藥一次，連續二次。");
                table6.setText("1.採收前21天停止施藥。\n" +
                        "2.具強皮膚及眼刺激性。\n" +
                        "3.對水生物具毒性。\n");
            } else if ("白葉枯病".equals(result_name)) {
                result.setText("白葉枯病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.bacterialleafblight);
                describe.setText(Html.fromHtml(getString(R.string.bacterial_leaf_blight)));
                describe2.setText("1.秈稻較易感染本病，曾經發病地區及風大之地區，避免種植秈稻。\n" +
                        "2.病菌由傷口侵入，儘量採用直播，減少移植時感染病菌。\n" +
                        "3.避免偏用氮素肥料。\n" +
                        "4.晨露未乾前，避免進入稻田，以減少人為傳染病菌。\n");
                table1.setText("14% 嘉賜克枯爛可溼性粉劑\n" +
                        "(tecloftalam + kasugamycin)\n");
                talbe2.setText("FRAC 24;D3 + FRAC 34;unknown\n");
                table3.setText("0.8公斤");
                table4.setText("1,500");
                table5.setText("病害發生初期開始施藥，每隔10天施藥一次，連續三次。");
                table6.setText("1.採收前14天停止施藥。\n" +
                        "2.不可與強酸及強鹼性藥劑混合。\n" +
                        "3.本藥劑混合25%賓克隆可濕性粉劑、75%三賽唑可濕性粉劑等農藥可能發生藥害。\n");
            } else if ("褐條葉枯病".equals(result_name)) {
                result.setText("褐條葉枯病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.narrowbrownleafspot);
                describe.setText(Html.fromHtml(getString(R.string.narrow_brown_spot)));
                describe2.setText("1.第一次施藥時，應噴施於稻株葉鞘部，第二次、三次施藥時，應噴施於全株。\n" +
                        "2.第一期作，如發生本病時，亦須施藥。\n");
                table1.setText("500 g/L (50% w/v) 待普克利乳劑\n" +
                        "(difenoconazole + propiconazole)\n");
                talbe2.setText("FRAC 3;G1");
                table3.setText("0.333公升");
                table4.setText("3,000");
                table5.setText("病害發生初期開始施藥，每隔14天施藥一次，連續二次。");
                table6.setText("1.採收前21天停止施藥。\n" +
                        "2.具強皮膚及眼刺激性。\n" +
                        "3.對水生物具毒性。\n");
            } else {
                Toast.makeText(getApplicationContext(), "辨識跳轉出錯，請填系工作團隊", Toast.LENGTH_SHORT).show();
                check = false;
            }
        }else if ("有機".equals(planet_method)) {
            if ("正常葉片".equals(result_name)) {
                result.setText("健康葉片");
                confidence.setText("");
                describe.setText("病害辨識為健康葉片");
                describe2.setText("");
                table1.setText("");
                talbe2.setText("");
                table3.setText("");
                table4.setText("");
                table5.setText("");
                table6.setText("");
            } else if ("稻熱病".equals(result_name)) {
                result.setText("稻熱病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.rice);
                describe.setText(Html.fromHtml(getString(R.string.rice_blast)));
                describe2.setText("1.通風不良或晨露不易消退的地區，不適合進行水稻有機栽培。\n"
                        +"2.選用抗病品種，秈稻品種均較粳稻品種具有較強的抵抗性，例如台中秈10號。\n"
                        +"3.施用矽酸爐渣等含矽較高的資材，減少氮肥施用量來增強水稻抵抗稻熱病的能力。\n"
                        +"4.依據花蓮區農業改良場進行其他有機資材防治稻熱病的試驗，結果選出肉桂油、丁香油可減輕稻熱病之發生。\n"
                        +"5.枯草桿菌屬於細菌類，繁殖速度快，對於真菌引起的稻熱病具有良好的拮抗作用，可以有效抑制。\n");
                table1.setText("矽酸爐渣等含矽較高的資材\n"+"肉桂油、丁香油\n"+"枯草桿菌\n");
                talbe2.setText("無");
                table3.setText("無");
                table4.setText("無");
                table5.setText("無");
                table6.setText("1.通風不良或晨露不易消退的地區，不適合進行水稻有機栽培。\n" +
                        "2.減少氮肥施用量。\n");
            } else if ("胡麻葉枯病".equals(result_name)) {
                result.setText("胡麻葉枯病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.brownspot);
                describe.setText(Html.fromHtml(getString(R.string.brown_spot)));
                describe2.setText("1.水稻胡麻葉枯病較其他水稻病害略有不同的地方，在於田間缺乏氮、鉀、矽等元素容易發生，尤其是氮肥的管理，足量氮肥反而可抑制本病發生，與水稻其他病害如稻熱病、紋枯病、白葉枯病等相反，而鉀肥及矽酸肥則可增強抗病。因此，砂質壤土、泥炭土壤或土壤表層淺、土壤貧瘠田區，容易發生本病。\n" +
                        "2.本病可來自田間病稻草，因此水稻收割後病稻桿應儘量移除，或打碎浸水淹埋二星期，降低下一期作感染源。\n" );
                table1.setText("亞磷酸");
                talbe2.setText("無");
                table3.setText("0.8公升");
                table4.setText("1,000");
                table5.setText("2-3天防治一次，連續三次，效果即可維持3-4週。");
                table6.setText("足量氮肥反而可抑制本病發生。");
            } else if ("紋枯病".equals(result_name)) {
                result.setText("紋枯病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.sheathblight);
                describe.setText(Html.fromHtml(getString(R.string.sheath_blight)));
                describe2.setText("1.有機質肥料應合理化施用，避免施用過量氮素含量高之有機質肥料。\n"
                        +"2.水稻移植支數宜在6-7支即可，並將每叢分藥支數一期作控制在25支以下，二期作控制在20支以下，讓水稻生長期間通風性良好，均可有效抑制紋枯病之發生。\n"
                        +"3.施用炭化稻殼及矽酸爐渣對於降低紋枯病罹病率均亦有良好抑制效果。\n"
                        +"4.將亞磷酸與木黴茵混合使用，對抗病能力更具效果，另外，參考紋枯病之發生生態及傳播機制，減少田間菌核密度為防治工作之根本，建議於發病田區應於插秧前2週進行深水淹灌，使菌核浮出水面，藉由風势或水流使其集中至角落，於插秧前撈除水面菌核及稻株殘體；倘田間發現稻株感染時，應適時適度使用建議之非農藥防治資材，並以分蘗盛期前使用較具防治效果，使用方式以每4天-5天1次，連續數次，可健化稻株並促進植株免疫系統，並使有益微生物群聚於稻株上，達到抑病作用。\n");
                table1.setText("炭化稻殼、矽酸爐渣\n"+"亞磷酸與木黴茵混合使用\n");
                talbe2.setText("無");
                table3.setText("無");
                table4.setText("無");
                table5.setText("分蘗盛期前使用較具防治效果。\n"
                        +"4天-5天1次，連續數次\n");
                table6.setText("1.避免施用過量氮素含量高之有機質肥料。\n" +
                        "2.移植支數宜在6-7支，每叢分藥支數一期作控制在25支以下，二期作控制在20支以下。\n");
            } else if ("白葉枯病".equals(result_name)) {
                result.setText("白葉枯病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.bacterialleafblight);
                describe.setText(Html.fromHtml(getString(R.string.bacterial_leaf_blight)));
                describe2.setText("1.施用矽酸爐渣或炭化稻殼等含矽資材，加強植株硬度，可以有效抵抗白葉枯病的侵害。\n" +
                        "2.有機質肥料仍應合理化施用，避免過量施用含氮量高之有機資材以增強葉片強度，減少傷口之發生。\n" +
                        "3.晨露末乾前勿進入稻田作業，避免人行摩擦造成病原菌侵入的傷口。\n" +
                        "4.噴施枯草桿菌防治。\n");
                table1.setText("矽酸爐渣、炭化稻殼\n" +
                        "枯草桿菌\n");
                talbe2.setText("無");
                table3.setText("無");
                table4.setText("無");
                table5.setText("無");
                table6.setText("1.施用含矽資材提高水稻植體矽含量，可以有效抑制水稻白葉枯病病斑發生的程度。\n" +
                        "2.晨露末乾前勿進入稻田作業。\n" +
                        "3.避免過量施用含氮量高之有機資材。\n");
            } else if ("褐條葉枯病".equals(result_name)) {
                result.setText("褐條葉枯病");
                confidence.setText("信心度:" + result_confidence + "%");
                img.setImageResource(R.drawable.narrowbrownleafspot);
                describe.setText(Html.fromHtml(getString(R.string.narrow_brown_spot)));
                describe2.setText("1.有機質肥料應合理化施用，避免施用過量氮素含量高之有機質肥料。\n"
                        +"2.水稻移植支數宜在6-7支即可，並將每叢分藥支數一期作控制在25支以下，二期作控制在20支以下，讓水稻生長期間通風性良好，均可有效抑制褐條葉枯病之發生。\n"
                        +"3.施用炭化稻殼及矽酸爐渣對於降低褐條葉枯病罹病率均亦有良好抑制效果。\n"
                        +"4.將亞磷酸與木黴茵混合使用，對抗病能力更具效果，另外，參考褐條葉枯病之發生生態及傳播機制，減少田間菌核密度為防治工作之根本，建議於發病田區應於插秧前2週進行深水淹灌，使菌核浮出水面，藉由風势或水流使其集中至角落，於插秧前撈除水面菌核及稻株殘體；倘田間發現稻株感染時，應適時適度使用建議之非農藥防治資材，並以分蘗盛期前使用較具防治效果，使用方式以每4天-5天1次，連續數次，可健化稻株並促進植株免疫系統，並使有益微生物群聚於稻株上，達到抑病作用。\n");
                table1.setText("炭化稻殼、矽酸爐渣\n"+"亞磷酸與木黴茵混合使用\n");
                talbe2.setText("無");
                table3.setText("無");
                table4.setText("無");
                table5.setText("分蘗盛期前使用較具防治效果。\n"
                        +"4天-5天1次，連續數次\n");
                table6.setText("1.避免施用過量氮素含量高之有機質肥料。\n" +
                        "2.移植支數宜在6-7支，每叢分藥支數一期作控制在25支以下，二期作控制在20支以下。\n");
            } else {
                Toast.makeText(getApplicationContext(), "辨識跳轉出錯，請填系工作團隊", Toast.LENGTH_SHORT).show();
                check = false;
            }
        }
        Boolean finalCheck = check;
        method.setOnClickListener(v -> {
            if (finalCheck){
                int methodnum = sharedPreferences.getInt("method", 0);
                if(methodnum == 5){Toast.makeText(getApplicationContext(), "收藏已滿(收藏上限為五)!", Toast.LENGTH_SHORT).show();}
                else{
                    global.method+=1;

                    editor.putInt("method",global.method);
                    editor.putString(Integer.toString(global.method), result_name);
                    // 提交更改
                    editor.apply();

                    Intent intent_M = new Intent();
                    intent_M.setClass(result.this, method.class);
                    startActivity(intent_M);

                    method.setEnabled(false);
                }
            }
            else{Toast.makeText(getApplicationContext(), "辨識跳轉出錯，請聯繫工作團隊", Toast.LENGTH_SHORT).show();}
        });
    }
}
