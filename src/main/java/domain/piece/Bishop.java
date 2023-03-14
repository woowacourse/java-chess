package domain.piece;

import domain.Color;
import domain.Column;
import domain.InitialColumns;
import domain.Square;

import java.util.List;

public class Bishop extends Piece {

    private final static List<Column> initialColumns = List.of(Column.C, Column.F);

    public Bishop(Color color) {
        super(color, new InitialColumns(initialColumns));
    }

    @Override
    public void move(Square start, Square destination) {

    }
}
