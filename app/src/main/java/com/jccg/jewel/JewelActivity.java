package com.jccg.jewel;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jccg.jewel.entities.Jewel;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class JewelActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     *
     */
    private EditText editTextJewelName;

    /**
     *
     */
    private EditText editTextJewelPrice;

    /**
     *
     */
    private EditText editTextJewelDescription;

    /**
     *
     */
    private FloatingActionButton saveJewelButton;

    /**
     *
     */
    private Realm realm;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        editTextJewelName = findViewById(R.id.edit_text_jewel_name);
        editTextJewelPrice = findViewById(R.id.edit_text_jewel_price);
        editTextJewelDescription = findViewById(R.id.edit_text_jewel_description);

        saveJewelButton = findViewById(R.id.save_jewel_button);
        saveJewelButton.setOnClickListener(this);

    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     *
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Jewel jewel = bgRealm.createObject(Jewel.class);
                jewel.setName(editTextJewelName.getText().toString());
                jewel.setPrice(Float.parseFloat(editTextJewelPrice.getText().toString()));
                jewel.setDescription(editTextJewelDescription.getText().toString());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                JewelActivity.super.onBackPressed();
                finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(JewelActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
