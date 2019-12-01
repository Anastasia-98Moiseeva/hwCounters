package com.example.recycleview;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recycleview.adapters.SpinnerAdapter;

import java.util.Arrays;

public class ItemFragment extends Fragment {
    private static final String ITEM_STR = "item";

    private String item;

    private ItemFragment(){
        super(R.layout.fragment_item);
    }

    public static ItemFragment newInstance(String str) {

        Bundle args = new Bundle();
        ItemFragment fragment = new ItemFragment();
        args.putSerializable(ITEM_STR, str);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        item = (String) getArguments().getSerializable(ITEM_STR);
        init(root);
        return root;
    }

    private void init(final View root){
        initSpiner(root, R.id.textFrom, R.id.spinnerFrom, item);
        initSpiner(root, R.id.textTo, R.id.spinnerTo, item);

        initTextView(root, R.id.textFrom, R.id.spinnerFrom);
        initTextView(root, R.id.textTo, R.id.spinnerTo);

        final TextView textFrom = root.findViewById(R.id.from);
        textFrom.setText(getResources().getString(R.string.from));

        TextView textTo = root.findViewById(R.id.to);
        textTo.setText(getResources().getString(R.string.to));

        final EditText fromNumber = root.findViewById(R.id.fromNumber);
        final EditText toNumber = root.findViewById(R.id.toNumber);
        final Button convert = root.findViewById(R.id.convert);

        final Spinner fromSpiner = root.findViewById(R.id.spinnerFrom);
        final Spinner toSpiner = root.findViewById(R.id.spinnerTo);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromSpinerString = fromSpiner.getSelectedItem().toString();
                String toSpinerString = toSpiner.getSelectedItem().toString();
                if(fromSpinerString.equals(toSpinerString)){
                    String s2 = fromNumber.getText().toString();
                    String s = convert(fromSpinerString, s2);
                    toNumber.setText(s);
                } else {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            getResources().getString(R.string.convertationErroe), Toast.LENGTH_LONG);
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

    private void initTextView(View root, int textId, int spinerId){
        TextView text = root.findViewById(textId);
        Spinner spinner = root.findViewById(spinerId);
        String str = spinner.getSelectedItem().toString();
        text.setText(str);
    }

    private void initSpiner(final View root, final int textId, final int spinerId, String item){
        Spinner spinner = root.findViewById(spinerId);
        spinner.setAdapter( new SpinnerAdapter(Arrays.asList(getResources()
                .getStringArray(R.array.arr_of_Counter))));
        spinner.setSelection(getItemIndex(spinner, item));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initTextView(root, textId, spinerId);
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
