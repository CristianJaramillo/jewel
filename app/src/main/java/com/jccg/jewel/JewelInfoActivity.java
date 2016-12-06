package com.jccg.jewel;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jccg.jewel.entities.Jewel;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

public class JewelInfoActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     *
     */
    private TextView textViewJewelName;

    /**
     *
     */
    private TextView textViewJewelPrice;

    /**
     *
     */
    private TextView textViewJewelDescription;

    /**
     *
     */
    private FloatingActionButton updateJewelButton;

    /**
     *
     */
    private FloatingActionButton deleteJewelButton;

    /**
     *
     */
    private Realm realm;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewel_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        textViewJewelName = (TextView) findViewById(R.id.text_view_jewel_name);
        textViewJewelName.setText(getIntent().getStringExtra("name"));
        textViewJewelDescription = (TextView) findViewById(R.id.text_view_jewel_description);
        textViewJewelDescription.setText(getIntent().getStringExtra("description"));
        textViewJewelPrice = (TextView) findViewById(R.id.text_view_jewel_price);
        textViewJewelPrice.setText(String.format("$ %7.2f", getIntent().getFloatExtra("price", 0.00f)));

        deleteJewelButton = (FloatingActionButton) findViewById(R.id.delete_jewel_button);
        deleteJewelButton.setOnClickListener(this);

    }

    /**
     *
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
    protected void onDestroy()
    {
        super.onDestroy();
        realm.close();
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                RealmQuery<Jewel> query = bgRealm.where(Jewel.class);
                query.equalTo("name", getIntent().getStringExtra("name"));
                query.equalTo("price", getIntent().getFloatExtra("price", 0.00f));
                query.findFirst().deleteFromRealm();

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                onBackPressed();
                finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(JewelInfoActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
