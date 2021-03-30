package ru.mirea.denisignatenko.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView currentDateTime;
    public static Calendar dateTime=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDateTime=findViewById(R.id.currentDateTime);
        setDateTime();
    }

    public void setDateTime(){
        currentDateTime.setText("Текущая дата и время : " +
                DateUtils.formatDateTime(this,dateTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE|
                                DateUtils.FORMAT_SHOW_YEAR));
    }

    public void onClickShowDialog(View view) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onClickTimeDialog(View view){
        TimePickerDialog dialogFragment = new TimePickerDialog
                (this,listenerTime,dateTime.get(Calendar.HOUR_OF_DAY),
                        dateTime.get(Calendar.MINUTE),true);
        dialogFragment.show();
    }

    public void onClickDateDialog(View view){
        DatePickerDialog dialogFragment = new DatePickerDialog
                (this,listenerDate,dateTime.get(Calendar.YEAR),
                        dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH));
        dialogFragment.show();
    }

    public void onClickProgressDialog(View view){
        ProgressDialog dialogFragment = new ProgressDialog(this);
        dialogFragment.setTitle("Загрузка");
        dialogFragment.setMessage("Получаю время");
        dialogFragment.setButton(Dialog.BUTTON_POSITIVE,"Ждём-с",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        dialogFragment.show();
    }

    TimePickerDialog.OnTimeSetListener listenerTime = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            setDateTime();
        }
    };

    DatePickerDialog.OnDateSetListener listenerDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDateTime();
        }
    };

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку ок....",
                Toast.LENGTH_SHORT).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку cancel",
                Toast.LENGTH_SHORT).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку нейтрально",
                Toast.LENGTH_SHORT).show();
    }
}