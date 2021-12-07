package example3.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

public class EchoOutboundHandler2 extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("进入 EchoOutboundHandler2.write");
        String str = ((ByteBuf)msg).toString(CharsetUtil.UTF_8);
        ByteBuf byteBuf = Unpooled.copiedBuffer("EchoOutboundHandler2"+str, CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
//        ctx.write(byteBuf);
//        ctx.flush();
        //super.write(ctx, msg, promise);
        System.out.println("退出 EchoOutboundHandler2.write");
    }
}
