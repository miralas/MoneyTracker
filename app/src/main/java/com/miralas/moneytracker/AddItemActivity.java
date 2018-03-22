package com.miralas.moneytracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG = "AddItemActivity";

    private EditText name, price;
    private Button addBtn;

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

        // Leave setTitle here, cause it's more universal solution
        setTitle(R.string.add_item_toolbar_title);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        addBtn = findViewById(R.id.add_btn);

        // Add text listener to fields
        name.addTextChangedListener(addItemTextWatcher);
        price.addTextChangedListener(addItemTextWatcher);

        // Add on click listener to add_btn
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String itemName = name.getText().toString();
//                String itemPrice = price.getText().toString();
                Log.i(TAG, "onClick: add_btn");
            }
        });

    }
}
