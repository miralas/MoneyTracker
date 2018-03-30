package com.miralas.moneytracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG = "AddItemActivity";

    public static final String TYPE_KEY = "type";

    private EditText name, price;
    private Button addBtn;

    private String type;
    private Api api;

    // Text watcher for all fields on this activity
    private TextWatcher addItemTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            addBtn.setEnabled(checkFields());
        }
    };

    // Func to check fields
    private boolean checkFields() {
        return !TextUtils.isEmpty(name.getText().toString().trim()) && !TextUtils.isEmpty(price.getText().toString().trim());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_item_toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        type = getIntent().getStringExtra(TYPE_KEY);

        // Leave setTitle here, cause it's more universal solution
        setTitle(R.string.add_item_toolbar_title);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        addBtn = findViewById(R.id.add_btn);

        // Add text listener to fields
        name.addTextChangedListener(addItemTextWatcher);
        price.addTextChangedListener(addItemTextWatcher);

        api = ((App)getApplication()).getApi();


        // Add on click listener to add_btn
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = name.getText().toString();
                int itemPrice = Integer.parseInt(price.getText().toString());

                Item item = new Item(itemName, itemPrice, type);

                Intent intent = new Intent();
                intent.putExtra("item", item);

                // send post request to api(just for test)
                addItem(item);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void addItem(Item item) {
        Call<String> call = api.addItem(item);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
