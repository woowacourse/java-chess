package chess.domain;

import java.util.Arrays;
import java.util.List;

public class Player {

    private final String color;
    private final Pieces pieces;

    private Player(final String color, final Pieces pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public static Player fromWhitePlayer(final Pieces pieces) {
        return new Player("white", pieces);
    }

    public static Player fromBlackPlayer(final Pieces pieces) {
        return new Player("black", pieces);
    }

    @Override
    public String toString() {
        return "Player{" +
                "color='" + color + '\'' +
                ", pieces=" + pieces +
                '}';
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }

    public String getColor() {
        return color;
    }
}
