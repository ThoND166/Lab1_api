package com.thondph16247_myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {
    Context context=this;
    FirebaseFirestore database;
    String id="";
    Todo todo=null;
    TextView tvKQ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKQ=findViewById(R.id.tvKQ);
        database=FirebaseFirestore.getInstance();//khoi tao
        //insertData();
        //updateData();
        deleteData();
//       selectData();
    }
    void insertData()
    {
        id= UUID.randomUUID().toString();//lay 1 id ngau nhien
        todo=new Todo(id,"title 1 04","content 1 04");
        HashMap<String,Object> mapToDo=todo.convertHashMap();//chuyen sang dang co the insert firebase
        database.collection("TODO").document(id)
                .set(mapToDo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Them thanh cong", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Them that bai", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    void updateData(){
        id="de223797-6c97-4e73-a7b3-8cb302ff6b85";
        todo=new Todo(id,"title update hung 1","content update hung 1");
        database.collection("TODO")
                .document(todo.getId())
                .update(todo.convertHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Update thanh cong", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "update that bai", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    void deleteData()
    {
        id="de223797-6c97-4e73-a7b3-8cb302ff6b85";
        database.collection("TODO").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    String strKQ="";
    ArrayList<Todo> selectData()
    {
        ArrayList<Todo> list=new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            strKQ="";
                            for(QueryDocumentSnapshot doc: task.getResult())
                            {
                                Todo t=doc.toObject(Todo.class);//chuyen ket qua sang object
                                strKQ+="id: "+t.getId()+"\n";
                                strKQ+="title: "+t.getTitle()+"\n";
                                strKQ+="content: "+t.getContent()+"\n";
                                list.add(t);
                            }
                            Toast.makeText(context, "Doc thanh cong", Toast.LENGTH_SHORT).show();
                            tvKQ.setText(strKQ);
                        }
                        else {
                            Toast.makeText(context, "Doc khong thanh cong", Toast.LENGTH_SHORT).show();
                        }
                        
                        
                    }
                });
        return list;
    }
}