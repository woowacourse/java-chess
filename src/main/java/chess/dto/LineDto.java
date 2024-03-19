package chess.dto;

import chess.model.Board;
import chess.model.Position;
import chess.model.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LineDto {

    private final List<String> line;

    public LineDto(List<String> line) {
        this.line = line;
    }

    public static LineDto of(Board board, int row) {
        List<String> line = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Position position = new Position(row, i);
            line.add(findPieceName(board, position));
        }
        return new LineDto(line);
    }

    private static String findPieceName(Board board, Position position) {
        Optional<Piece> piece = board.findPiece(position);
        if (piece.isPresent()) {
            return piece.get()
                .toString();
        }
        return ".";
    }

    @Override
    public String toString() {
        return String.join("", line);
    }
}
