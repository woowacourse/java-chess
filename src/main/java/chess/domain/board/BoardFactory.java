package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;
import chess.domain.side.Side;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BoardFactory {
    public static Board createInitial() {
        final Map<Square, Piece> board = new LinkedHashMap<>();
        setup(board, Side.from(Color.BLACK));
        setup(board, Side.from(Color.WHITE));
        setup(board, Side.from(Color.NOTHING));

        return new Board(board);
    }
    
    public static Board create(Map<Square, Piece> board) {
        return new Board(board);
    }

    private static void setup(final Map<Square, Piece> board, final Side side) {
        for (Role role : Role.values()) {
            putPiece(board, role, side);
        }
    }

    private static void putPiece(final Map<Square, Piece> board, final Role role, final  Side side) {
        List<File> files = role.getInitialFiles();
        List<Rank> ranks = role.getInitialRanksBySide(side);
        for (File file : files) {
            for (Rank rank : ranks) {
                board.put(Square.of(file, rank), role.create(side));
            }
        }
    }
}
