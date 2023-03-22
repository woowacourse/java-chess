package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;
import chess.domain.side.Side;

import java.util.*;

public class BoardFactory {
    private static final Map<Square, Piece> board = new LinkedHashMap<>();

    public static Board create() {
        setup(Side.from(Color.BLACK));
        setup(Side.from(Color.WHITE));
        setup(Side.from(Color.NOTHING));

        return new Board(board);
    }

    private static void setup(final Side side) {
        for (Role role : Role.values()) {
            List<Rank> initialRanksBySide = role.getInitialRanksBySide(side);
            putPiece(initialRanksBySide, role.getInitialFiles(), role.create(side));
        }
    }

    private static void putPiece(final List<Rank> ranks, final List<File> files, final Piece piece) {
        for (File file : files) {
            for (Rank rank : ranks) {
                board.put(Square.of(file, rank), piece);
            }
        }
    }
}
