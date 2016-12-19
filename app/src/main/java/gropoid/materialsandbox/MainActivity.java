package gropoid.materialsandbox;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(android.R.id.button1)
    Button button1;
    @BindView(android.R.id.button2)
    Button button2;
    @BindView(android.R.id.button3)
    Button button3;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;

    // Inflated later
    View peekContentView;

    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getLayoutInflater().inflate(R.layout.fragment_bottom_sheet, nestedScrollView);
        bottomSheetBehavior = BottomSheetBehavior.from(nestedScrollView);
        peekContentView = ButterKnife.findById(nestedScrollView, R.id.peek_content);
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56 + 32, r.getDisplayMetrics());
        bottomSheetBehavior.setPeekHeight(Math.round(px));
        BottomSheetBehavior.BottomSheetCallback callback = new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i(TAG, "New state COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i(TAG, "New state EXPANDED");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        };
        bottomSheetBehavior.setBottomSheetCallback(callback);
        initFragment();
    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MyFragment()).commit();
    }


    @OnClick({android.R.id.button1, android.R.id.button2, android.R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case android.R.id.button1: // expand
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case android.R.id.button2: // collapse
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            default:
                break;
        }
    }
}
