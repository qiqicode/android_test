package code.two;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by qiqicode on 13-7-14.
 */
public class NewGame extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.new_game);
        new AlertDialog.Builder(this)
                .setTitle(R.string.new_game_title)
                .setItems(R.array.difficulty,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                startGame(i);
                            }
                        }
                        ).show();
    }

    public void startGame(int i)
    {
        Log.d("TAG", "clicked on" + i);
    }
}
