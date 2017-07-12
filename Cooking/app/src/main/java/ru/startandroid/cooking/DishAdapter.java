package ru.startandroid.cooking;

import android.content.Context;
import android.net.sip.SipAudioCall;
import android.net.sip.SipSession;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Asus on 10.07.2017.
 */

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    List<Dish> dishes;
    OnDishCkickListener onDishCkickListener;
    OnDishLongClickListener onDishLongClickListener;
    Context context;

    public interface OnDishCkickListener {
        public void onDishClick(Dish dish);
    }

    public interface OnDishLongClickListener{
        public void onDishLongClick(Dish dish);
    }

    public DishAdapter(List<Dish> dishes, OnDishCkickListener onDishCkickListener,OnDishLongClickListener onDishLongClickListener) {
        this.dishes = dishes;
        this.onDishCkickListener = onDishCkickListener;
        this.onDishLongClickListener = onDishLongClickListener;

    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DishViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dish, parent, false));
    }

    @Override
    public void onBindViewHolder(final DishViewHolder holder, int position) {
        holder.name.setText(dishes.get(position).getName());
        holder.time.setText(dishes.get(position).getTime());
        holder.difficult.setText(dishes.get(position).getDifficult());
        holder.recepie.setText(dishes.get(position).getRecepie());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDishCkickListener.onDishClick(dishes.get(holder.getAdapterPosition()));
            }
        });
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onDishLongClickListener.onDishLongClick(dishes.get(holder.getAdapterPosition()));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public class DishViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView recepie;
        TextView difficult;
        TextView time;
        RelativeLayout relativeLayout;

        public DishViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_tv);
            recepie = (TextView) itemView.findViewById(R.id.recepie);
            difficult = (TextView) itemView.findViewById(R.id.difficult);
            time = (TextView) itemView.findViewById(R.id.time);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rel_lay);
        }

    }

}
