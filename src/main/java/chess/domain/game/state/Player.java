package chess.domain.game.state;

import java.util.Arrays;

import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

public enum Player {
    WHITE(Color.WHITE),
    BLACK(Color.BLACK)
    ;

    private final Color color;

    Player(Color color) {
        this.color = color;
    }

    public boolean isMyPiece(Piece piece) {
        return piece.isSameColor(this.color);
    }

    public Player change() {
        return Arrays.stream(Player.values())
            .filter(player -> player.color != this.color)
            .findFirst()
            .get();
    }
}
