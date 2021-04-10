package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardInitializer {
    private static final int BOARD_LENGTH = 8;

    public static Board initiateBoard() {
        final Map<Position, Piece> map = new LinkedHashMap<>();

        putLineInMap(map, Horizontal.EIGHT, getPiecesOfFirstLine(Owner.BLACK));
        putLineInMap(map, Horizontal.SEVEN, getPiecesOfSecondLine(Owner.BLACK));
        putLineInMap(map, Horizontal.SIX, getEmptyLine());
        putLineInMap(map, Horizontal.FIVE, getEmptyLine());
        putLineInMap(map, Horizontal.FOUR, getEmptyLine());
        putLineInMap(map, Horizontal.THREE, getEmptyLine());
        putLineInMap(map, Horizontal.TWO, getPiecesOfSecondLine(Owner.WHITE));
        putLineInMap(map, Horizontal.ONE, getPiecesOfFirstLine(Owner.WHITE));

        return new Board(map);
    }

    private static void putLineInMap(final Map<Position, Piece> map, final Horizontal horizontal, final Piece[] pieces) {
        final Vertical[] verticals = Vertical.values();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            map.put(new Position(verticals[i], horizontal), pieces[i]);
        }
    }

    private static Piece[] getPiecesOfFirstLine(final Owner owner) {
        return new Piece[]{
                Rook.of(owner),
                Knight.of(owner),
                Bishop.of(owner),
                Queen.of(owner),
                King.of(owner),
                Bishop.of(owner),
                Knight.of(owner),
                Rook.of(owner)
        };
    }

    private static Piece[] getPiecesOfSecondLine(final Owner owner) {
        final Piece[] pieces = new Pawn[BOARD_LENGTH];
        Arrays.fill(pieces, Pawn.of(owner));
        return pieces;
    }

    private static Piece[] getEmptyLine() {
        final Piece[] pieces = new Empty[BOARD_LENGTH];
        Arrays.fill(pieces, Empty.of());
        return pieces;
    }
}
