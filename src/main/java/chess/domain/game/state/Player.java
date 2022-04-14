package chess.domain.game.state;

import java.util.Arrays;

import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

public enum Player {
    White(Color.White),
    Black(Color.Black)
    ;

    private final Color color;

    Player(Color color) {
        this.color = color;
    }

    public static Player getName(String name) {
        if (name.equals(White.name())) {
            return White;
        }

        return Black;
    }

    public boolean hasPiece(Piece piece) {
        return piece.isSameColor(this.color);
    }

    public Player change() {
        return Arrays.stream(Player.values())
            .filter(player -> player.color != this.color)
            .findFirst()
            .get();
    }
}
