package domon.cn.timer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity {
    @Bind(R.id.categroy_rv)
    RecyclerView mCategroiesRv;
    private CategoriesAdapter mRecyelerViewAdapter;

    public static void actionStart(Context context) {
        Intent i = new Intent(context, CategoriesActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ButterKnife.bind(this);

        mRecyelerViewAdapter = new CategoriesAdapter(this);
        mCategroiesRv.setLayoutManager(new GridLayoutManager(this, 2));
        mCategroiesRv.setAdapter(mRecyelerViewAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
