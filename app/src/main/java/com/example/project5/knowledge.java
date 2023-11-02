package com.example.project5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class knowledge extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge);

        RelativeLayout R1 = findViewById(R.id.R1);
        ImageView R1img = findViewById(R.id.R1image);
        TextView R1text = findViewById(R.id.t1);

        RelativeLayout R2 = findViewById(R.id.R2);
        ImageView R2img = findViewById(R.id.R2image);
        TextView R2text = findViewById(R.id.t2);

        RelativeLayout R3 = findViewById(R.id.R3);
        ImageView R3img = findViewById(R.id.R3image);
        TextView R3text = findViewById(R.id.t3);

        RelativeLayout R4 = findViewById(R.id.R4);
        ImageView R4img = findViewById(R.id.R4image);
        TextView R4text = findViewById(R.id.t4);

        RelativeLayout R5 = findViewById(R.id.R5);
        ImageView R5img = findViewById(R.id.R5image);
        TextView R5text = findViewById(R.id.t5);

        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(knowledge.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        //彈出視窗
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                popimg.setImageDrawable(R1img.getDrawable());
                poptext.setText(R1text.getText());
                popdescription.setTextSize(15);
                popdescription.setText("稻熱病會感染水稻的各個生育期，侵害各個部位。秧苗期稻熱病主要發生於葉片及葉鞘，此時稻組織較嫩易感病。稻熱病菌感染後，初期呈墨綠色或灰綠色，隨後轉為急速型之白色病斑，病斑迅速擴展，引起葉片甚至全株秧苗枯死。本田期水稻葉稻熱病之病斑順著葉脈擴展，圓形至紡錘形、兩端較尖，初期墨綠色或灰綠色，急速型病斑為白色，病斑上有大量病原菌孢子，後期病斑邊緣呈褐色或深褐色，中間灰白色，病斑邊緣黃暈不明顯。稻熱病較少危害本田稻熱病本田期水稻葉鞘，但會感染葉舌及葉節稱之為葉舌稻熱病。稻熱病菌感染葉舌後再擴展至葉節引起臨近葉片及葉鞘組織褐變乾枯萎凋死亡。節稻熱病發生於抽穗後之水稻，莖節褐變或黑變嚴重時稻莖枯死易折斷。葉鞘外側無明顯病徵，葉鞘內側表面則常有褐變現象，莖節上有大量病菌孢子。穗稻熱病包括穗頸、枝梗及穀粒稻熱病，穗頸及枝梗被害時，初呈灰綠色水浸狀病斑，病斑邊緣轉為深褐色，被害部位以上的枝梗及穀粒枯死。穀粒被害，呈暗褐色圓形或橢圓形病斑。");

                viewPopupwindow.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                popimg.setImageDrawable(R2img.getDrawable());
                poptext.setText(R2text.getText());
                popdescription.setTextSize(15);
                popdescription.setText("其病徵有三種型態，典型葉枯病徵、急性萎凋型病徵和淡黃化型病徵。病害在田間通常發生於分盛期之後，有時則在苗期就可發現病徵，主要在葉片或葉鞘，偶爾可在穀粒上出現。(一)幼苗期病徵：最初水浸狀小斑點在下位葉葉緣出現，高溫時斑點上出現菌泥，斑點後來延長，而漸變成黃色，病葉最後枯萎而很難和自然老化枯葉分辨，在熱帶地區幼苗可能經由葉片或根部之傷口或水孔侵入而成系統性感染。病菌在幼苗內繁殖迅速，而很快侵入維管束而造成幼苗之死亡。本田期之病徵：本田最初出現病徵在插秧後3~4 星期，而以分盛期後出現較多，常在葉緣離葉尖5-6公分處出現水浸狀小斑，然後逐漸延長而變成黃或枯黃色，而在健全或病斑之間有一狹小水浸狀帶，這些病斑通常沿一邊或兩邊葉緣，並向中肋擴大，最後可能到達葉鞘，黃色病斑最後變成白或灰白色，而在高溫狀態下易被腐生真菌所佔據，並出現很多大小不一黑斑，並有菌泥由病斑上溢出，菌泥乾燥呈黃色小球型，直徑約1.22公釐，很容易由病斑上脫落。在最適當發病條件下，看不到上述病徵，水浸狀和暗綠色病斑出現不久，罹病葉片即向內捲，病斑很快向下延伸，而上端葉片被害部份萎凋並成灰白色，有黏狀菌泥溢出，稱為急性型病徵，常在高氮肥地區及感病秈稻上出現。葉鞘上病徵主要由葉片往下延伸，最初呈黃綠或灰綠色條斑，再轉變成黃白色，在嚴重感染株整個葉鞘都褪色，枯萎，病株之根系發育不良，植株矮小，稔實率很低。(三)急性萎凋型病徵：最初記錄在印尼有此病徵型之發生，受感染之幼苗外葉捲起，萎凋並成灰綠到淡褐或灰褐色，植株很明顯矮小，並呈冠腐，用手壓擠可感覺到充滿黏性黃色細菌，病株有的整株由地際處斷開，而飄浮在水面，此型病徵偶而出現在成株上，大都在孕穗或開花期，數條大白色斑紋出現在劍葉，往下延伸至葉鞘，幼穗萎凋成白色，最後整株水稻枯死。急性萎凋型病徵在臺灣於1974年推廣嘉農秈8號和嘉農秈11號兩品種後在南部地區第二期大發生，停止栽培這兩品種後，田間再也沒有出現此型病徵。\n");


                viewPopupwindow.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        R3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                popimg.setImageDrawable(R3img.getDrawable());
                poptext.setText(R3text.getText());
                popdescription.setTextSize(15);
                popdescription.setText("稻種萌芽時受害會引起苗枯病，稻芽初呈黃褐色水浸狀病變，子葉上呈短黑色線狀斑點，嚴重時繼而枯萎死亡，病苗上有墨綠色菌絲及分生孢子，病苗之子葉褐色至深褐色，並向本葉之葉鞘蔓延，葉鞘受害後亦呈褐色病變，組織脆而易折斷，葉片生長受阻不易伸長，致葉片直立黃化，嚴重則與葉鞘一起枯萎死亡。老秧苗葉片病斑與本田期葉片病斑相似，初呈墨綠色水浸狀小斑點，隨後轉為褐色小斑點，再漸次擴大成為紡錘形或橢圓形，一般病斑如胡麻種子之大小，整個病斑呈褐色至深褐色，周圍明顯有黃暈，有些病斑上具有同心輪紋。稻植株缺氮、鉀或矽而顯現感病性時，病斑會繼續擴大，大型病斑沿葉脈呈長橢圓形，兩端較圓寬，黃暈明顯但較窄，常易被誤認為稻熱病斑，但稻熱病之病斑兩端較尖黃暈不明顯，兩者間有頗大差異。稻孕穗期嚴重發生胡麻葉枯病，植株呈現矮化，葉片常無法正常伸展，葉片縐縮，葉片組織略變厚，抽穗緩慢，稻穗短小結實不佳。本病甚少危害稻葉鞘，但會危害葉節及葉舌，即葉片與葉鞘交接處及其葉舌，初呈墨綠水浸狀小斑，而後轉為褐色，並擴大病斑，如發生在孕穗期劍葉之葉舌，嚴重時會造成抽穗不良。本病會感染稻穗，致病力比稻熱病弱。穗頸或枝梗被害後，形成墨綠色轉黑褐色病斑，被害部位以上之稻穗不會立即枯死。穀粒亦常被感染，初呈褐色至黑褐色小斑點，病斑擴大後成為胡麻種子大小之茶褐、暗褐色病斑，嚴重時病斑會擴展至大部份外穎，環境適宜並有孢子柄及分生孢子產生。稻穗被害後，空秕粒增加，其糙米病變為銹米、死米或青米等，對稻米品質影響甚鉅。");

                viewPopupwindow.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        R4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                popimg.setImageDrawable(R4img.getDrawable());
                poptext.setText(R4text.getText());
                popdescription.setTextSize(15);
                popdescription.setText("本病通常在水稻葉片上產生條狀褐色病斑，亦會感染葉鞘、葉舌、穗梗及稻穎等部位。病斑一般被局限於2~4條葉脈中間縱向擴展，形成細長條形病斑、寬度小於1公釐、長度2~10公釐，赤褐色，病斑外圍有黃暈，病斑會合併。部分稻品種之病斑較短黑褐色略有黃暈。");

                viewPopupwindow.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        R5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                popimg.setImageDrawable(R5img.getDrawable());
                poptext.setText(R5text.getText());
                popdescription.setTextSize(15);
                popdescription.setText("水稻從秧苗期至成熟期整個生育期間均會被紋枯病菌感染，各生育期之病徵大同小異。目前農友大多採用機械插秧，箱育秧苗時間短，秧苗期發生紋枯病的機會少。本田期水稻栽培過程中，從分中期至成熟期為紋枯病之主要發生期。秧苗初罹患紋枯病時於葉鞘與葉片上均出現灰污綠色水浸狀病斑，後期病斑中央灰白色邊緣褐色。本田水稻分中期紋枯病開始發生，紋枯病菌常從稻叢內部稻株之下位葉鞘開始感染，再向外部稻株及向上蔓延。葉鞘上病徵先形成約1公分大小的灰綠色水浸狀圓形或橢圓形病斑，後逐漸擴大為長約2~3公分，寬1公分之病斑，病斑邊緣褐色中間轉成灰白色，高濕高溫環境下數個病斑癒合成虎斑狀（圖一）。晴朗的氣候下，葉鞘組織枯死，並導致水份輸送不良，造成葉片黃化乾枯，稻叢外圍稻株之葉鞘雖然沒有紋枯病斑，但內部稻株之下位葉會有枯黃現象，撥開稻株常可看見稻叢內部有紋枯病發生。陰雨或重露的高濕環境下，葉鞘上病斑會蔓延擴展到葉片上。分盛期以後，稻叢之葉片相互交織，當葉片接觸到鄰近葉片或葉鞘之病斑亦會被傳染致病。葉部受害時初呈濕潤狀灰綠色，病斑迅速擴大形成雲紋狀或不正形的枯褐色大病斑。感染紋枯病之葉片組織枯死初期不易造成捲曲，與白葉枯病引起葉片捲曲，有所不同。水稻孕穗後期遇到高溫高濕之環境，農友如未採取防治紋枯病措施，發病稻叢之稻穗在抽出時就會被感染，稻穗被害部位呈污綠色，枝梗及穀粒腐朽呈灰褐色枯死。乳熟期以後之稻穗被害則局部初呈污綠色，後轉為灰褐色病斑。稻紋枯病在環境適宜，溫度24~28℃之高濕環境下，發病後約3~4天，病斑上或附近稻表面組織上的菌絲會開始形成菌核，菌核初呈白色菌絲團，經過約2天時間轉為褐色，菌核接觸寄主組織之一面常向內凹，而成不正之扁球形，紋枯病與其他菌核性病害之主要區別，為病株外表會形成菌核。");

                viewPopupwindow.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }
}
