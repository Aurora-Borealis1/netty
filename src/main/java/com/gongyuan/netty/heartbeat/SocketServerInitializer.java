package com.gongyuan.netty.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author by TaoWangwang
 * @classname HttpServerInitializer
 * @description TODO
 * @date 2020/9/17 16:55
 */
public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //解码器
        pipeline.addLast(new IdleStateHandler(5,7,3, TimeUnit.SECONDS));        //添加自定义的ChannelHandler
        pipeline.addLast("myHandler", new MyServerHandler());
    }
}
