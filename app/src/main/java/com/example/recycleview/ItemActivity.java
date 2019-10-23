package com.example.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Objects;


public class ItemActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        String item = Objects.requireNonNull(getIntent().getExtras()).getString("item");

        initSpiner(R.id.textFrom, R.id.spinnerFrom, item);
        initSpiner(R.id.textTo, R.id.spinnerTo, item);

        initTextView(R.id.textFrom, R.id.spinnerFrom);
        initTextView(R.id.textTo, R.id.spinnerTo);

        final TextView textFrom = findViewById(R.id.from);
        textFrom.setText(getResources().getString(R.string.from));

        TextView textTo = findViewById(R.id.to);
        textTo.setText(getResources().getString(R.string.to));

        final EditText fromNumber = findViewById(R.id.fromNumber);
        final EditText toNumber = findViewById(R.id.toNumber);
        final Button convert = findViewById(R.id.convert);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner fromSpiner = findViewById(R.id.spinnerFrom);
                Spinner toSpiner = findViewById(R.id.spinnerTo);
                String fromSpinerString = fromSpiner.getSelectedItem().toString();
                String toSpinerString = toSpiner.getSelectedItem().toString();
                if(fromSpinerString.equals(toSpinerString)){
                    String s2 = fromNumber.getText().toString();
                    String s = convert(fromSpinerString, s2);
                    toNumber.setText(s);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.convertationErroe), Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }

    public String convert(String str1, String str2) {
        String[] valueAndCounter = str2.split(" ");
        String value = valueAndCounter[0];
        String counter = "";
        for(int i = 1; i < valueAndCounter.length; i++){
            counter += (valueAndCounter[i]) + " ";
        }
        counter = counter.substring(0, counter.length() - 1);
        switch (str1){
            case "Длина":
                if (valueAndCounter.length == 2) {
                    return convertToSantimiters(value, counter);
                } else {
                    return "Ошибка в единице измерения";
                }
            case "Площадь":
                if (valueAndCounter.length == 4) {
                    return convertToSantimitersInSquare(value, counter);
                } else {
                    return "Ошибка в единице измерения";
                }
            case "Время":
                if (valueAndCounter.length == 2) {
                    return convertToSeconds(value, counter);
                } else {
                    return "Ошибка в единице измерения";
                }
        }
        return "";
    }

    private String convertToSeconds(String value, String counter){
        switch (counter) {
            case "ч":
                return Integer.valueOf(value) * 3600 + " с";
            case "мин":
                return Integer.valueOf(value) * 60 + " с";
            case "с":
                return Integer.valueOf(value) + " с";
        }
        return "Неизвестная единица измерения";
    }

    private String convertToSantimitersInSquare(String value, String counter){
        switch (counter) {
            case "см в кв":
                return Integer.valueOf(value) * 0.0001 + " м в кв";
            case "мм в кв":
                return Integer.valueOf(value) * 0.000001 + " м в кв";
            case "м в кв":
                return Integer.valueOf(value) + " м в кв";
        }
        return "Неизвестная единица измерения";
    }

    private String convertToSantimiters(String value, String counter){
        switch (counter) {
            case "см":
                return Integer.valueOf(value) * 0.01 + " м";
            case "мм":
                return Integer.valueOf(value) * 0.001 + " м";
            case "м":
                return Integer.valueOf(value) + " м";
        }
        return "Неизвестная единица измерения";
    }

    private void initTextView(int textId, int spinerId){
        TextView text = findViewById(textId);
        Spinner spinner = findViewById(spinerId);
        String str = spinner.getSelectedItem().toString();
        text.setText(str);
    }

    private void initSpiner(final int textId, final int spinerId, String item){
        Spinner spinner = findViewById(spinerId);
        spinner.setAdapter( new SpinnerAdapter(Arrays.asList(getResources().getStringArray(R.array.arr_of_Counter))));
        spinner.setSelection(getItemIndex(spinner, item));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initTextView(textId, spinerId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private int getItemIndex(Spinner spinner, String myString){
        for (int i = 0; i < spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

}
