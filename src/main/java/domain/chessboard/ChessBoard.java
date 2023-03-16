package domain.chessboard;

import domain.coordinate.Position;
import domain.piece.Color;

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

    public void validateTurn(final Color color, final Square square) {
        if (square.getColor() != color) {
            throw new IllegalStateException(String.format("%s의 턴이 아닙니다.", square.getColor()));
        }
    }

}
