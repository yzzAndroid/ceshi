// MAIDLManger.aidl
package com.qianfeng.yzz.myaidl3;
import com.qianfeng.yzz.myaidl3.Student;
// Declare any non-default types here with import statements

interface MyIDLManger {
    List<Student> getList();
    void add(in Student student);
}
