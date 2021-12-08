package example5.websockethandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String socketUri;

    public  HttpRequestHandler(String uri){
        socketUri =uri;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        if(fullHttpRequest.uri().equalsIgnoreCase(socketUri)){
            channelHandlerContext.fireChannelRead(fullHttpRequest.retain());
        }
    }
}
