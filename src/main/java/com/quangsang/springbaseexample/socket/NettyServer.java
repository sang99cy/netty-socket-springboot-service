package com.quangsang.springbaseexample.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.cert.CertificateException;

@Component
@Slf4j
public class NettyServer {
    private static Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Value("${port.socket.server}")
    private Integer port = 8888;

//    private boolean itRun = false;
//
//    public boolean inInt() {
//        return this.itRun;
//    }
//
//    public boolean isItRun() {
//        return itRun;
//    }
//
//    public void setItRun(boolean itRun) {
//        this.itRun = itRun;
//    }

    @Bean
    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            sb.group(bossGroup, group) //  Binding thread pool
                    .channel(NioServerSocketChannel.class) //  Specifies the use of Channel
                    .localAddress(this.port)//  Binding listening port
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() { //  Trigger actions when binding client connections

                        @Override
                        protected void initChannel(SocketChannel ch) throws CertificateException {
                            logger.info("Receive new client connection: {}", ch.toString());
                            //The WebSocket protocol itself is based on the HTTP protocol, so this side should also use the HTTP interpolation.
                            ch.pipeline().addLast(new HttpServerCodec());
                            //Written by block
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            ch.pipeline().addLast(new HttpObjectAggregator(8192));
                            ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", "WebSocket", true, 65536 * 10));
                            ch.pipeline().addLast(new NettySocketHandler());
                        }
                    });
            ChannelFuture cf = sb.bind(port).sync(); //  Server asynchronously creates binding
            System.out.println("Starting is listening:" + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); //  Close server channel
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                group.shutdownGracefully(); //  Release thread pool resources
                bossGroup.shutdownGracefully();
            } catch (Exception e) {
                log.info(e.getMessage());
            }

        }
    }
}
