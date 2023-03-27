package chess.domain;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.position.move.PieceMove;

public final class ChessGame {

    private static final String UNABLE_TO_MOVE_ERROR_MESSAGE = "이동할 수 없습니다.";
    private static final String TURN_MISMATCHED_ERROR_MESSAGE = "다른 진영의 기물을 선택할 수 없습니다.";
    private static final String EMPTY_CHOICE_ERROR_MESSAGE = "빈 칸은 선택할 수 없습니다.";
    public static final String SAME_POSITION_ERROR_MESSAGE = "출발 지점과 도착 지점은 동일할 수 없습니다";

    private final ChessBoard chessBoard;
    private Turn turnCamp;

    public ChessGame(final ChessBoard chessBoard, final Turn turnCamp) {
        this.turnCamp = turnCamp;
        this.chessBoard = chessBoard;
    }

    public void move(final Position fromPosition, final Position toPosition) {
        validateBeforeMoveTo(fromPosition, toPosition);
        PieceMove pieceMove = getPieceMove(fromPosition, toPosition);

        Path path = new Path();
        path.judgeBetweenStuck(
                chessBoard.choiceBetweenPiece(path.getBetweenPositions(fromPosition, toPosition)),
                pieceMove);
        validateLastMovable(chessBoard.choosePiece(toPosition), pieceMove, true);

        chessBoard.movePieceOn(fromPosition, toPosition);
        changeTurn();
    }

    private PieceMove getPieceMove(final Position fromPosition, final Position toPosition) {
        Piece fromPiece = chessBoard.choosePiece(fromPosition);

        return fromPiece.getMovement(fromPosition, toPosition);
    }

    public void validateBeforeMoveTo(final Position fromPosition, final Position toPosition) {
        validatePickExistPiece(chessBoard.choosePiece(fromPosition));
        validateTurnCamp(chessBoard.choosePiece(fromPosition));
        validateSameCamp(chessBoard.choosePiece(fromPosition), chessBoard.choosePiece(toPosition));
        validateEqualPosition(fromPosition, toPosition);
    }

    private void validateTurnCamp(final Piece fromPiece) {
        if (!turnCamp.isMyTurn(fromPiece)) {
            throw new IllegalArgumentException(TURN_MISMATCHED_ERROR_MESSAGE);
        }
    }

    private void validatePickExistPiece(final Piece fromPiece) {
        if (fromPiece.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CHOICE_ERROR_MESSAGE);
        }
    }

    private void validateSameCamp(final Piece fromPiece, final Piece toPiece) {
        if (fromPiece.isEmpty()) {
            return;
        }
        if (fromPiece.isSameCamp(toPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE_ERROR_MESSAGE);
        }
    }

    private void validateEqualPosition(final Position fromPosition, final Position toPosition) {
        if (fromPosition.equals(toPosition)) {
            throw new IllegalArgumentException(SAME_POSITION_ERROR_MESSAGE);
        }
    }

    public void validateLastMovable(final Piece piece, final PieceMove pieceMove, final boolean lastPiece) {
        if (!pieceMove.isMovable(piece, lastPiece)) {
            throw new IllegalArgumentException(UNABLE_TO_MOVE_ERROR_MESSAGE);
        }
    }

    public boolean isKingsLive() {
        return chessBoard.isKingsLive();
    }

    private void changeTurn() {
        this.turnCamp = turnCamp.convert(turnCamp);
    }

    public double getWhiteScore() {
        return chessBoard.calculateTotalScoreByCamp(Camp.WHITE);
    }

    public double getBlackScore() {
        return chessBoard.calculateTotalScoreByCamp(Camp.BLACK);
    }

    public Camp getWinnerCamp() {
        if (chessBoard.isKingsLive()) {
            return getWinnerByScore();
        }
        return getWinnerByKingLive();
    }

    private Camp getWinnerByKingLive() {
        if (chessBoard.isKingLiveByCamp(Camp.WHITE)) {
            return Camp.WHITE;
        }
        return Camp.BLACK;
    }

    private Camp getWinnerByScore() {
        if (chessBoard.calculateTotalScoreByCamp(Camp.WHITE) > chessBoard.calculateTotalScoreByCamp(Camp.BLACK)) {
            return Camp.WHITE;
        }
        if (chessBoard.calculateTotalScoreByCamp(Camp.WHITE) < chessBoard.calculateTotalScoreByCamp(Camp.BLACK)) {
            return Camp.BLACK;
        }
        return Camp.NEUTRAL;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}

