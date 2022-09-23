package shadow.huntrip.myapplication.levels;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import shadow.huntrip.myapplication.R;
import shadow.huntrip.myapplication.RockLevels;
import shadow.huntrip.myapplication.addition.CustomDialog;
import shadow.huntrip.myapplication.addition.CustomTimer;

public class Level8 extends AppCompatActivity
{
    private String _currentSound;

    private List<String> _tempSounds;
    private List<String> _allSounds;
    private List<Button> _optionButtons;

    private MediaPlayer _soundPlayer;
    private Dialog _explainDialog;

    private CustomTimer _customTimerInstance;

    private int _correctAnswersCounter;

    private Button _play_music_btn;

    private final int[] _progress = new int[]{
            R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
            R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
    };

    @Override
    protected void onCreate(Bundle savedInstanceBundle)
    {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.all_levels);

        InitializeElements();
        InitializeExplainDialog();
        ResetButtonsText();
        SetButtonsClipToOutline();
        GetSongData();
    }

    private void InitializeElements()
    {
        _currentSound = "";
        _tempSounds = new ArrayList<>();
        _allSounds = new ArrayList<>();
        _optionButtons = new ArrayList<>();
        _soundPlayer = null;
        _correctAnswersCounter = 0;
        _customTimerInstance = CustomTimer.GetInstance();

        _optionButtons.add(findViewById(R.id.optionA_btn));
        _optionButtons.add(findViewById(R.id.optionB_btn));
        _optionButtons.add(findViewById(R.id.optionC_btn));
        _optionButtons.add(findViewById(R.id.optionD_btn));

    }

    private void InitializeExplainDialog()
    {
        _explainDialog = new Dialog(this);
        _explainDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        _explainDialog.setContentView(R.layout.prewviewdialogone);
        _explainDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _explainDialog.show();

        ImageView previewImg = _explainDialog.findViewById(R.id.prewviewImg);
        previewImg.setImageResource(R.drawable.rammstein_logo);

        TextView textView = _explainDialog.findViewById(R.id.dialogText);
        textView.setText(R.string.Rammstein_text);
    }

    private void ResetButtonsText()
    {
        _optionButtons.forEach(button -> button.setText(""));
    }

    private void SetButtonsClipToOutline()
    {
        _optionButtons.forEach(button -> button.setClipToOutline(true));
    }

    //Делаем кнопки доступными для нажатия
    private void MakeButtonsEnabled()
    {
        _optionButtons.forEach(button -> button.setEnabled(true));
    }

    //Делаем кнопки недоступными для нажатия
    private void MakeButtonsDisabled()
    {
        _optionButtons.forEach(button -> button.setEnabled(false));
    }

    private void GetSongData()
    {
        try
        {
            _tempSounds = new ArrayList<>(Arrays.asList(getAssets().list("Level8_music")));

            for(int i = 0; i < _tempSounds.size(); i++)
                _tempSounds.set(i, "Level8_music/" + _tempSounds.get(i));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        _allSounds = new ArrayList<>(_tempSounds);
        SelectRandomSong();
    }

    public void play_music_btn_click(View sender)
    {
        PlaySound();
    }

    private void PlaySound()
    {
        AssetFileDescriptor fileDescriptor;

        try
        {
            fileDescriptor = getAssets().openFd(_currentSound);
            _soundPlayer = new MediaPlayer();
            _soundPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength());
            _soundPlayer.prepare();
            _soundPlayer.start();

            _play_music_btn = findViewById(R.id.listen_music);
            _play_music_btn.setEnabled(false);

            StartTimer();

            MakeButtonsEnabled();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void StartTimer()
    {
        _customTimerInstance.StartTimer(findViewById(R.id.Timer), this, _soundPlayer, 10);
        _customTimerInstance.SetInterrupt(false);
    }

    private void InterruptTimer()
    {
        _customTimerInstance.SetInterrupt(true);
    }

    private void SelectRandomSong()
    {
        Random random = new Random();

        try
        {
            int index = random.nextInt(_tempSounds.size());
            _currentSound = _tempSounds.get(index);
            _tempSounds.remove(index);
            SetChooseButtons();
        }
        catch (Exception e)
        {
            if(_correctAnswersCounter >= 8 && _correctAnswersCounter <= 10)
            {
                SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                final int level = save.getInt("Level", 1);
                if (level <= 8)
                {
                    SharedPreferences.Editor editor = save.edit();
                    editor.putInt("Level", 9);
                    editor.commit();
                }

                CustomDialog.ShowPositiveDialog(this, Level9.class, this, _correctAnswersCounter);
            }
            else
            {
                switch(_correctAnswersCounter)
                {
                    case 0, 5, 6, 7 -> CustomDialog.ShowNegativeDialog(this, Level8.class, this, _correctAnswersCounter, "баллов");
                    case 1 -> CustomDialog.ShowNegativeDialog(this, Level8.class, this, _correctAnswersCounter, "балл");
                    case 2, 3, 4 -> CustomDialog.ShowNegativeDialog(this, Level8.class, this, _correctAnswersCounter, "балла");
                    default -> throw new IllegalArgumentException("Нифига не работает ><");
                }
            }
        }
    }

    private void SetChooseButtons()
    {
        Random random = new Random();
        int index = random.nextInt(4);

        var currentSoundFileName = _currentSound.substring(_currentSound.lastIndexOf('/') + 1);
        var tempSoundsAllPaths = new ArrayList<>(_allSounds);

        for(int i = 0; i < _optionButtons.size(); i++)
        {
            if(index == i)
            {
                _optionButtons.get(i).setText(currentSoundFileName.replace(".mp3", ""));
            }

            else
            {
                var nextIndex = random.nextInt(tempSoundsAllPaths.size());
                var randomSoundPath = tempSoundsAllPaths.get(nextIndex);

                var randomSoundFileName =
                        randomSoundPath.substring(randomSoundPath.lastIndexOf('/') + 1);

                while(randomSoundFileName.equals(currentSoundFileName))
                {
                    nextIndex = random.nextInt(tempSoundsAllPaths.size());
                    randomSoundPath = tempSoundsAllPaths.get(nextIndex);
                    randomSoundFileName =
                            randomSoundPath.substring(randomSoundPath.lastIndexOf('/') + 1);
                }

                tempSoundsAllPaths.remove(randomSoundPath);
                _optionButtons.get(i).setText(randomSoundFileName.replace(".mp3", ""));
            }
        }
    }

    public void Options_btn_click(View sender)
    {
        var currentSoundFileName =
                _currentSound.substring(_currentSound.lastIndexOf('/') + 1)
                        .replace(".mp3", "");

        Button clicked_btn = (Button) sender;

        if(currentSoundFileName.equals(clicked_btn.getText().toString()))
        {
            if(_soundPlayer != null)
            {
                _soundPlayer.stop();
                InterruptTimer();
                Toast.makeText(this, "Вы угадали!", Toast.LENGTH_SHORT).show();
                _correctAnswersCounter += 1;
                SelectRandomSong();
                _play_music_btn = findViewById(R.id.listen_music);
                _play_music_btn.setEnabled(true);

                MakeButtonsDisabled();

                for (int i = 0; i < 10; i++)
                    findViewById(_progress[i]).setBackgroundResource(R.drawable.style_points_default);

                for(int i = 0; i < _correctAnswersCounter; i++)
                    findViewById(_progress[i]).setBackgroundResource(R.drawable.style_points_catch);
            }
        }
        else
        {
            if (_correctAnswersCounter > 0)
                _correctAnswersCounter -= 1;

            else
                _correctAnswersCounter = 0;

            Toast.makeText(this, "Не, нифига, попробуйте ещё раз", Toast.LENGTH_SHORT).show();

            for (int i = 0; i < 9; i++)
                findViewById(_progress[i]).setBackgroundResource(R.drawable.style_points_default);

            for(int i = 0; i < _correctAnswersCounter; i++)
                findViewById(_progress[i]).setBackgroundResource(R.drawable.style_points_catch);
        }
    }

    public void btn_back_rocklevels(View sender)
    {
        finish();
        startActivity(new Intent(this, RockLevels.class));
        _soundPlayer.stop();
    }

    public void btn_close(View sender)
    {
        _explainDialog.dismiss();
    }
}