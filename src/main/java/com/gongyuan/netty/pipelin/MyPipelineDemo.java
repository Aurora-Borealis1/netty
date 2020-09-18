package com.gongyuan.netty.pipelin;

/**
 * @author by TaoWangwang
 * @classname MyPipelineDemo
 * @description TODO
 * @date 2020/9/18 10:36
 */
public class MyPipelineDemo {
    HandlerContext head = new HandlerContext(new MyAbstractHandler(){
        @Override
        void handle(HandlerContext context, Object arg) {
            context.runNext(arg);
        }
    });

    void addLast(MyAbstractHandler handler ){
        HandlerContext p = head;
        while (p.next != null){
            p = p.next;
        }
        p.next = new HandlerContext(handler);
    }

    void startProcess(Object arg){
        head.run(arg);
    }


    public static void main(String[] args) {

    }





}


class HandlerContext{
    MyAbstractHandler handler;
    HandlerContext next;


    public HandlerContext(MyAbstractHandler handler) {
        this.handler = handler;
    }


    void run(Object arg){
        this.handler.handle(this,arg);
    }

    void runNext(Object arg){
        if(this.next != null){
            next.run(arg);
        }
    }




}
abstract class MyAbstractHandler {
    abstract void handle(HandlerContext context ,Object arg);
}


class MyHandler1 extends  MyAbstractHandler{

    void handle(HandlerContext context, Object arg) {
        System.out.println("我是handler1");
        System.out.println("我正在处理"+arg);
        context.runNext(arg);
    }
}

class MyHandler2 extends MyAbstractHandler {

    void handle(HandlerContext context, Object arg) {
        System.out.println("我是handler2");
        System.out.println("我正在处理"+arg);
    }
}