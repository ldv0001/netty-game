import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MainApplication {


    public static void main(String[] args)  {
        final String host="localhost";
        final int port =9000;
        System.out.println("start");

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        CustomChannelInitializer channelInitializer = new CustomChannelInitializer();

        try{
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b .handler(channelInitializer);
            b .option(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture future = b.connect(host,port).sync();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            System.out.println("client shutdown");
        }

    }
}
