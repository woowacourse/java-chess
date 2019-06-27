package chess.domain.piece;

import chess.domain.path.BishopPath;
import chess.domain.path.Path;
import chess.domain.path.PathFactory;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    private Bishop(PieceColor color, Path path) {
        super(color, path, PieceType.BISHOP);
    }

    public static Bishop whiteCreate() {
        return new Bishop(PieceColor.WHITE, PathFactory.BISHOP.create());
    }

    public static Bishop blackCreate() {
        return new Bishop(PieceColor.BLACK, PathFactory.BISHOP.create());
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
