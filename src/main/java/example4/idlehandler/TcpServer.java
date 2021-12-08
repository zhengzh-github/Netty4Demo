package example4.idlehandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class TcpServer {
    private static final int port=8080;
    public static void main(String[] args) throws InterruptedException {
        StartServer();
    }

    private  static void StartServer() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap b= new ServerBootstrap();
        b.group(bossGroup,workGroup).
                channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new IdleStateHandler(0, 0, 10, TimeUnit.SECONDS));
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new HeartBeatServerHandler());
                    }
                }
            );

        ChannelFuture channelFuture = b.bind(port).sync();
        System.out.println("服务器绑定端口" + port);

        channelFuture.channel().closeFuture().sync();
        System.out.println("服务器已关闭.");

    }
}
