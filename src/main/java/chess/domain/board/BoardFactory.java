package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BoardFactory {
    private static final Color FIRST_TURN_COLOR = Color.WHITE;

    public static Board createInitial() {
        final Map<Square, Piece> board = new LinkedHashMap<>();
        setup(board, Color.BLACK);
        setup(board, Color.WHITE);
        setup(board, Color.NOTHING);

        return new Board(board, FIRST_TURN_COLOR);
    }

    public static Board create(Map<Square, Piece> board) {
        return new Board(new LinkedHashMap<>(board), FIRST_TURN_COLOR);
    }

    private static void setup(final Map<Square, Piece> board, final Color color) {
        for (Role role : Role.values()) {
            putPiece(board, role, color);
        }
    }

    private static void putPiece(final Map<Square, Piece> board, final Role role, final Color color) {
        List<File> files = role.getInitialFiles();
        List<Rank> ranks = role.getInitialRanksBySide(color);
        for (File file : files) {
            for (Rank rank : ranks) {
                board.put(Square.of(file, rank), role.create(color));
            }
        }
    }
}
