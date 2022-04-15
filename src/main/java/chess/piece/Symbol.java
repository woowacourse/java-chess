package chess.piece;

import chess.game.Player;

public enum Symbol {

    PAWN("pawn", 'p'),
    KNIGHT("knight", 'n'),
    BISHOP("bishop", 'b'),
    ROOK("rook", 'r'),
    QUEEN("queen", 'q'),
    KING("king", 'k'),
    BLANK("blank", '.')
    ;

    private final String name;
    private final char symbol;

    Symbol(final String name, char symbol) {
        this.name = name;
        this.symbol =  symbol;
    }

    public String getImageName(final Player player) {
        if (player.equals(Player.WHITE)) {
            return "white_" + name;
        }
        if (player.equals(Player.BLACK)) {
            return "black_" + name;
        }
        return name;
    }

    public Character getSymbol(final Player player) {
        if (player.equals(Player.WHITE)) {
            return symbol;
        }
        if (player.equals(Player.BLACK)) {
            return Character.toUpperCase(symbol);
        }
        return symbol;
    }
}
