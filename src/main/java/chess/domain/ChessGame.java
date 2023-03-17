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

    private final PiecesPosition piecesPosition;
    private Camp turnCamp = Camp.WHITE;

    public ChessGame(PiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    public void move(Position fromPosition, Position toPosition) {
        Piece fromPiece = piecesPosition.choicePiece(fromPosition);
        Piece toPiece = piecesPosition.choicePiece(toPosition);
        validateBeforeMoveTo(fromPiece, toPiece);

        PieceMove pieceMove = getPieceMove(fromPosition, toPosition);

        validateMovable(toPiece, pieceMove);
        piecesPosition.movePieceOn(fromPosition, toPosition);
        changeTurn();
    }

    private void validateBeforeMoveTo(Piece fromPiece, Piece toPiece) {
        validateFromPiece(fromPiece);
        validateSameCamp(fromPiece, toPiece);
    }

    private void validateFromPiece(Piece fromPiece) {
        if (fromPiece.isBlack() && turnCamp != Camp.BLACK
                || !fromPiece.isBlack() && turnCamp == Camp.BLACK) {
            throw new IllegalArgumentException(TURN_MISMATCHED);
        }

        if (fromPiece.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CHOICE);
        }
    }

    private void validateSameCamp(Piece fromPiece, Piece toPiece) {
        if (fromPiece.isSameCamp(toPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private PieceMove getPieceMove(Position fromPosition, Position toPosition) {
        Piece fromPiece = piecesPosition.choicePiece(fromPosition);
        PieceMove pieceMove = fromPiece.getMovement(fromPosition, toPosition);

        List<Position> pathPositions = fromPosition.getBetweenPositions(toPosition);
        for (Position position : pathPositions) {
            validateMovableBetween(pieceMove, position);
        }

        return pieceMove;
    }

    private void validateMovable(Piece toPiece, PieceMove pieceMove) {
        if (!pieceMove.isMovable(toPiece, true)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void validateMovableBetween(PieceMove pieceMove, Position position) {
        Piece betweenPiece = piecesPosition.choicePiece(position);
        if (!pieceMove.isMovable(betweenPiece, false)) {
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

