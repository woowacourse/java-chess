package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.PieceType;
import chess.domain.piece.exception.IllegalPieceMoveException;
import java.util.List;

public class EmptyState implements MoveState {

    private static final PieceType pieceType = PieceType.EMPTY;
    private static final EmptyState instance = new EmptyState();

    private EmptyState() {
    }

    public static EmptyState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        throw new IllegalPieceMoveException();
    }

    @Override
    public MoveState getNextState() {
        throw new IllegalPieceMoveException();
    }

    @Override
    public double getScore(List<MoveState> sameFileColorPiecesState) {
        return pieceType.getScore();
    }
}
