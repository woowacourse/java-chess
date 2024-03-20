package chess.dto;

import chess.model.Board;
import chess.model.Position;
import java.util.ArrayList;
import java.util.List;

public class LineDto {

    private final List<String> line;

    public LineDto(List<String> line) {
        this.line = line;
    }

    public static LineDto of(Board board, int row) {
        List<String> line = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            Position position = new Position(row, i);
            line.add(findPieceName(board, position));
        }
        return new LineDto(line);
    }

    private static String findPieceName(Board board, Position position) {
        return board.findPiece(position)
                .toString();
    }

    @Override
    public String toString() {
        return String.join("", line);
    }
}
