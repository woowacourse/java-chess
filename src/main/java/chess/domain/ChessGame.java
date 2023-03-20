package chess.domain;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.move.PieceMove;
import java.util.List;

public final class ChessGame {

    private static final String UNABLE_TO_MOVE = "이동할 수 없습니다.";
    private static final String TURN_MISMATCHED = "다른 진영의 기물을 선택할 수 없습니다.";
    private static final String EMPTY_CHOICE = "빈 칸은 선택할 수 없습니다.";
    private static final String UNABLE_TO_EQUAL_POSITION = "출발 지점과 도착 지점은 동일할 수 없습니다";

    private final PiecesPosition piecesPosition;
    private Camp turnCamp = Camp.WHITE;

    public ChessGame(PiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    public void move(Position fromPosition, Position toPosition) {
        validateBeforeMove(fromPosition, toPosition);
        PieceMove pieceMove = getPieceMove(fromPosition, toPosition);

        validateMovable(piecesPosition.isPieceExist(toPosition), pieceMove);
        piecesPosition.movePieceBy(fromPosition, toPosition);
        changeTurn();
    }

    private void validateBeforeMove(Position fromPosition, Position toPosition) {
        validateEqualPosition(fromPosition, toPosition);
        validatePickExistPiece(fromPosition);
        validateNotMoveToSameCampPiece(fromPosition, toPosition);
        validateTurn(fromPosition);
    }

    private void validateEqualPosition(Position fromPosition, Position toPosition) {
        if (fromPosition.equals(toPosition)) {
            throw new IllegalArgumentException(UNABLE_TO_EQUAL_POSITION);
        }
    }

    private void validatePickExistPiece(Position fromPosition) {
        if (!piecesPosition.isPieceExist(fromPosition)) {
            throw new IllegalArgumentException(EMPTY_CHOICE);
        }
    }

    private void validateNotMoveToSameCampPiece(Position fromPosition, Position toPosition) {
        if (!piecesPosition.isPieceExist(toPosition)) {
            return;
        }

        Piece fromPiece = piecesPosition.peekPiece(fromPosition);
        Piece toPiece = piecesPosition.peekPiece(toPosition);
        if (fromPiece.isSameCamp(toPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void validateTurn(Position fromPosition) {
        Piece fromPiece = piecesPosition.peekPiece(fromPosition);

        if (fromPiece.isMismatchedCamp(turnCamp)) {
            throw new IllegalArgumentException(TURN_MISMATCHED);
        }
    }

    private PieceMove getPieceMove(Position fromPosition, Position toPosition) {
        Piece fromPiece = piecesPosition.peekPiece(fromPosition);
        PieceMove pieceMove = fromPiece.getMovement(fromPosition, toPosition);

        List<Position> pathPositions = fromPosition.getBetweenPositions(toPosition);
        for (Position position : pathPositions) {
            validateMovableBetween(pieceMove, position);
        }

        return pieceMove;
    }

    private void validateMovableBetween(PieceMove pieceMove, Position position) {
        boolean isEmpty = piecesPosition.isPieceExist(position);
        if (!pieceMove.isMovable(isEmpty, false)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void validateMovable(boolean isEmpty, PieceMove pieceMove) {
        if (!pieceMove.isMovable(isEmpty, true)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void changeTurn() {
        this.turnCamp = Camp.convert(turnCamp);
    }

    public PiecesPosition getPiecesPosition() {
        return piecesPosition;
    }
}

