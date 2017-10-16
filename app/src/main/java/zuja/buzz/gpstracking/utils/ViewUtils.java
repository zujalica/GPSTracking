package zuja.buzz.gpstracking.utils;


import android.view.View;

/**
 * Utils class for common view operations.
 */
public class ViewUtils {

    public static void toggleEditableView(boolean editable, View... views){
        for(View v : views){
            v.setEnabled(editable);
        }
    }

}
