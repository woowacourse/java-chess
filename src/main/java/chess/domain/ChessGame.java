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
    public static final String SAME_POSITION = "출발 지점과 도착 지점은 동일할 수 없습니다";

    private final ChessBoard chessBoard;
    private Camp turnCamp;

    public ChessGame(ChessBoard chessBoard, Camp turnCamp) {
        this.turnCamp = turnCamp;
        this.chessBoard = chessBoard;
    }

    public void move(Position fromPosition, Position toPosition) {
        validateBeforeMoveTo(fromPosition, toPosition, turnCamp);

        PieceMove pieceMove = getPieceMove(fromPosition, toPosition);

        validateMovable(toPosition, pieceMove,true);
        chessBoard.movePieceOn(fromPosition, toPosition);
        changeTurn();
    }

    private PieceMove getPieceMove(Position fromPosition, Position toPosition) {
        Piece fromPiece = chessBoard.choicePiece(fromPosition);
        PieceMove pieceMove = fromPiece.getMovement(fromPosition, toPosition);

        List<Position> pathPositions = fromPosition.getBetweenPositions(toPosition);
        for (Position position : pathPositions) {
            validateMovable(position, pieceMove, false);
        }

        return pieceMove;
    }

    public void validateBeforeMoveTo(Position fromPosition, Position toPosition, Camp turnCamp) {
        validateFromPiece(chessBoard.choicePiece(fromPosition), turnCamp);
        validateSameCamp(chessBoard.choicePiece(fromPosition), chessBoard.choicePiece(toPosition));
        validateEqualPosition(fromPosition, toPosition);
    }

    private void validateFromPiece(Piece fromPiece,Camp turnCamp) {
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

    public void validateMovable(Position position, PieceMove pieceMove,boolean lastPiece) {
        if (!pieceMove.isMovable(chessBoard.choicePiece(position), lastPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE);
        }
    }

    private void changeTurn() {
        this.turnCamp = Camp.convert(turnCamp);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}

