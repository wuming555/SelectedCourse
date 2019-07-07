package com.system.controller;

public class MyThread extends Thread{
    //重写run方法，设置线程任务，即线程要干什么
    @Override
    public void run(){
      for(int i=0;i<20;i++){
          System.out.println("mythread"+i);
      }
    }

}
