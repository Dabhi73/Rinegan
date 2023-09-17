package ipproject.com;

import android.content.Context;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CommonMethod {

    CommonMethod(Context context, String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
