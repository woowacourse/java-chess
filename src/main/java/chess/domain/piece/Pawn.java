package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.Vectors;
import chess.domain.board.YPosition;
import chess.domain.path.BlackPawnPath;
import chess.domain.path.Path;
import chess.domain.path.PathFactory;
import chess.domain.path.WhitePawnPath;

import java.util.Set;
import java.util.stream.Collectors;

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

        if (!source.isSameY(new YPosition("2")) && getColor().equals(PieceColor.WHITE)) {
            return movableArea.removeSource(source.moveUp(2));
        }

        if (!source.isSameY(new YPosition("7")) && getColor().equals(PieceColor.BLACK)) {
            return movableArea.removeSource(source.moveDown(2));
        }

        return movableArea;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
