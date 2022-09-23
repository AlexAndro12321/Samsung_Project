package shadow.huntrip.myapplication.addition;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CustomTimer extends AppCompatActivity
{
    private static final class CustomTimerInstanceHolder
    {
        private static final CustomTimer _INSTANCE = new CustomTimer();
    }

    private CustomTimer(){}

    private int _seconds;
    private boolean _isInterrupted;

    public void SetInterrupt(boolean value)
    {
        _isInterrupted = value;
    }

    public static CustomTimer GetInstance()
    {
        return CustomTimerInstanceHolder._INSTANCE;
    }

    public void StartTimer(TextView textView, Context appContext, MediaPlayer soundPlayer, int seconds)
    {
        _seconds = seconds;

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() ->
        {
            if(_isInterrupted)
            {
                textView.setText("");
                scheduler.shutdown();
                return;
            }

            if(_seconds < 0 && soundPlayer != null)
            {
                soundPlayer.stop();

                runOnUiThread(() -> Toast.makeText(appContext, "Время вышло", Toast.LENGTH_SHORT).show());

                textView.setText("");
                scheduler.shutdown();
                return;
            }

            textView.setText(String.valueOf(_seconds));
            _seconds--;
        }, 0, 1, TimeUnit.SECONDS);
    }
}