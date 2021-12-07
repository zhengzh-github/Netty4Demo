package example4.idlehandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("5秒内未收到数据...");
        if(evt instanceof IdleStateEvent)
        {
            IdleStateEvent idleStateEvent = (IdleStateEvent)evt;
            if(idleStateEvent.state() == IdleState.READER_IDLE)
            {
                System.out.println("该客户端连接已关闭...");
                ctx.channel().close();

            }
        }else
        {
            super.userEventTriggered(ctx, evt);
        }

    }
}
