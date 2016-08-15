package com.xlw.android.demo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.xlw.android.demo.databinding.ActivityMainBinding;
import com.xlw.android.demo.engine.FloatViewManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FloatViewManager.getInstance(this).showFloatView();

       // android.os.Process.killProcess(android.os.Process.myPid());

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        student = new Student("张三", "南京", "18810950723");
       // student = new Student();
       /* student.setPhone("18810950723");
        student.setAddr("南京");
        student.setName("张三");*/

        binding.setStudent(student);

        final List<Student> list = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            Student  student = new Student("王五" + i, "湖北" + i, "13373268956");
            list.add(student);
        }



  /*      StudentAdapter<Student> adapter = new StudentAdapter<>(this, list, R.layout.list_item, com.xlw.android.demo.BR.student);
        binding.mListview.setAdapter(adapter);
        binding.mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.get(position).setName("武藏");
                Toast.makeText(MainActivity.this, "student:" + list.get(position).getName() + "," + position, Toast.LENGTH_LONG).show();

            }
        });*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            student.setPhone("15543496725");
                            student.setAddr("上海");
                            student.setName("小明");
                            Log.d("222", "显示出来" + Thread.currentThread().getName());

                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }




}
