package service;

import model.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameService {

    public boolean verifyInput(Object o){
        String msg = (String)o;
        char ch  = msg.charAt(0);
        if(ch>=48&&ch<=57){
            return true;
        }else {
            return false;
        }
    }

    public String messageConverter(Object o){
        String msg = (String)o;
        msg = msg.substring(0,1);
        return msg;
    }

    public boolean boardPositionIsFree(HashMap<String,Symbol>state,String msg){

        if (state.get(msg)==Symbol.Space){
            return true;
        }else {
            return false;
        }
    }

    public byte switchPlayerPointer(byte pointer){
        if (pointer==0){
            pointer++;
        }else {
            pointer--;
        }
        return pointer;
    }

    public boolean isThereAWinner(HashMap<String,Symbol>state,Symbol symbol){

        int counter=1;
        List<Symbol>list = new ArrayList<>();
        //horizontal check
        for(int i=1;i<10;i++){
            list.add(state.get(String.valueOf(i)));
            counter++;
            if(counter>=4){
                boolean b= list.stream().allMatch(x->x.equals(symbol));
                if(b){
                    return true;
                }else{
                    list.clear();
                    counter=1;
                }
            }
        }

        // vertical check
        for(int j=1;j<4;j++) {
            for (int i = j; i < 10; i += 3) {
                list.add(state.get(String.valueOf(i)));
                counter++;
                if (counter >= 4) {
                    boolean b = list.stream().allMatch(x -> x.equals(symbol));
                    if (b) {
                        return true;
                    } else {
                        list.clear();
                        counter = 1;
                    }
                }
            }
        }
        // diagonal 1 check
        for (int i = 1; i < 10; i += 4) {
            list.add(state.get(String.valueOf(i)));
            counter++;
            if (counter >= 4) {
                boolean b = list.stream().allMatch(x -> x.equals(symbol));
                if (b) {
                    return true;
                }else {
                    list.clear();
                    counter = 1;
                }
            }
        }
        //diagonal 2 check
        for (int i = 3; i <8; i +=2) {
            list.add(state.get(String.valueOf(i)));
            counter++;
            if (counter >= 4) {
                boolean b = list.stream().allMatch(x -> x.equals(symbol));
                if (b) {
                    return true;
                }
            }
        }
        return false;
    }

    public String startScreen(){
        String screen ="Enter the number where you want do display X or O\n\n"+
                " 1 | 2 | 3 \n"+
                "---+---+---\n"+
                " 4 | 5 | 6 \n"+
                "---+---+---\n"+
                " 7 | 8 | 9 \n";
        return screen;

    }



}
