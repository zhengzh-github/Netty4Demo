package example5.websockethandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChannelSupervise {
    private static ChannelGroup GlobalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static ConcurrentMap<String, ChannelId> ChannelIdMap = new ConcurrentHashMap();

    public  static void addChannel(Channel channel){
        GlobalGroup.add(channel);
        ChannelIdMap.put(channel.id().asShortText(),channel.id());
    }

    public  static void removeChannel(Channel channel){
        GlobalGroup.remove(channel);
        ChannelIdMap.remove(channel.id().asShortText());

    }

    public static  Channel findChannel(String id){
        return GlobalGroup.find(ChannelIdMap.get(id));
    }


    public static void send2All(TextWebSocketFrame tws){
        GlobalGroup.writeAndFlush(tws);
    }

}
