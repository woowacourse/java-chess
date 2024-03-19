package chess.chessBoard;

import java.util.List;

public class ChessBoard {

    private final List<Square> board;

    private ChessBoard(List<Square> board) {
        this.board = board;
    }

    public ChessBoard() {
        this(BoardGenerator.generate());
    }
}
