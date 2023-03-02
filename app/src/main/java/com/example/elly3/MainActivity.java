package com.example.elly3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edName, edSecondName, edEmail;
    private DatabaseReference mDatabase;//ссылка на объект бд
    private  String USER_KEY = "USER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private  void init() { //void ничего не возвращает
    edName = findViewById(R.id.edName);
    edSecondName = findViewById(R.id.edSecondName);
    edEmail = findViewById(R.id.edEmail);
    mDatabase= FirebaseDatabase.getInstance().getReference(USER_KEY);
    }
    public  void onClickSave(View view) {
        String id = mDatabase.getKey();//получение значения из поля
        String name = edName.getText().toString();//получение текста и превращение в
        String sec_name = edSecondName.getText().toString();//получение текста и превращение в строку
        String email = edEmail.getText().toString();//получение текста и превращение в строку
        User newUser = new User(id, name, sec_name, email);

        if (!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(sec_name)&& !TextUtils.isEmpty(email)) {
            //проверка на пустое поле
            mDatabase.push().setValue(newUser);//отправить информацию о бд -создание пользователя
            Toast.makeText(this,"Пользователь успешно доббавлен", Toast.LENGTH_SHORT).show();
        }
        else {//выдает сообщение о пустом поле
            Toast.makeText(this,"Пустое поле",Toast.LENGTH_SHORT).show();
        }
    }
    public  void onClickRead(View view){

    }
}