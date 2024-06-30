package model;

import java.util.HashMap;

public class GameBoard {
    String board;

    Symbol symbol = Symbol.Space;
    HashMap<String,Symbol> state = new HashMap<>();

    public GameBoard(){
        for(int i = 1;i<10;i++) {
            state.put(Integer.toString(i), symbol);
        }
        initializeBoard();
    }

    public void setSymbol(String str,Symbol symbol){
        state.put(str,symbol);
    }

    public Symbol getSymbol(Symbol symbol){
        return state.get(symbol.getCharacter());
    }


    public String getBoard(){
        initializeBoard();
        return board+"\n";
    }


    public void initializeBoard() {
            board = String.format(" %s | %s | %s \n" +
                                  "---+---+---\n" +
                                  " %s | %s | %s \n" +
                                  "---+---+---\n" +
                                  " %s | %s | %s \n",state.get("1").getCharacter()
                                                    ,state.get("2").getCharacter()
                                                    ,state.get("3").getCharacter()
                                                    ,state.get("4").getCharacter()
                                                    ,state.get("5").getCharacter()
                                                    ,state.get("6").getCharacter()
                                                    ,state.get("7").getCharacter()
                                                    ,state.get("8").getCharacter()
                                                    ,state.get("9").getCharacter());
        }
    public HashMap<String,Symbol>getState(){
        return state;
    }
}
