package example3.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class EchoOutboundHandler1 extends ChannelOutboundHandlerAdapter {
    private static final   Logger logger = LoggerFactory.getLogger(EchoOutboundHandler1.class);
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("进入 EchoOutboundHandler1.write");
        //logger.info("########################################");
        String data = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
        ByteBuf byteBuf = Unpooled.copiedBuffer("[EchoOutboundHandler1]", CharsetUtil.UTF_8);
        String currentTime = new Date(System.currentTimeMillis()).toString();
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());


        //ctx.writeAndFlush(Unpooled.copiedBuffer("[EchoOutboundHandler1]", CharsetUtil.UTF_8));
        //ctx.writeAndFlush(msg);
        //ctx.channel().writeAndFlush(Unpooled.copiedBuffer("在OutboundHandler里测试一下channel().writeAndFlush", CharsetUtil.UTF_8));
        ctx.write(byteBuf);
        //ctx.flush();
        //需要执行下一个
       // super.write(ctx, msg, promise);
        System.out.println("退出 EchoOutboundHandler1.write");

    }
}
