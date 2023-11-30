package com.example.project5;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.preference.PreferenceManager;


public class GlobalVariable extends Application {
    public String planet_method;
    public boolean switchState;

    // 从 SharedPreferences 中加载开关状态
    public void loadSwitchState(Context context) {
        SharedPreferences switchStatePreferences = context.getSharedPreferences("SwitchStatePreferences", Context.MODE_PRIVATE);
        switchState = switchStatePreferences.getBoolean("switch_state", false);
        if (switchState) {
            planet_method = "有機";
        } else {
            planet_method = "慣行";
        }
    }
}

//switchStateValue = switchState.getBoolean("switch_state", false);
//if(switchStateValue){planet_method = "有機";}
//else {planet_method = "慣行";}