package gropoid.materialsandbox;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by geraud on 19/12/2016.
 */

public class CompoundView extends LinearLayout {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;

    public CompoundView(Context context) {
        this(context, null);
    }

    public CompoundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public CompoundView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.compound_view, this);
        ButterKnife.bind(getRootView(), this);
    }

    @OnClick({R.id.tv1, R.id.tv2})
    public void onClick(View view) {
        String message;
        switch (view.getId()) {
            case R.id.tv1:
                message = "TextView 1 clicked !";
                break;
            case R.id.tv2:
            default:
                message = "TextView 2 clicked !";
                break;
        }
        Snackbar.make(tv1, message, Snackbar.LENGTH_SHORT).show();
    }
}
