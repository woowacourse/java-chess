package domain.chessboard;

import domain.coordinate.Position;

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

    public Square findSquare(Position position) {
        return chessBoard.get(position.getY())
                .getRank()
                .get(position.getX());
    }

}
