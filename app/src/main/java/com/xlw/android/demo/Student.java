package com.xlw.android.demo;

import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by wei on 2016/8/4.
 */
public class Student extends BaseObservable {

    private String name;
    private String addr;
    private String phone;


    public Student(String name, String addr, String phone) {
        this.name = name;
        this.addr = addr;
        this.phone = phone;
    }
   // @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
       // notifyPropertyChanged(com.xlw.android.demo.BR.name);
        notifyChange();
       // Log.d("222", "调用setName方法" + com.xlw.android.demo.BR.name);
    }
  //  @Bindable
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
     //   notifyPropertyChanged(com.xlw.android.demo.BR.addr);
        notifyChange();
    }
   // @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
       // notifyPropertyChanged(com.xlw.android.demo.BR.phone);
        notifyChange();
    }



    public void click(View view){
       setName(getName() + "( 已点击 )");
        Toast.makeText(view.getContext(),"长按了昵称:"+getName(),Toast.LENGTH_SHORT).show();
    }

}
