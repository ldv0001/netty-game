package model;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

public class Player {

    String playerName;
    Symbol symbol;
    Channel channel;

    ChannelId id;

    public ChannelId getId() {
        return id;
    }

    public Player(int playerNumber,Channel channel, ChannelId id) {
        this.playerName = "player"+playerNumber;
        if(playerNumber==1){
            this.symbol=Symbol.Cross;
        }else {
            this.symbol=Symbol.Circle;
        }
        this.id = id;
        this.channel=channel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Channel getChannel() {
        return channel;
    }
}
