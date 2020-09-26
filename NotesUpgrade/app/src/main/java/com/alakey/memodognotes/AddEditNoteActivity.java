package com.alakey.memodognotes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.alakey.notesupgrade.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.alakey.notesupgrade.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.alakey.notesupgrade.EXTRA_DESCRIPTION";
    public static final String EXTRA_DATE =
            "com.alakey.notesupgrade.EXTRA_DATE";
    public static final String EXTRA_PRIORITY =
            "com.alakey.notesupgrade.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private TextView textViewDate;
    private RadioGroup radioGroupPriority;
    private Calendar date;
    private SharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Установка темы
        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else  setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        textViewDate = findViewById(R.id.text_view_date);
        radioGroupPriority = findViewById(R.id.radio_group_priority);
        date = Calendar.getInstance();
        //Установка начальной даты
        setInitialDate();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle(getString(R.string.edit_title));
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            textViewDate.setText(intent.getStringExtra(EXTRA_DATE));
        } else {
            setTitle(getString(R.string.add_title));
        }
    }

    private void saveNote() {
        //Заголовок
        String title = editTextTitle.getText().toString();
        //Описание
        String description = editTextDescription.getText().toString();
        //Дата
        String date = textViewDate.getText().toString();
        //Приоритет
        int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());

        //Проверка на заполненость полей
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, R.string.all_fill_in, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_DATE, date);
        data.putExtra(EXTRA_PRIORITY, priority);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    //Кнопка (галочка) сохранить
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // установка начальной даты
    private void setInitialDate() {
        textViewDate.setText(DateUtils.formatDateTime(this,
                date.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View view) {
        new DatePickerDialog(AddEditNoteActivity.this, d,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, monthOfYear);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };
}
