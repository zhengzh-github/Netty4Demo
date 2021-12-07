package example3.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class EchoInboundHandler1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("进入 EchoInboundHandler1.channelRead");

        String data = ((ByteBuf)msg).toString(CharsetUtil.UTF_8);
        System.out.println("EchoInboundHandler1.channelRead 收到数据：" + data);
        //ctx.fireChannelRead(Unpooled.copiedBuffer("[EchoInboundHandler1] " + data, CharsetUtil.UTF_8));
        ctx.fireChannelRead(msg);
        System.out.println("退出 EchoInboundHandler1 channelRead");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoInboundHandler1 channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("EchoInboundHandler1 exceptionCaught:"+cause.toString());
    }
}
