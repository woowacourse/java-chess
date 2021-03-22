package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardInitializer {
    private static final int BOARD_LENGTH = 8;

    public static Board initiateBoard() {
        final Map<Position, Piece> map = new LinkedHashMap<>();

        resetBoard(map);

        return new Board(map);
    }

    public static void resetBoard(Map<Position, Piece> board) {
        putLineInMap(board, Horizontal.EIGHT, getPiecesOfFirstLine(Owner.BLACK));
        putLineInMap(board, Horizontal.SEVEN, getPiecesOfSecondLine(Owner.BLACK));
        putLineInMap(board, Horizontal.SIX, getEmptyLine());
        putLineInMap(board, Horizontal.FIVE, getEmptyLine());
        putLineInMap(board, Horizontal.FOUR, getEmptyLine());
        putLineInMap(board, Horizontal.THREE, getEmptyLine());
        putLineInMap(board, Horizontal.TWO, getPiecesOfSecondLine(Owner.WHITE));
        putLineInMap(board, Horizontal.ONE, getPiecesOfFirstLine(Owner.WHITE));
    }

    private static void putLineInMap(final Map<Position, Piece> map, final Horizontal horizontal, final Piece[] pieces) {
        final Vertical[] verticals = Vertical.values();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            map.put(new Position(verticals[i], horizontal), pieces[i]);
        }
    }

    private static Piece[] getPiecesOfFirstLine(final Owner owner) {
        return new Piece[]{
                Rook.getInstanceOf(owner),
                Knight.getInstanceOf(owner),
                Bishop.getInstanceOf(owner),
                Queen.getInstanceOf(owner),
                King.getInstanceOf(owner),
                Bishop.getInstanceOf(owner),
                Knight.getInstanceOf(owner),
                Rook.getInstanceOf(owner)
        };
    }

    private static Piece[] getPiecesOfSecondLine(final Owner owner) {
        final Piece[] pieces = new Pawn[BOARD_LENGTH];
        Arrays.fill(pieces, Pawn.getInstanceOf(owner));
        return pieces;
    }

    private static Piece[] getEmptyLine() {
        final Piece[] pieces = new Empty[BOARD_LENGTH];
        Arrays.fill(pieces, Empty.getInstance());
        return pieces;
    }
}
