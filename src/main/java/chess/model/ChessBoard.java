package chess.model;

import java.util.Map;

public class ChessBoard {
    private final Map<ChessPosition, Piece> board;

    public ChessBoard(Map<ChessPosition, Piece> board) {
        this.board = board;
    }



    public Map<ChessPosition, Piece> getBoard() {
        return board;
    }
}
