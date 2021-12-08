package example5.websockethandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TcpWebSocketServer {
    private static int port=8080;
    public static void main(String[] args) throws InterruptedException {
        StartServer();
    }

    private  static void StartServer() throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 64*1024)
                // 日志监听
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new WebSocketChannelInitializer());

        //logger.info("webSocket服务器启动成功："+channel);
        System.out.println("webSocket服务器启动成功...");
        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
//        System.out.println("服务器绑定端口" + port);

        channelFuture.channel().closeFuture().sync();
        System.out.println("服务器已关闭.");

    }
}
