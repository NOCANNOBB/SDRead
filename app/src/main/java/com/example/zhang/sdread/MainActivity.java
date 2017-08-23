package com.example.zhang.sdread;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    private EditText mTxtName;
    private Button mButton;
    private EditText mulTextContent;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionRequest pr = new PermissionRequest();
        String[] Permissions = {"android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"};
        pr.RequestPermission(this.getApplicationContext(),Permissions,this);
        mTxtName = (EditText)this.findViewById(R.id.txtName);
        mButton = (Button)this.findViewById(R.id.button2);
        mulTextContent = (EditText)findViewById(R.id.editText);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nr = loadText(mTxtName.getText().toString().trim());
                mulTextContent.setText(nr);

            }
        });

    }

    public String loadText(String name){
        String nr = null;
        String FilePath = Environment.getExternalStoragePublicDirectory("")+"/" + name;
        try
        {

            File f = new File(FilePath);
            byte[] buff = new byte[(int)f.length()];
            FileInputStream fis = new FileInputStream(f);
            fis.read(buff);
            fis.close();
            nr = new String(buff,"utf-8");
            nr = nr.replaceAll("\\r\\n","\n");
        }
        catch (Exception e){
            Toast.makeText(getBaseContext(),"没有找到指定文件:" + FilePath,Toast.LENGTH_LONG).show();
        }
        return  nr;
    }
}
