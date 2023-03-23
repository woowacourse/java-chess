package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.position.move.PieceMove;

public final class ChessGame {

    private static final String UNABLE_TO_MOVE_ERROR_MESSAGE = "이동할 수 없습니다.";
    private static final String TURN_MISMATCHED_ERROR_MESSAGE = "다른 진영의 기물을 선택할 수 없습니다.";
    private static final String EMPTY_CHOICE_ERROR_MESSAGE = "빈 칸은 선택할 수 없습니다.";
    public static final String SAME_POSITION_ERROR_MESSAGE = "출발 지점과 도착 지점은 동일할 수 없습니다";
    public static final int KING_COUNT = 2;

    private final ChessBoard chessBoard;
    private Turn turnCamp;

    public ChessGame(ChessBoard chessBoard, Turn turnCamp) {
        this.turnCamp = turnCamp;
        this.chessBoard = chessBoard;
    }

    public void move(Position fromPosition, Position toPosition) {
        validateBeforeMoveTo(fromPosition, toPosition);
        PieceMove pieceMove = getPieceMove(fromPosition, toPosition);

        Path path = new Path();
        path.judgeBetweenStuck(
                chessBoard.choiceBetweenPiece(path.getBetweenPositions(fromPosition, toPosition)),
                pieceMove);
        validateLastMovable(chessBoard.choicePiece(toPosition), pieceMove, true);

        chessBoard.movePieceOn(fromPosition, toPosition);
        changeTurn();
    }

    private PieceMove getPieceMove(Position fromPosition, Position toPosition) {
        Piece fromPiece = chessBoard.choicePiece(fromPosition);

        return fromPiece.getMovement(fromPosition, toPosition);
    }

    public void validateBeforeMoveTo(Position fromPosition, Position toPosition) {
        validatePickExistPiece(chessBoard.choicePiece(fromPosition));
        validateTurnCamp(chessBoard.choicePiece(fromPosition));
        validateSameCamp(chessBoard.choicePiece(fromPosition), chessBoard.choicePiece(toPosition));
        validateEqualPosition(fromPosition, toPosition);
    }

    private void validateTurnCamp(Piece fromPiece) {
        if (turnCamp.isMyTurn(fromPiece)) {
            throw new IllegalArgumentException(TURN_MISMATCHED_ERROR_MESSAGE);
        }
    }

    private void validatePickExistPiece(Piece fromPiece) {
        if (fromPiece.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CHOICE_ERROR_MESSAGE);
        }
    }

    private void validateSameCamp(Piece fromPiece, Piece toPiece) {
        if (fromPiece.isEmpty()) {
            return;
        }
        if (fromPiece.isSameCamp(toPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE_ERROR_MESSAGE);
        }
    }

    private void validateEqualPosition(Position fromPosition, Position toPosition) {
        if (fromPosition.equals(toPosition)) {
            throw new IllegalArgumentException(SAME_POSITION_ERROR_MESSAGE);
        }
    }

    public void validateLastMovable(Piece piece, PieceMove pieceMove, boolean lastPiece) {
        if (!pieceMove.isMovable(piece, lastPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE_ERROR_MESSAGE);
        }
    }

    public boolean isKingLive() {
        return chessBoard.inKingLive() == KING_COUNT;
    }

    private void changeTurn() {
        this.turnCamp = turnCamp.convert(turnCamp);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}

