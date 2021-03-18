package domain;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
                System.out.print(pieces.getPieceOf(Position.of(column, row))
                                       .display());
            }
            System.out.println();
        }
    }

    public Piece pickStartPiece(Player player, Position position) {
        Piece piece = pieces.getPieceOf(position);
        if (piece.isSameColor(player.getColor())) {
            return piece;
        }
        throw new IllegalArgumentException();
    }

    public void action(Player player, Position from, Position to) {
        Piece start = pickStartPiece(player, from);
        Piece piece = pieces.getPieceOf(to);
        if (piece.isEmpty()) {
            start.move(to, pieces);
            return;
        }
        if (piece.isSameColor(player.getColor())) {
            throw new IllegalArgumentException();
        }
        start.kill(to, pieces);
        pieces.delete(piece);
    }
}
