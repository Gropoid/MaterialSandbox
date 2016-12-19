package gropoid.materialsandbox;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by geraud on 05/11/16.
 */

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior bottomSheetBehavior;  // received from

    private BottomSheetBehavior.BottomSheetCallback bottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setBottomSheetCallback(bottomSheetBehaviorCallback);
            bottomSheetBehavior.setPeekHeight(200);
            bottomSheetBehavior.setHideable(false);
        }
    }

    void setState(int newState) {
        switch(newState){
            case(BottomSheetBehavior.STATE_COLLAPSED):
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case(BottomSheetBehavior.STATE_EXPANDED):
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case(BottomSheetBehavior.STATE_HIDDEN):
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            default:
                // do nothing
                break;
        }


    }
}
