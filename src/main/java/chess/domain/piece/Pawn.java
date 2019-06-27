package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Square;
import chess.domain.board.Vectors;
import chess.domain.path.Path;
import chess.domain.path.PathFactory;

public class Pawn extends Piece {
    private static final double SCORE = 1;

    private Pawn(PieceColor color, Path path) {
        super(color, path, PieceType.PAWN);
    }

    public static Pawn whiteCreate() {
        return new Pawn(PieceColor.WHITE, PathFactory.WHITE_PAWN.create());
    }

    public static Pawn blackCreate() {
        return new Pawn(PieceColor.BLACK, PathFactory.BLACK_PAWN.create());
    }

    @Override
    public Vectors movableArea(Square source) {
        Vectors movableArea =  super.movableArea(source);

        if (!source.isSameY(new Position(2)) && isWhite()) {
            return movableArea.removeSource(source.moveUp(2));
        }

        if (!source.isSameY(new Position(7)) && isBlack()) {
            return movableArea.removeSource(source.moveDown(2));
        }

        return movableArea;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    public boolean isWhite() {
        return super.isSameColor(PieceColor.WHITE);
    }

    public boolean isBlack() {
        return super.isSameColor(PieceColor.BLACK);
    }
}
