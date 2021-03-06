package com.theteus.kubota.SKCModule;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.theteus.kubota.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SKCForm01 extends Fragment {
    EditText prefixEdit;
    EditText nameEdit;
    EditText surNameEdit;
    EditText idNumEdit;
    EditText phoneEdit;
    EditText emailEdit;
    EditText faxEdit;
    EditText sklPhoneEdit;
    EditText mobPhone1Edit;
    EditText mobPhone2Edit;
    EditText vehicleOwnEdit;
    EditText birthDateEdit;
    TextView ageText;
    RadioGroup genderGroup;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
    RadioGroup personGroup;
    RadioButton generalPersonRadioButton;
    RadioButton corporationRadioButton;

    private DatePickerDialog birthDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    public SKCForm01(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skcform01, container, false);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById(view);
        initDatePick(view);

        return view;
    }

    private void findViewsById(View view){
        birthDateEdit = (EditText) view.findViewById(R.id.skc_form1_birthdate);
        birthDateEdit.setInputType(InputType.TYPE_NULL);
        ageText = (TextView) view.findViewById(R.id.skc_form1_age);

        prefixEdit = (EditText) view.findViewById(R.id.skc_form1_prefix);
        nameEdit = (EditText) view.findViewById(R.id.skc_form1_name);
        surNameEdit = (EditText) view.findViewById(R.id.skc_form1_surname);
        idNumEdit = (EditText) view.findViewById(R.id.skc_form1_idnum);
        phoneEdit = (EditText) view.findViewById(R.id.skc_form1_phone);
        emailEdit = (EditText) view.findViewById(R.id.skc_form1_email);
        faxEdit = (EditText) view.findViewById(R.id.skc_form1_fax);
        sklPhoneEdit = (EditText) view.findViewById(R.id.skc_form1_sklphone);
        mobPhone1Edit = (EditText) view.findViewById(R.id.skc_form1_mobphone1);
        mobPhone2Edit = (EditText) view.findViewById(R.id.skc_form1_mobphone2);
        vehicleOwnEdit = (EditText) view.findViewById(R.id.skc_form1_productnumber);

        genderGroup  = (RadioGroup) view.findViewById(R.id.skc_form1_gender);
        maleRadioButton = (RadioButton) genderGroup.findViewById(R.id.skc_form1_gender1);
        femaleRadioButton = (RadioButton) genderGroup.findViewById(R.id.skc_form1_gender2);
        personGroup = (RadioGroup) view.findViewById(R.id.skc_form1_persontype);
        generalPersonRadioButton = (RadioButton) personGroup.findViewById(R.id.skc_form1_persontype1);
        corporationRadioButton = (RadioButton) personGroup.findViewById(R.id.skc_form1_persontype2);
    }

    private void initDatePick(View view){
        Calendar newCalendar = Calendar.getInstance();
        birthDatePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                birthDateEdit.setText(dateFormatter.format(newDate.getTime()));
                ageText.setText(ageCalculator(year, monthOfYear, dayOfMonth));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        birthDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthDatePickerDialog.show();
            }
        });
    }

    private String ageCalculator(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (age<=0){
            birthDateEdit.setError("ใส่วัน/เดือน/ปีเกิด ไม่ถูกต้องนะจ๊ะ");
            return "อายุ";
        }
        else {
            birthDateEdit.setError(null);
            return "อายุ " + String.valueOf(age) + " ปี";
        }
    }

    public Bundle getAllData(){
        Bundle args = new Bundle();
        String prefix="";
        String name="";
        String surName="";
        String idNum="";
        String phone="";
        String email="";
        String fax="";
        String sklPhone="";
        String mobPhone1="";
        String mobPhone2="";
        String vehicleOwn="";
        String birthDate="";

        /*prefix = prefixEdit.getText().toString();
        name = nameEdit.getText().toString();
        surName = surNameEdit.getText().toString();
        idNum = idNumEdit.getText().toString();
        phone = phoneEdit.getText().toString();
        email = emailEdit.getText().toString();
        fax = faxEdit.getText().toString();
        sklPhone = sklPhoneEdit.getText().toString();
        mobPhone1 = mobPhone1Edit.getText().toString();
        mobPhone2 = mobPhone2Edit.getText().toString();
        vehicleOwn = vehicleOwnEdit.getText().toString();
        birthDate = birthDateEdit.getText().toString();*/

        args.putString("prefix", prefix);
        args.putString("name", name);
        args.putString("surName", surName);
        args.putString("idNum", idNum);
        args.putString("phone", phone);
        args.putString("email", email);
        args.putString("fax", fax);
        args.putString("sklPhone", sklPhone);
        args.putString("mobPhone1", mobPhone1);
        args.putString("mobPhone2", mobPhone2);
        args.putString("vehicleOwn", vehicleOwn);
        args.putString("birthDate", birthDate);

        args.putString("test", "Page 1 Retrieved data field");

        return args;
    }
}
