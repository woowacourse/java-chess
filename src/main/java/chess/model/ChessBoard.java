package chess.model;

import java.util.List;

public class ChessBoard {
    private final List<Piece> board;

    public ChessBoard(List<Piece> board) {
        this.board = board;
    }

    public List<Piece> getBoard() {
        return board;
    }
}
