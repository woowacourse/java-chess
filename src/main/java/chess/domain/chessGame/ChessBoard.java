package chess.domain.chessGame;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public final class ChessBoard {

    public static final int KING_NECESSARY_COUNT = 2;

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
    }

    public boolean isKingDead() {
        long kingCount = chessBoard.values().stream()
                .filter(piece -> piece.getPieceType() == PieceType.KING)
                .count();
        return kingCount != KING_NECESSARY_COUNT;
    }

    public void movePiece(Position startPosition, Position endPosition) {
        validateCanMove(chessBoard, startPosition, endPosition);
        executeMove(startPosition, endPosition);
    }

    private void validateCanMove(Map<Position, Piece> chessBoard, Position startPosition, Position endPosition) {
        PieceMoveValidator pieceMoveValidator = new PieceMoveValidator(chessBoard);
        pieceMoveValidator.checkPieceExistInStartPosition(startPosition);

        Piece startPiece = chessBoard.get(startPosition);
        if (startPiece.getPieceType() == PieceType.PAWN) {
            PawnMoveValidator pawnMoveValidator = new PawnMoveValidator(chessBoard);
            pawnMoveValidator.checkPawnCanMove(startPosition, endPosition);
        }

        pieceMoveValidator.checkPieceCanMove(startPosition, endPosition);
    }

    private void executeMove(Position startPosition, Position endPosition) {
        chessBoard.put(endPosition, chessBoard.get(startPosition));
        chessBoard.remove(startPosition);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
