package game.MindGame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Created by Maxis on 20-Oct-15.
 */
public class SettingsActivity extends Activity {

    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_COUNTER = "difficulty";
    private SharedPreferences mSettings;
    String[] data = {"Считалочка", "Цветные кружочечки", "Бегающая точечка"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        getActionBar().hide();

    }
    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
      /*  SharedPreferences.Editor editor = mSettings.edit();
        if (switch1.isChecked())
            editor.putInt(APP_PREFERENCES_COUNTER, 1);
        else
        if (switch2.isChecked())
            editor.putInt(APP_PREFERENCES_COUNTER, 2);
        else
        if (switch3.isChecked())
            editor.putInt(APP_PREFERENCES_COUNTER, 3);
        editor.apply(); */
    }

    @Override
    protected void onResume() {
        super.onResume();
        Switch switch1 = (Switch) findViewById(R.id.switch1) ;
        Switch switch2 = (Switch) findViewById(R.id.switch2) ;
        Switch switch3 = (Switch) findViewById(R.id.switch3) ;


        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            Toast.makeText(getBaseContext(), "Position = " + mSettings.getInt(APP_PREFERENCES_COUNTER, 2), Toast.LENGTH_SHORT).show();
            int temp=mSettings.getInt(APP_PREFERENCES_COUNTER,0);
           /* switch (temp) {
                case 1:
                    switch2.toggle();
                case 2:
                    switch2.toggle();
                case 3:
                    switch3.toggle();
                default:
                    switch1.toggle();
            } */

            // mCounter = mSettings.getInt(APP_PREFERENCES_COUNTER, 0);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        switch1.setChecked(true);
        //attach a listener to check for changes in state
        switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch2.setChecked(false);
                    switch3.setChecked(false);
                } else {
                    if (!(switch2.isChecked() || switch3.isChecked()))
                        switch1.setChecked(true);
                }

            }
        });

        switch2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch1.setChecked(false);
                    switch3.setChecked(false);
                } else {
                    if (!(switch1.isChecked() || switch3.isChecked()))
                        switch2.setChecked(true);
                    for (int i = 0; i < 1; i++) {
                        i++;
                    }
                }
            }
        });

        switch3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch2.setChecked(false);
                    switch1.setChecked(false);
                    for (int i = 0; i < 1; i++)
                        i++;
                } else {
                    if (!(switch1.isChecked() || switch2.isChecked()))
                        switch3.setChecked(true);
                }
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner1.setAdapter(adapter);
        // заголовок
        spinner1.setPrompt("Выберите уровень");
        // выделяем элемент
        spinner1.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позицию нажатого элемента
                // Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // второе выпадающее меню
        spinner2.setAdapter(adapter);
        // заголовок
        spinner2.setPrompt("Выберите уровень");
        // выделяем элемент
        spinner2.setSelection(1);
        // устанавливаем обработчик нажатия

        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                if (spinner2.getSelectedItemPosition() == spinner3.getSelectedItemPosition())
                    if (spinner3.getSelectedItemPosition() != 0) {
                        spinner2.setSelection(0);
                    } else {
                        spinner2.setSelection(spinner3.getSelectedItemPosition() + 1);
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        // третье выпадающее меню

        spinner3.setAdapter(adapter);
        // заголовок
        spinner3.setPrompt("Выберите уровень");
        // выделяем элемент
        spinner3.setSelection(2);
        // устанавливаем обработчик нажатия
        spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                if (spinner2.getSelectedItemPosition() == spinner3.getSelectedItemPosition())
                    if (spinner2.getSelectedItemPosition() != 0) {
                        spinner3.setSelection(0);
                    } else
                        spinner3.setSelection(spinner2.getSelectedItemPosition() + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        SharedPreferences.Editor editor = mSettings.edit();
        // if (switch1.isChecked())
        //     editor.putInt(APP_PREFERENCES_COUNTER, 1);
        // else
        if (switch2.isChecked())
            editor.putInt(APP_PREFERENCES_COUNTER, 2);
        // else
        // if (switch3.isChecked())
        //     editor.putInt(APP_PREFERENCES_COUNTER, 3);
        editor.apply();

    }
}


