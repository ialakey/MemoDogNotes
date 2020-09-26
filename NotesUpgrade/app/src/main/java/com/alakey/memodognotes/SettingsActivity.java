package com.alakey.memodognotes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchTheme;
    private SharedPref sharedpref;
    private String urlFollow = "https://www.instagram.com/i_alakey";
    private String urlMoreApps = "https://play.google.com/store/apps/developer?id=I_Alakey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Установка темы
        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(getString(R.string.settings_header));

        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        switchTheme=(Switch)findViewById(R.id.switch_theme);
        if (sharedpref.loadNightModeState()==true) {
            switchTheme.setChecked(true);
        }
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartSettingsActivity();
                }
                else {
                    sharedpref.setNightModeState(false);
                    restartSettingsActivity();
                }
            }
        });
    }

    //Перезагрузка активности
    public void restartSettingsActivity () {
        Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(intent);
        finish();
    }

    //Перезагрузка активности х2
    public void restartMainActivity () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Кнопка назад
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                restartMainActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Другие приложения
    public void onClickMoreApps(View view) {
        Intent intentMoreApps = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMoreApps));
        startActivity(intentMoreApps);
    }

    //Разработчик
    public void onClickFollow(View view) {
        Intent intentFollow = new Intent(Intent.ACTION_VIEW, Uri.parse(urlFollow));
        startActivity(intentFollow);
    }

    //Поделиться
    public void onClickShare(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Приложение MemoDog: Заметки, скачивай прямо сейчас: " + urlMoreApps);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,"Поделиться"));
    }
}
