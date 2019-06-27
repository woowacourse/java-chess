package chess.domain.piece;

import chess.domain.path.Path;
import chess.domain.path.PathFactory;
import chess.domain.path.QueenPath;

public class Queen extends Piece {
    private static final double SCORE = 9;

    private Queen(PieceColor color, Path path) {
        super(color, path, PieceType.QUEEN);
    }

    public static Queen whiteCreate() {
        return new Queen(PieceColor.WHITE, PathFactory.QUEEN.create());
    }

    public static Queen blackCreate() {
        return new Queen(PieceColor.BLACK, PathFactory.QUEEN.create());
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
