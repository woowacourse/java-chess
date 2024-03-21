package domain.chess.game;

import domain.Position;
import domain.chess.board.ChessBoard;
import domain.chess.board.ChessBoardWrapper;
import domain.chess.piece.Piece;
import java.util.List;

public class ChessGame {
    private final ChessBoardWrapper chessBoardWrapper;

    public ChessGame() {
        this.chessBoardWrapper = new ChessBoardWrapper(new ChessBoard());
    }

    public boolean move(Position from, Position to) {
        return chessBoardWrapper.move(from, to);
    }

    public List<Piece> getPiecesOnBoard() {
        return chessBoardWrapper.getPiecesOnBoard();
    }
}
