package chess.domain.board;

import chess.domain.Color;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class Score {
    private Score() {
    }

    public static double calculateScore(List<Piece> pieces, Color color) {
        final List<Piece> piecesByColor = pieces.stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());

        return calculateScore(piecesByColor);
    }

    private static double calculateScore(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }
}
