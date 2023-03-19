package chess.domain;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.move.PieceMove;

import java.util.List;
import java.util.Map;

public final class ChessGame {

    private static final String UNABLE_TO_MOVE = "이동할 수 없습니다.";
    private static final String TURN_MISMATCHED = "다른 진영의 기물을 선택할 수 없습니다.";
    private static final String EMPTY_CHOICE = "빈 칸은 선택할 수 없습니다.";
    public static final String SAME_POSITION = "출발 지점과 도착 지점은 동일할 수 없습니다";

    private final Map<Position, Piece> piecesPosition;
    private Camp turnCamp;

    public ChessGame(Map<Position, Piece> piecesPosition, Camp turnCamp) {
        this.turnCamp = turnCamp;
        this.piecesPosition = piecesPosition;
    }

    public void move(Position fromPosition, Position toPosition) {
        validateBeforeMoveTo(fromPosition, toPosition);

        PieceMove pieceMove = getPieceMove(fromPosition, toPosition);

        validateMovable(toPosition, pieceMove);
        movePieceOn(fromPosition, toPosition);
        changeTurn();
    }

    private void validateBeforeMoveTo(Position fromPosition, Position toPosition) {
        validateFromPiece(piecesPosition.get(fromPosition));
        validateSameCamp(piecesPosition.get(fromPosition), piecesPosition.get(toPosition));
        validateEqualPosition(fromPosition, toPosition);
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

    private void validateEqualPosition(Position fromPosition, Position toPosition) {
        if (fromPosition.isSamePosition(toPosition)) {
            throw new IllegalArgumentException(SAME_POSITION);
        }
    }

    private PieceMove getPieceMove(Position fromPosition, Position toPosition) {
        Piece fromPiece = piecesPosition.get(fromPosition);
        PieceMove pieceMove = fromPiece.getMovement(fromPosition, toPosition);

        List<Position> pathPositions = fromPosition.getBetweenPositions(toPosition);
        for (Position position : pathPositions) {
            validateMovableBetween(pieceMove, position);
        }

        return pieceMove;
    }

    private void validateMovable(Position toPosition, PieceMove pieceMove) {
        if (!pieceMove.isMovable(piecesPosition.get(toPosition), true)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void validateMovableBetween(PieceMove pieceMove, Position position) {
        Piece betweenPiece = piecesPosition.get(position);
        if (!pieceMove.isMovable(betweenPiece, false)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void movePieceOn(Position fromPosition, Position toPosition) {
        piecesPosition.put(toPosition, piecesPosition.get(fromPosition));
        piecesPosition.put(fromPosition, new Empty());
    }

    private void changeTurn() {
        this.turnCamp = Camp.convert(turnCamp);
    }

    public Map<Position, Piece> getPiecesPosition() {
        return piecesPosition;
    }
}

