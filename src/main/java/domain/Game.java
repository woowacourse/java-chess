package domain;

import domain.piece.*;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;

import java.util.*;

public class Game {
    private final Pieces pieces;

    public Game() {
        pieces = new Pieces();
        pieces.init();
    }

    public void display() {
        List<Row> rows = Arrays.asList(Row.values());
        Collections.reverse(rows);
        for (Row row : rows) {
            for (Column column : Column.values()) {
                System.out.print(pieces.getPieceOf(Position.of(column, row)).display());
            }
            System.out.println();
        }
    }
}
