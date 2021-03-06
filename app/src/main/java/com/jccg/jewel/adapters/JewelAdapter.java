package com.jccg.jewel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jccg.jewel.R;
import com.jccg.jewel.entities.Jewel;

import org.w3c.dom.Text;

import java.util.List;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class JewelAdapter extends BaseAdapter {

    /**
     *
     */
    private LayoutInflater inflater;

    /**
     *
     */
    private List<Jewel> jewels = null;

    /**
     *
     * @param context
     */
    public JewelAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     *
     * @param jewels
     */
    public void setData(List<Jewel> jewels) {
        this.jewels = jewels;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCount() {
        if (jewels == null) {
            return 0;
        }
        return jewels.size();
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        if (jewels == null || jewels.get(position) == null) {
            return null;
        }
        return jewels.get(position);
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     *
     * @param position
     * @param currentView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View currentView, ViewGroup parent) {

        if (currentView == null) {
            currentView = inflater.inflate(R.layout.jewel_item_list, parent, false);
        }

        final Jewel jewel = jewels.get(position);

        if (jewel != null) {
            ((TextView) currentView.findViewById(R.id.item_name)).setText(jewel.getName());
            ((TextView) currentView.findViewById(R.id.item_description)).setText(jewel.getDescription());
        }

        return currentView;
    }

}
