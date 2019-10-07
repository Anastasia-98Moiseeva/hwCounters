package com.example.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;


public class ItemActivity extends Activity {

    private static final int POSITION_ALL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        initSpiner(R.id.textFrom, R.id.spinnerFrom);
        initSpiner(R.id.textTo, R.id.spinnerTo);

        initTextView(R.id.textFrom, R.id.spinnerFrom);
        initTextView(R.id.textTo, R.id.spinnerTo);

        TextView textFrom = findViewById(R.id.from);
        textFrom.setText(getResources().getString(R.string.from));

        TextView textTo = findViewById(R.id.to);
        textTo.setText(getResources().getString(R.string.to));

        final EditText fromNumber = findViewById(R.id.fromNumber);
        final EditText toNumber = findViewById(R.id.toNumber);

        Button convert = findViewById(R.id.convert);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner from = findViewById(R.id.spinnerFrom);
                Spinner to = findViewById(R.id.spinnerTo);
                if(from.getSelectedItem().equals(to.getSelectedItem())){
                    toNumber.setText(fromNumber.getText());
                } else {
                    toNumber.setText(convert(String.valueOf(fromNumber.getText())));
                }
            }
        });
    }

    public String convert(String value) {
        if (value.equals("")) {
            return "0";
        } else {
            return String.valueOf(Integer.parseInt(value)+2);
        }
    }

    private void initTextView(int textId, int spinerId){
        TextView text = findViewById(textId);
        Spinner spinner = findViewById(spinerId);
        String str = spinner.getSelectedItem().toString();
        text.setText(str);
    }

    private void initSpiner(final int textId, final int spinerId){
        Spinner spinner = findViewById(spinerId);
        spinner.setAdapter( new SpinnerAdapter(Arrays.asList(getResources().getStringArray(R.array.arr_of_Counter))));
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

}
