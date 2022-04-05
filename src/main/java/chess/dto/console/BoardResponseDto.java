package chess.dto.console;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardResponseDto {

    private final List<List<String>> names;

    public BoardResponseDto(final Map<Position, Piece> board) {
        final List<List<String>> names = new ArrayList<>();
        for (Row row : Row.values()) {
            names.add(createNamesOnSameRow(board, row));
        }
        this.names = names;
    }

    private List<String> createNamesOnSameRow(final Map<Position, Piece> board, final Row row) {
        return Arrays.stream(Column.values())
                .map(column -> board.get(Position.of(column, row)))
                .map(this::decideName)
                .collect(Collectors.toList());
    }

    private String decideName(final Piece piece) {
        final String name = decideNameByType(piece);
        if (piece.getColor() == Color.BLACK) {
            return name.toUpperCase(Locale.ROOT);
        }
        return name;
    }

    private String decideNameByType(final Piece piece) {
        if (piece.getType() == Type.KING) {
            return "k";
        }
        if (piece.getType() == Type.QUEEN) {
            return "q";
        }
        if (piece.getType() == Type.BISHOP) {
            return "b";
        }
        if (piece.getType() == Type.KNIGHT) {
            return "n";
        }
        if (piece.getType() == Type.ROOK) {
            return "r";
        }
        if (piece.getType() == Type.PAWN) {
            return "p";
        }
        return ".";
    }

    public List<List<String>> getNames() {
        return names;
    }
}
