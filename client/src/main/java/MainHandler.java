import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        String msg = (String)o;
        System.out.println(msg);
        if (msg.contains("player1")||(msg.contains("waiting"))) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String response = reader.readLine();
            ctx.channel().writeAndFlush(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
