package chess.domain.piece;

import chess.domain.color.Color;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.score.Score;
import chess.domain.state.ChessState;
import java.util.Map;
import java.util.Set;

public abstract class Piece {
    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract Set<Position> findPath(Positions positions);

    public abstract ChessState state(Map<Position, Piece> board);

    public abstract boolean isBlank();

    public abstract PieceType pieceType();

    public abstract Score score();

    public final boolean isTypeOf(Set<PieceType> type) {
        return type.contains(pieceType());
    }

    public final boolean isSameColor(Color otherColor) {
        return color == otherColor;
    }

    public final boolean isOppositeColor(Piece other) {
        return this.color.findOppositeColor() == other.color;
    }

    public final Color color() {
        return color;
    }
}
