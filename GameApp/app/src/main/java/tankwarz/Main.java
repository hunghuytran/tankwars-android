package tankwarz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tranhung.gameapp.R;

public class Main extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Layout
        setContentView(R.layout.activity_main);
    }



    public void startGame(View view)
    {
        //Creates a gamepanel
        setContentView(new GamePanel(this));
    }



}
