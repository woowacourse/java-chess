package domain.game;

import domain.board.Board;
import domain.board.BoardInitializer;
import domain.board.Position;
import domain.piece.Piece;
import domain.piece.PieceColor;

import java.util.Map;

import static domain.piece.PieceColor.WHITE;

public class ChessGame {
    private final Board board;

    private PieceColor currentColor = WHITE;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public static ChessGame initGame() {
        return new ChessGame(BoardInitializer.initBoard());
    }

    public Map<Position, Piece> piecePositions() {
        return board.piecePositions();
    }

    public void play(final Position source, final Position destination) {
        board.movePiece(currentColor, source, destination);
        currentColor = currentColor.toggle();
    }
}
