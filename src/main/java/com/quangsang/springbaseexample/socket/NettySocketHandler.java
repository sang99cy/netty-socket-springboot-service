package com.quangsang.springbaseexample.socket;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NettySocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Gson gson = new Gson();
    private static Logger logger = LoggerFactory.getLogger(NettySocketHandler.class);
    public static ChannelGroup channelGroup = new DefaultChannelGroup(null);
    public static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();
    public static Channel channelBackup;


    private final String ERROR_CONNECT_SERVER = "{\"op\":\"ERROR\",\"data\":{\"message\":\"connect to server zeppelin fail\"}}";

    /**
     * Khi có connect mới thêm connect vào group
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    /**
     * khi connect bị disconnected -> xóa khỏi group
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.remove(ctx.channel());
        //Remove the currently leaving client from the server's ChannelMap
        Collection<Channel> col = channelMap.values();
        while (true == col.contains(ctx.channel())) {
            col.remove(ctx.channel());
            logger.info("Netty client connection is successful!");
        }
    }

    /**
     * Được gọi khi mởi connect
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Netty is established with the client, and the channel is open!");
    }


    /**
     * Nhận message từ client
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //System.out.println(msg.text());
        Message receivedMessage = deserializeMessage(msg.text());
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
            ChannelFuture f = ctx.channel().writeAndFlush(new TextWebSocketFrame("(gameID=0)[LiveOthelloServer=" + 1 + "]\n"));
        }
    }


    /**
     * Calls when the IO of the server is thrown
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("Netty and the client are disconnected, the channel is turned off!");
        if (channelGroup.contains(ctx)) {
            channelGroup.remove(ctx.channel());
        }
    }

    /**
     * parser message String to json
     */
    protected Message deserializeMessage(String msg) {
        return gson.fromJson(msg, Message.class);
    }
}
