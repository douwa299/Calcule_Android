package com.example.test1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity3 extends AppCompatActivity  implements View.OnClickListener {
    private Toolbar toolbar;


    private Button back;
    private MaterialButton nine , eight , seven, six, five, four, three, two , one, zero;
    private MaterialButton   add, multp , equale,virgule,buttc,div , parenthese,ac,minus ,prct;
    private TextView operation , result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        this.result=findViewById(R.id.resultat);
        this.operation=findViewById(R.id.operation);
        assignId(buttc,R.id.buttc);
        assignId(nine,R.id.nine);
        assignId(eight,R.id.eight);
        assignId(seven,R.id.seven);
        assignId(six,R.id.six);
        assignId(five,R.id.five);
        assignId(four,R.id.four);
        assignId(three,R.id.three);
        assignId(two,R.id.two);
        assignId(one,R.id.one);
        assignId(zero,R.id.zero );
        assignId(add,R.id.add);
        assignId(div,R.id.div);
        assignId(minus,R.id.minus);
        assignId(multp,R.id.multp);
        assignId(equale,R.id.equale);
        assignId(virgule,R.id.virgule);
        assignId(parenthese,R.id.parenthese);
        assignId(ac,R.id.ac);
        assignId(prct,R.id.prct);




    }

   void assignId(MaterialButton btn , int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
   }
    @Override
    public void onClick(View v) {
         MaterialButton Button =(MaterialButton) v;
            String ButtonText= Button.getText().toString();
            String datatocalculate=operation.getText().toString();
            if(ButtonText.equals("AC")){
                operation.setText("");
                result.setText("0");
                return;
            }
            if (ButtonText.equals(("="))){
                operation.setText(result.getText());
                return;
            }
            if (ButtonText.equals("C")){
                datatocalculate=datatocalculate.substring(0,datatocalculate.length()-1);

            }else{
                datatocalculate=datatocalculate+ButtonText;
            }

            operation.setText(datatocalculate);

            String finalresult = getresult(datatocalculate);
            if(!finalresult.equals("err")){
                result.setText(finalresult);
            }


    }
    String getresult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
        
            Scriptable scriptable =context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;
        }catch(Exception e){
            return "err";

        }
      
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.standard) {
            Toast.makeText(getApplicationContext(),"Standard sélectionné!",Toast.LENGTH_LONG).show();
           


            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}