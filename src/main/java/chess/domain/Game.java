package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

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
        return pieces.toList()
                     .stream()
                     .filter(Piece::isKing)
                     .count() == 2;
    }

    public Map<Color, Double> score() {
        Map<Color, Double> scores = new HashMap<>();
        scores.put(Color.BLACK, calculateScore(Color.BLACK));
        scores.put(Color.WHITE, calculateScore(Color.WHITE));
        return scores;
    }

    private double calculateScore(Color color) {
        return calculateGeneralScore(color) + calculatePawnScore(color);
    }

    private double calculatePawnScore(Color color) {
        List<Piece> pawns = pieces.toList()
                                  .stream()
                                  .filter(piece -> piece.isSameColor(color))
                                  .filter(Piece::isPawn)
                                  .collect(Collectors.toList());

        Map<Column, Long> pawnCountingByColumn = pawns.stream()
                                                         .collect(groupingBy(Piece::getColumn, counting()));

        return pawnCountingByColumn.values()
                                   .stream()
                                   .mapToDouble(count -> {
                                       if (count >= 2) {
                                           return count * 0.5;
                                       }
                                       return count;
                                   })
                                   .sum();
    }

    private double calculateGeneralScore(Color color) {
        return pieces.toList()
                     .stream()
                     .filter(piece -> piece.isSameColor(color))
                     .filter(piece -> !piece.isPawn())
                     .mapToDouble(Piece::score)
                     .sum();
    }
}
