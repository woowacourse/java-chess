package chess.domain.piece;

import static chess.domain.chessboard.attribute.File.D;
import static chess.domain.chessboard.attribute.Rank.EIGHT;
import static chess.domain.chessboard.attribute.Rank.ONE;

import java.util.Set;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public class Queen extends SlidingPiece {

    private static final Position WHITE_INITIAL_POSITION = Position.from("d1");
    private static final Position BLACK_INITIAL_POSITION = Position.from("d8");

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

    public static Piece ofInitialPosition(final Color color) {
        if (color.isBlack()) {
            return new Queen(color, BLACK_INITIAL_POSITION);
        }
        return new Queen(color, WHITE_INITIAL_POSITION);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
