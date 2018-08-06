package com.example.rohit.searchfromlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView lv;
    private EditText editText;
    private ArrayAdapter<String> adapter;

    private String products[] = {"Apple", "Banana","Pinapple", "Orange", "Papaya", "Melon",
            "Grapes", "Water Melon","Lychee", "Guava", "Mango", "Kivi"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listView);
        editText =findViewById(R.id.editText);
        adapter = new ArrayAdapter<>(this, R.layout.for_list_view, R.id.product_name, products);
        lv.setAdapter(adapter);

        final List<String> list = Arrays.asList(products);
        Log.d("listIs",""+list);

        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                adapter.getFilter().filter(charSequence);
                Log.d("beforeTextChanged","i="+i+"    i1="+i1+"  i2="+i2);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                Log.d("onTextChanged","i="+i+"    i1="+i1+"  i2="+i2);
                adapter.getFilter().filter(charSequence);
                Log.d("charSeqIs",""+charSequence);
                String string = ""+charSequence;
                Log.d("stringIs",string);
                if (list.contains(string))
                    return;
                else
                    Toast.makeText(MainActivity.this,"does not exist.",Toast.LENGTH_SHORT).show();
                    //((TextView)findViewById(R.id.product_name)).setText("Item does not exist.");
                //Toast.makeText(getApplicationContext(),"on text change",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                //Toast.makeText(getApplicationContext(),"after text change",Toast.LENGTH_SHORT).show();
                if (editable!=null)
                    Log.d("editable","true");
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String string = ((TextView)view.findViewById(R.id.product_name)).getText().toString();
                Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
                Log.d("StringIs",string);
            }
        });
    }
}
