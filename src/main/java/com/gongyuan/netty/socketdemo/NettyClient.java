package com.gongyuan.netty.socketdemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author by TaoWangwang
 * @classname NettyServer
 * @description TODO
 * @date 2020/9/17 11:48
 */
public class NettyClient {
    public static void main(String[] args) {
        //构造两个线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            //服务端启动辅助类
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new SocketClientInitializer());

            ChannelFuture future = bootstrap.connect("localhost",9999).sync();
            //等待服务端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // 优雅退出，释放线程池资源
            eventLoopGroup.shutdownGracefully();
        }

    }
}
