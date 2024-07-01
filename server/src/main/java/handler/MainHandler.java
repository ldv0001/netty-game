package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import model.GameBoard;
import model.Player;
import service.GameService;

import java.util.ArrayList;
import java.util.List;

public class MainHandler extends SimpleChannelInboundHandler {

    static GameBoard board = new GameBoard();

    static List<Player>list = new ArrayList<>();

    GameService gameService = new GameService();

    static int counter=1;

    static byte playerPointer=0;

    static byte movesCounter=0;

    String msg;

    Player player;

    boolean result;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {

        if(counter<3){
            broadcastMessage("waiting for 2 players\n");
            return;
        }
        if  (gameService.verifyInput(o)){
            msg = gameService.messageConverter(o);
        }else {
            player.getChannel().writeAndFlush("wrong input\n");
            return;
        }
        player = list.get(playerPointer);
        if (player.getId().equals(ctx.channel().id())) {
            if(gameService.boardPositionIsFree(board.getState(),msg)){
                board.setSymbol(msg, list.get(playerPointer).getSymbol());
                movesCounter++;
            }else {
                player.getChannel().writeAndFlush("this position is already taken\n");
                return;
            }
            broadcastMessage(board.getBoard());

            playerPointer = gameService.switchPlayerPointer(playerPointer);

        }else{
            broadcastMessage("it "+player.getPlayerName()+"'s turn!\n");
        }
        if (movesCounter>4){
            result=gameService.isThereAWinner(board.getState(),player.getSymbol());
            if(result){
                broadcastMessage(player.getPlayerName()+" is a winner!\n");
                broadcastMessage("game over\n");
                ctx.channel().parent().eventLoop().shutdownGracefully();
            }
        }
        if(movesCounter==9){

            broadcastMessage("it's a draw!\n");
            broadcastMessage("game over\n");
            ctx.channel().parent().eventLoop().shutdownGracefully();
        }
        broadcastMessage("player"+(playerPointer+1)+"'s turn:\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        if(counter<3) {
            Player player = new Player(counter,ctx.channel(),ctx.channel().id());
            list.add(player);
            ctx.channel().writeAndFlush(player.getPlayerName()+" has joined\n");
            if (counter==2){
                broadcastMessage(gameService.startScreen());
            }
            counter++;

        }else{
            ctx.close();
        }

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        if(counter<3) {
            ctx.channel().parent().eventLoop().shutdownGracefully();
        }
        System.out.println(ctx.channel()+" is inactive");

    }

    void broadcastMessage(String message){
        for (Player player: list) {
            player.getChannel().writeAndFlush(message);
        }
    }
}
