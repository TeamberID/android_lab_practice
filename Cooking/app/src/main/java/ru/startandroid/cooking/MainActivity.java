package ru.startandroid.cooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DishAdapter.OnDishCkickListener, DishAdapter.OnDishLongClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Dish> dishes = new ArrayList<>();
        for(int i = 0; i < 6; i ++){
            Dish d = new Dish();
            dishes.add(d);
        }

        dishes.get(0).setName("pahlava");
        dishes.get(1).setName("halva");
        dishes.get(2).setName("SHAURMA");
        dishes.get(3).setName("mcduck");
        dishes.get(4).setName("chop");
        dishes.get(5).setName("vegetables");



        RecyclerView dishesRv = (RecyclerView) findViewById(R.id.rv_food);
        DishAdapter adapter = new DishAdapter(dishes,this,this);
        dishesRv.setAdapter(adapter);
        dishesRv.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onDishClick(Dish dish) {
        Intent intent = new Intent(this,act2.class);
        intent.putExtra(act2.EXTRA_TEXT, dish.getName());
        startActivity(intent);
    }

    @Override
    public void onDishLongClick(Dish dish) {
        Toast.makeText(this,"WAZZZZZUP",Toast.LENGTH_SHORT).show();
    }
}
