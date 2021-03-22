package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Score;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Pieces {
    public static final int KING_COUNT = 2;
    private final List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Piece findByPosition(Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findFirst()
                .orElse(new Empty());
    }

    public void removePieceByPosition(Position target) {
        Piece piece = findByPosition(target);
        pieces.remove(piece);
    }

    public boolean isAliveAllKings() {
        return KING_COUNT == (int) pieces.stream()
                .filter(piece -> piece instanceof King)
                .count();
    }

    public Pieces piecesByColor(Color color) {
        return pieces.stream()
                .filter(piece -> piece.isSameTeam(color))
                .collect(Collectors.collectingAndThen(toList(), Pieces::new));
    }

    public Score totalScore() {
        return new Score(pieces.stream()
                .mapToDouble(Piece::score)
                .sum());
    }

    public int pawnCountByX(char index) {
        return (int) pieces.stream()
                .filter(Piece::isPawn)
                .filter(piece -> piece.position.sameX(index))
                .count();
    }
}
