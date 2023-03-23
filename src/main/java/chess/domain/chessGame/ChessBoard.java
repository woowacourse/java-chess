package chess.domain.chessGame;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public final class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
    }

    public void movePiece(Position startPosition, Position endPosition) {
        validateCanMove(chessBoard, startPosition, endPosition);
        executeMove(startPosition, endPosition);
    }

    private void validateCanMove(Map<Position, Piece> chessBoard, Position startPosition, Position endPosition) {
        PieceMoveValidator pieceMoveValidator = new PieceMoveValidator(chessBoard);
        pieceMoveValidator.checkPieceExistInStartPosition(startPosition);

        Piece startPiece = chessBoard.get(startPosition);
        if (startPiece.getPieceType() == PieceName.PAWN) {
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
