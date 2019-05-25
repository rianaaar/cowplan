package com.psbo.cowplan;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import com.psbo.cowplan.Model.data_sapi;
import com.psbo.cowplan.BacaSapiActivity;
import com.psbo.cowplan.InputSapiActivity;



public class AdapterSapiRecycleView extends RecyclerView.Adapter<AdapterSapiRecycleView.ViewHolder> {

    private ArrayList<data_sapi> daftarSapi;
    private Context context;
    FirebaseDataListener listener;

    public AdapterSapiRecycleView(ArrayList<data_sapi> sapis, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarSapi = sapis;
        context = ctx;
        listener = (BacaSapiActivity)ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.sapi);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sapi, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarSapi.get(position).getNama_sapi();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk Delete dan update data
                 */
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_view);
                    dialog.setTitle("Pilih Aksi");
                    dialog.show();

                    Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                    //apabila tombol delete diklik
                    delButton.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    /**
                                     *  Kodingan untuk Delete data
                                     */
                                    dialog.dismiss();
                                    listener.onDeleteData(daftarSapi.get(position), position);
                                }
                            }
                    );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarSapi.size();
    }
    public interface FirebaseDataListener{
        void onDeleteData(data_sapi sapi, int position);
    }
}
