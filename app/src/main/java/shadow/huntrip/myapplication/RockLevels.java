package shadow.huntrip.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import shadow.huntrip.myapplication.levels.Level1;
import shadow.huntrip.myapplication.levels.Level2;
import shadow.huntrip.myapplication.levels.Level3;
import shadow.huntrip.myapplication.levels.Level4;
import shadow.huntrip.myapplication.levels.Level5;
import shadow.huntrip.myapplication.levels.Level6;
import shadow.huntrip.myapplication.levels.Level7;
import shadow.huntrip.myapplication.levels.Level8;
import shadow.huntrip.myapplication.levels.Level9;
import shadow.huntrip.myapplication.levels.Level10;

public class RockLevels extends AppCompatActivity {
    private final int[] _levelValues = new int[]{
            R.id.LP, R.id.SOAD, R.id.Skillet, R.id.Scorpions, R.id.KuSh,
            R.id.Queen, R.id.Metallica, R.id.Rammstein, R.id.Nirvana, R.id.ACDC
    };

    private SharedPreferences _save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rocklevels);

        _save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = _save.getInt("Level", 1);

        for(int i=1; i<level; i++){
            TextView tv = findViewById(_levelValues[i]);
            tv.setText(""+(i+1));
        }
    }
        public void level1 (View sender)
        {
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 1) {
                finish();
                startActivity(new Intent(this, Level1.class));
            }
        }

        public void level2 (View sender)
        {
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 2) {
                finish();
                startActivity(new Intent(this, Level2.class));
            }
        }

        public void level3 (View sender)
        {
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 3) {
                finish();
                startActivity(new Intent(this, Level3.class));
            }
        }

        public void level4 (View sender)
        {
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 4) {
                finish();
                startActivity(new Intent(this, Level4.class));
            }
        }

        public void level5 (View sender)
        {
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 5) {
                finish();
                startActivity(new Intent(this, Level5.class));
            }
        }

        public void level6 (View sender)
        {
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 6) {
                finish();
                startActivity(new Intent(this, Level6.class));
            }
        }

        public void level7 (View sender)
        {
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 7) {
                finish();
                startActivity(new Intent(this, Level7.class));
            }
        }

        public void level8 (View sender){
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 8) {
                finish();
                startActivity(new Intent(this, Level8.class));
            }
        }

        public void level9 (View sender)
        {
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 9) {
                finish();
                startActivity(new Intent(this, Level9.class));
            }
        }

        public void level10 (View sender)
        {
            _save = getSharedPreferences("Save", MODE_PRIVATE);
            final int level = _save.getInt("Level", 1);
            if (level >= 10) {
                finish();
                startActivity(new Intent(this, Level10.class));
            }
        }

        public void btn_back (View sender)
        {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
