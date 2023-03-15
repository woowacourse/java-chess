package domain.chessboard;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private final List<Rank> chessBoard;

    public ChessBoard(final List<Rank> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public List<Rank> getChessBoard() {
        return new ArrayList<>(chessBoard);
    }

}
