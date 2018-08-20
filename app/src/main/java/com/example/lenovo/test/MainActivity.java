package com.example.lenovo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];
    int player1Points = 0;
    int player2Points = 0;
    boolean player1Turn = true;
    int count = 0;
    Button reset_button,clear_button;
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset_button = (Button) findViewById(R.id.reset_button);
        clear_button = (Button) findViewById(R.id.clear_button);
        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                String field = "button_" + i + j;
                int resID = getResources().getIdentifier(field,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAll();
                Toast.makeText(getApplicationContext(),"PLAYER1 : "+player1Points+", PLAYER2 : "+player2Points,Toast.LENGTH_SHORT).show();
            }
        });
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBoard();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            ((Button) view).setText("X");
        }
        else {
            ((Button) view).setText("O");
        }

        count++;
        if (checkForWin()){
            if (player1Turn) {
                Toast.makeText(getApplicationContext(), "PLAYER 1 IS WINNER", Toast.LENGTH_SHORT).show();
                player1Points++;
                t1.setText("PLAYER1:" + player1Points);
            }
            else {
                Toast.makeText(getApplicationContext(),"PLAYER 2 IS WINNER", Toast.LENGTH_SHORT).show();
                player2Points++;
                t2.setText("PLAYER2:"+player2Points);
            }
        }
        else if (count == 9){
            Toast.makeText(getApplicationContext(),"MATCH DRAW!",Toast.LENGTH_SHORT).show();
        }
        else {
            player1Turn = !player1Turn;
        }
    }

    public boolean checkForWin() {
        String[][] fill = new String[3][3];
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                fill[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i=0;i<3;i++){
            if (fill[i][0].equals(fill[i][1]) && fill[i][0].equals(fill[i][2]) && !fill[i][0].equals("")){
                return true;
            }
        }
        for (int i=0;i<3;i++){
            if (fill[0][i].equals(fill[1][i]) && fill[0][i].equals(fill[2][i]) && !fill[0][i].equals("")){
                return true;
            }
        }
        if (fill[0][0].equals(fill[1][1]) && fill[0][0].equals(fill[2][2]) && !fill[0][0].equals("")){
            return true;
        }
        if (fill[0][2].equals(fill[1][1]) && fill[0][2].equals(fill[2][0]) && !fill[0][2].equals("")){
            return true;
        }
        return false;
    }
    public void clearBoard(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        player1Turn = true;
        count = 0;
    }
    public void resetAll(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        t1.setText("PLAYER1:0");
        t2.setText("PLAYER2:0");
        player1Turn = true;
        count = 0;
    }
}
