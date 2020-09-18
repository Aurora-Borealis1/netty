package com.gongyuan.netty.chatdemo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;

/**
 * @author by TaoWangwang
 * @classname MyHandler
 * @description TODO
 * @date 2020/9/18 13:59
 */
public class ChatServerHandler extends SimpleChannelInboundHandler <String>{
    private static final ChannelGroup channels = new  DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        System.out.println("发送消息的是"+channel.remoteAddress());
        channel.writeAndFlush("自己："+msg+"\n");
        channels.forEach(ch -> {
            if(ch != channel){
                ch.writeAndFlush(channel.remoteAddress()+"发送消息："+msg+"\n");
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"下线了");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.add(channel);
        channels.writeAndFlush("服务器"+ channel.remoteAddress()+"加入群聊"+"\n");

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.add(channel);
        channels.writeAndFlush("服务器"+ channel.remoteAddress()+"退出群聊"+"\n");
        //channels.remove(channel);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
