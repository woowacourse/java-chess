package chess.domain.player;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import java.util.Objects;

public class Player {

    private final PieceColor color;

    public Player(PieceColor color) {
        this.color = color;
    }

    public static Player of(PieceColor color) {
        return new Player(color);
    }

    public boolean isOwnerOf(Piece piece) {
        return piece.hasColor(color);
    }

    public boolean isColor(PieceColor color) {
        return this.color.equals(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return color == player.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
