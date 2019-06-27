package chess.domain.piece;

import chess.domain.path.Path;
import chess.domain.path.PathFactory;
import chess.domain.path.RookPath;

public class Rook extends Piece {
    private static final double SCORE = 5;

    private Rook(PieceColor color, Path path) {
        super(color, path, PieceType.ROOK);
    }

    public static Rook whiteCreate() {
        return new Rook(PieceColor.WHITE, PathFactory.ROOK.create());
    }

    public static Rook blackCreate() {
        return new Rook(PieceColor.BLACK, PathFactory.ROOK.create());
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
