package com.veer.multiselectsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.veer.multiselect.Adapter.MyRVAdpater;
import com.veer.multiselect.MultiSelectActivity;
import com.veer.multiselect.Util.Constants;
import com.veer.multiselect.Util.LoadBitmap;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  //views
  private EditText edNumber;
  private Button btImages;
  private Button btVideos;
  private RecyclerView rcItems;
  //vars
  private ArrayList<String> paths = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();
    setUpList();
    setUpEvents();
  }

  private void setUpList() {
    rcItems = (RecyclerView) findViewById(R.id.rvItems);
    StaggeredGridLayoutManager staggeredGridLayout = new StaggeredGridLayoutManager(3, 1);
    rcItems.setLayoutManager(staggeredGridLayout);
    rcItems.setAdapter(new ItemAdapter());
  }

  private void setUpEvents() {
    btImages.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, MultiSelectActivity.class);
        int limit = 5;
        if (!(edNumber.getText().toString().isEmpty())) {
          limit = Integer.parseInt(edNumber.getText().toString());
        }
        intent.putExtra(com.veer.multiselect.Util.Constants.LIMIT, limit);
        intent.putExtra(com.veer.multiselect.Util.Constants.SELECT_TYPE,
            com.veer.multiselect.Util.Constants.PATH_IMAGE);
        startActivityForResult(intent,
            com.veer.multiselect.Util.Constants.REQUEST_CODE_MULTISELECT);
      }
    });
    btVideos.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, MultiSelectActivity.class);
        int limit = 5;
        if (!(edNumber.getText().toString().isEmpty())) {
          limit = Integer.parseInt(edNumber.getText().toString());
        }
        intent.putExtra(com.veer.multiselect.Util.Constants.LIMIT, limit);
        intent.putExtra(com.veer.multiselect.Util.Constants.SELECT_TYPE, Constants.PATH_VIDEO);
        startActivityForResult(intent,
            com.veer.multiselect.Util.Constants.REQUEST_CODE_MULTISELECT);
      }
    });
  }

  private void initViews() {
    edNumber = (EditText) findViewById(R.id.edLimit);
    btImages = (Button) findViewById(R.id.btImages);
    btVideos = (Button) findViewById(R.id.btVideos);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == com.veer.multiselect.Util.Constants.REQUEST_CODE_MULTISELECT
        && resultCode == RESULT_OK) {
      paths = data.getStringArrayListExtra(com.veer.multiselect.Util.Constants.GET_PATHS);
      rcItems.getAdapter().notifyDataSetChanged();
    }
  }

  class ItemAdapter extends RecyclerView.Adapter<MyViewHolder> {

    @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemView =
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

      return new MyViewHolder(itemView);
    }

    @Override public void onBindViewHolder(MyViewHolder holder, int position) {
      LoadBitmap.loadBitmap(paths.get(position), holder.ivItem);
    }

    @Override public int getItemCount() {
      return paths.size();
    }
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivItem;

    public MyViewHolder(View itemView) {
      super(itemView);
      ivItem = (ImageView) itemView.findViewById(R.id.ivItem);
    }
  }
}
