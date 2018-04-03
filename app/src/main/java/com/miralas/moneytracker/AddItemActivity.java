package com.miralas.moneytracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.miralas.moneytracker.api.AddItemResult;
import com.miralas.moneytracker.api.Api;

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
    private App app;

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

        app = (App)getApplication();
        api = app.getApi();


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
                addItem(item, app.getAuthToken(), intent);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void addItem(Item item, String token, final Intent intent) {
        Call<AddItemResult> call = api.addItem(item.price, item.name, type);

        call.enqueue(new Callback<AddItemResult>() {
            @Override
            public void onResponse(Call<AddItemResult> call, Response<AddItemResult> response) {
                AddItemResult result = response.body();
                if (result.status.equals(getString(R.string.success_msg))) {
                    setResult(RESULT_OK, intent);
                } else {
                    setResult(RESULT_CANCELED, intent);
                }
            }

            @Override
            public void onFailure(Call<AddItemResult> call, Throwable t) {
                setResult(RESULT_CANCELED, intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
