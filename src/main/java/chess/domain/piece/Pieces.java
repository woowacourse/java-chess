package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Pieces {
    private final List<Piece> pieces = new ArrayList<>();

    public Pieces(Piece... pieces) {
        this.pieces.addAll(Arrays.asList(pieces));
    }

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public void delete(Piece piece) {
        pieces.remove(piece);
    }

    public Piece getPieceOf(Position position) {
        return pieces.stream()
                     .filter(piece -> piece.hasPosition(position))
                     .findFirst()
                     .orElse(new Empty());
    }

    public boolean hasPieceOf(Position position) {
        return pieces.stream()
                     .anyMatch(piece -> piece.hasPosition(position));
    }

    public List<Piece> toList() {
        return pieces;
    }

    public double score(Color color) {
        return calculateGeneralScore(color) + calculatePawnScore(color);
    }

    private double calculateGeneralScore(Color color) {
        return pieces.stream()
                     .filter(piece -> piece.isSameColor(color))
                     .filter(piece -> !piece.isPawn())
                     .mapToDouble(Piece::score)
                     .sum();
    }

    private double calculatePawnScore(Color color) {
        return pawnCountingByColumn(color).values()
                                          .stream()
                                          .mapToDouble(this::lowerPawnScore)
                                          .sum();
    }

    private Map<Column, Long> pawnCountingByColumn(Color color) {
        return pawnsWith(color).stream()
                               .collect(groupingBy(Piece::getColumn, counting()));
    }

    private List<Piece> pawnsWith(Color color) {
        return pieces.stream()
                     .filter(piece -> piece.isSameColor(color))
                     .filter(Piece::isPawn)
                     .collect(Collectors.toList());
    }

    private double lowerPawnScore(long count) {
        if (count >= 2) {
            return count * 0.5;
        }
        return count;
    }
}
