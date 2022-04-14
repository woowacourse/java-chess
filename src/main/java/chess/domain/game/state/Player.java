package chess.domain.game.state;

import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Player {
    WHITE(Color.WHITE),
    BLACK(Color.BLACK)
    ;

    private final Color color;

    Player(Color color) {
        this.color = color;
    }

    public static Player of(Color color) {
        return Arrays.stream(Player.values())
                .filter(player -> player.color == color)
                .findFirst()
                .orElseThrow();
    }

    public boolean isMyPiece(Piece piece) {
        return piece.isSameColor(this.color);
    }

    public Player change() {
        return Arrays.stream(Player.values())
            .filter(player -> player.color != this.color)
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("턴을 바꿀 수 없습니다."));
    }
}
