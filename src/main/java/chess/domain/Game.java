package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    private final Pieces pieces;
    private final Turn turn;

    public Game() {
        pieces = new Pieces();
        turn = new Turn();
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

    public void move(Position from, Position to) {
        Player player = turn.player();
        Piece start = pickStartPiece(player, from);
        Piece piece = pieces.getPieceOf(to);
        if (piece.isEmpty()) {
            start.moveToEmpty(to, pieces);
            turn.next();
            return;
        }
        if (piece.isSameColor(player.getColor())) {
            throw new IllegalArgumentException();
        }
        start.moveForKill(to, pieces);
        pieces.delete(piece);
        turn.next();
    }

    public boolean isNotEnd() {
        return pieces.toList().stream().filter(Piece::isKing).count() == 2;
    }
}
