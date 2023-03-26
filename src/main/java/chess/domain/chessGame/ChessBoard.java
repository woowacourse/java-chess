package chess.domain.chessGame;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class ChessBoard {

    private static final int KING_NECESSARY_COUNT = 2;

    private final Map<Position, Piece> chessBoard;
    private Color turn;

    public ChessBoard(Map<Position, Piece> chessBoard, Color turn) {
        this.chessBoard = new HashMap<>(chessBoard);
        this.turn = turn;
    }

    public boolean isKingDead() {
        long kingCount = chessBoard.values().stream()
                .filter(piece -> piece.getPieceType() == PieceType.KING)
                .count();
        return kingCount != KING_NECESSARY_COUNT;
    }

    public void movePiece(Position startPosition, Position endPosition) {
        validateCanMove(startPosition, endPosition);
        executeMove(startPosition, endPosition);
    }

    private void validateCanMove(Position startPosition, Position endPosition) {
        PieceMoveValidator pieceMoveValidator = new PieceMoveValidator(chessBoard);
        pieceMoveValidator.checkPieceExistInStartPosition(startPosition);
        pieceMoveValidator.checkTurn(startPosition, turn);

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
        switchTurn();
    }

    private void switchTurn() {
        turn = Arrays.stream(Color.values())
                .filter(color -> color != turn)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR]"));
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }

    public Color getTurn() {
        return turn;
    }
}
