package model;

public enum Symbol {
    Cross("X"), Circle("O"), Space(" ");

    String character;

    Symbol(String ch){
        this.character =ch;
    }
    public String getCharacter() {
        return character;
    }

}
