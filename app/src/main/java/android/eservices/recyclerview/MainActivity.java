package android.eservices.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GameActionInterface{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private MyAdapter myAdapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        setupRecyclerView();


    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        myAdapter = new MyAdapter(DataGenerator.generateData());
        recyclerView.setAdapter(myAdapter);

    }

    public void displaySnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();

    }
   /* public void changeLayout(){
        layoutManager = new LinearLayoutManager(this);
        if (recyclerView.getLayoutManager() == layoutManager){
            GridLayoutManager grid;
            grid = new GridLayoutManager();
            recyclerView.setLayoutManager(grid);
        }
    }*/
    @Override
    public void onGameInfoClicked(String gameTitle) {
        List<GameViewModel> data = DataGenerator.generateData();
        for (int i = 0; i<data.size();i++){
            if (data.get(i).getTitle()== gameTitle){
                displaySnackBar("ce jeu est "+data.get(i).getDescription());
            }
        }
    }

    @Override
    public void onGameClicked(String gameTitle) {
        List<GameViewModel> data = DataGenerator.generateData();
        for (int i = 0; i<data.size();i++){
            if (data.get(i).getTitle()== gameTitle){
                displaySnackBar(data.get(i).getDescription() + " si ma mémoire est bonne");
            }
        }
    }

    //TODO create callback methods for item click
    //Use ressource strings to get the text to display

}
