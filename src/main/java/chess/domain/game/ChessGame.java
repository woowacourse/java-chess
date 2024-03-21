package chess.domain.game;

import chess.domain.Position;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardWrapper;
import chess.domain.piece.Piece;
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
