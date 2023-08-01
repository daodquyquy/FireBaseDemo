package vn.fpt.ph26439.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText edt_id,edt_name,edt_email,edt_address;
    private Button btnSend,btnUpdate,btnDelete;
    private TextView tv_data;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        database = FirebaseDatabase.getInstance();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                reference = database.getReference("message");
//                reference.setValue(edt_id.getText().toString()); // cập nhật dữ liệu
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        // lấy về ở đây
//                        String s = snapshot.getValue(String.class);
//                        tv_data.setText(s);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        // Lỗi ở đây
//                    }
//                });
                //reference = database.getReference("user");
                reference = database.getReference("listUser/1");
                int id = Integer.parseInt(edt_id.getText().toString());
                String name = edt_name.getText().toString();
                String email = edt_email.getText().toString();
                String address = edt_address.getText().toString();
                User user = new User(id,name,email,address);
                reference.setValue(user);

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user1 = snapshot.getValue(User.class);
                        tv_data.setText(user1.getName());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    private void initUI(){
        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_address = findViewById(R.id.edt_address);
        btnSend = findViewById(R.id.btnSend);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        tv_data = findViewById(R.id.tv_thongtin);

    }
}