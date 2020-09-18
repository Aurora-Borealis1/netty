package com.gongyuan.netty.pipelin;

/**
 * @author by TaoWangwang
 * @classname MyPipelineDemo
 * @description TODO
 * @date 2020/9/18 10:36
 */
public class MyPipelineDemo2 {
    MyHandlerContext head = new MyHandlerContext(new Handler() {
        void handle(MyHandlerContext context, Object o) {
            context.nextProcess(o);
        }
    });

    void addLast(Handler handler){
        MyHandlerContext p = head;
        while (p.next != null){
            p = p.next;
        }
        p.next = new MyHandlerContext(handler);
    }

    void startProcess(Object o){
        head.process(o);
    }

    public static void main(String[] args) {
        MyPipelineDemo2 demo2 = new MyPipelineDemo2();
        demo2.addLast(new HandlerA());
        demo2.addLast(new HandlerB());
        demo2.addLast(new HandlerB());
        demo2.addLast(new HandlerA());

        demo2.startProcess("小摩托");
    }


}




class MyHandlerContext{
    Handler handler;
    MyHandlerContext next;

    public MyHandlerContext(Handler handler) {
        this.handler = handler;
    }

    public void process(Object o){
        handler.handle(this,o);
    }

    public void nextProcess(Object o){
        if(next != null){
            next.process(o);
        }

    }

}

abstract class Handler{
    abstract void handle(MyHandlerContext context,Object o);
}


class HandlerA extends Handler{

    void handle(MyHandlerContext context,Object o) {
        System.out.println("我是HandlerA");
        System.out.println("我正在处理"+o.toString());
        context.nextProcess(o);
    }
}

class HandlerB extends Handler{

    void handle(MyHandlerContext context,Object o) {
        System.out.println("我是HandlerB");
        System.out.println("我正在处理"+o.toString());
        context.nextProcess(o);
    }
}