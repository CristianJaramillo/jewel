package com.jccg.jewel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jccg.jewel.adapters.JewelAdapter;
import com.jccg.jewel.entities.Jewel;

import java.io.InputStream;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.internal.IOException;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    /**
     *
     */
    private ListView listView;

    /**
     *
     */
    private FloatingActionButton addJewelButton;

    /**
     *
     */
    private JewelAdapter jewelAdapter;

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
        setContentView(R.layout.activity_main);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        // Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);

        addJewelButton = (FloatingActionButton) findViewById(R.id.add_jewel);
        addJewelButton.setOnClickListener(this);

    }

    /**
     *
     */
    @Override
    public void onResume()
    {
        super.onResume();

        if(jewelAdapter == null)
        {

            jewelAdapter = new JewelAdapter(this);
            jewelAdapter.setData(loadJewels());
            jewelAdapter.notifyDataSetChanged();

            listView = (ListView) findViewById(R.id.list_jewel);
            listView.setAdapter(jewelAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                /**
                 *
                 * @param parent
                 * @param view
                 * @param position
                 * @param id
                 */
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Jewel jewel = (Jewel) parent.getItemAtPosition(position);

                    Intent intent = new Intent(MainActivity.this, JewelInfoActivity.class);

                    intent.putExtra("name", jewel.getName());
                    intent.putExtra("price", jewel.getPrice());
                    intent.putExtra("description", jewel.getDescription());

                    startActivity(intent);

                }
            });

            listView.invalidate();

        } else {
            Log.v(getLocalClassName(), "jewelAdapter exists");
            jewelAdapter.setData(loadJewels());
            listView.setAdapter(jewelAdapter);
            jewelAdapter.notifyDataSetChanged();
        }

    }

    /**
     *
     */
    @Override
    public void onDestroy(){
        super.onDestroy();
        realm.close();
    }

    /**
     *
     * @return
     */
    private List<Jewel> loadJewels() {
        return realm.where(Jewel.class).findAll();
    }

    /**
     *
     * @throws java.io.IOException
     */
    private void loadJsonFromStream() throws java.io.IOException {
        // Use streams if you are worried about the size of the JSON whether it was persisted on disk
        // or received from the network.
        InputStream stream = getAssets().open("jewels.json");

        // Open a transaction to store items into the realm
        realm.beginTransaction();
        try {
            realm.createAllFromJson(Jewel.class, stream);
            realm.commitTransaction();
        } catch (IOException e) {
            // Remember to cancel the transaction if anything goes wrong.
            realm.cancelTransaction();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, JewelActivity.class));
    }
}