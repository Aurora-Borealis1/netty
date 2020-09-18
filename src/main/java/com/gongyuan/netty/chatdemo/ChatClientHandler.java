package com.gongyuan.netty.chatdemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author by TaoWangwang
 * @classname MyHandler
 * @description TODO
 * @date 2020/9/18 13:59
 */
public class ChatClientHandler extends SimpleChannelInboundHandler <String>{

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

}
