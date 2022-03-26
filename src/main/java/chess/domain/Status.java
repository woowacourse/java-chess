package chess.domain;

import java.util.List;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Square;

public class Status {
    private final Board board;

    public Status(Board board) {
        this.board = board;
    }

    public double calculateScore(Color color) {
        double sum = 0;

        List<Map.Entry<Square, Piece>> survives = board.filterBy(color);

        for (Map.Entry<Square, Piece> survive : survives) {
            Piece piece = survive.getValue();
            sum = piece.addScore(sum);
        }

        for (File file : File.values()) {
            int count = (int)survives.stream()
                    .filter(entry -> entry.getValue().isPawn())
                    .filter(entry -> entry.getKey().checkFile(file))
                    .count();

            if (count > 1) {
                sum -= 0.5 * count;
            }
        }

        return sum;
    }
}
