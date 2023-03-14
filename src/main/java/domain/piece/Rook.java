package domain.piece;

import domain.Color;
import domain.Column;
import domain.InitialColumns;
import domain.Rank;
import domain.Square;

import java.util.List;


public class Rook extends Piece{

    private final static List<Column> initialColumns = List.of(Column.A, Column.H);

    public Rook(Color color) {
        super(color, new InitialColumns(initialColumns));
    }

    @Override
    public void move(Square start, Square destination) {

    }
}
