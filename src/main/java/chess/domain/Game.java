package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.*;

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

    public void move(Position from, Position to) {
        Player player = turn.player();
        player.move(from, to, pieces);
        turn.next();
    }

    public boolean isNotEnd() {
        System.out.println(turn.player().getColor().name());
        return pieces.toList()
                     .stream()
                     .filter(Piece::isKing)
                     .count() == 2;
    }

    public Map<Color, Double> score() {
        Map<Color, Double> scores = new HashMap<>();
        scores.put(Color.BLACK, pieces.score(Color.BLACK));
        scores.put(Color.WHITE, pieces.score(Color.WHITE));
        return scores;
    }
}
