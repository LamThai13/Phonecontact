package com.example.lamthai.phonecontact;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lamthai.phonecontact.adapter.ContactAdapter;
import com.example.lamthai.phonecontact.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact> arrayContact;
    EditText edt_name,edt_number;
    RadioButton rdbt_male, rdbt_female;
    Button btn_add_contact;
    ListView lv_contact;
    ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        arrayContact = new ArrayList<Contact>();
        adapter = new ContactAdapter(this,R.layout.item_contact,arrayContact);
        lv_contact.setAdapter(adapter);
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogConfirm(position);
            }
        });

        btn_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString().trim();
                String number = edt_number.getText().toString().trim();
                boolean isMale = true;
                if (rdbt_male.isChecked()){
                    isMale = true;
                }else isMale = false;
            if (TextUtils.isEmpty(name)||TextUtils.isEmpty(number)){
                Toast.makeText(MainActivity.this,"input", Toast.LENGTH_SHORT).show();
            }else {
                Contact contact = new Contact(isMale,name,number);
                arrayContact.add(contact);
            }
            adapter.notifyDataSetChanged();
            }
        });
    }
    public void showDialogConfirm(final int position){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btncall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnsendMessage = (Button) dialog.findViewById(R.id.btn_sendmassege);

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCall(position);
                dialog.dismiss();
            }
        });
        btnsendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void sendSMS(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"+arrayContact.get(position).getmNumber()));
        startActivity(intent);
    }

    private void intentCall(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+arrayContact.get(position).getmNumber()));
        startActivity(intent);
    }

    public void init(){
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_number = (EditText) findViewById(R.id.edt_number);
        rdbt_male = (RadioButton) findViewById(R.id.rdbt_male);
        rdbt_female = (RadioButton) findViewById(R.id.rdbt_female);
        btn_add_contact = (Button) findViewById(R.id.btn_add_contact);
        lv_contact = (ListView) findViewById(R.id.lv_contact);
    }
}
