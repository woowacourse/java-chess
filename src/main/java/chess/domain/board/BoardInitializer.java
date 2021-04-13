package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
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

    public static void resetBoard(final Map<Position, Piece> board) {
        putLineInMap(board, Vertical.EIGHT, getPiecesOfFirstLine(Owner.BLACK));
        putLineInMap(board, Vertical.SEVEN, getPiecesOfSecondLine(Owner.BLACK));
        putLineInMap(board, Vertical.SIX, getEmptyLine());
        putLineInMap(board, Vertical.FIVE, getEmptyLine());
        putLineInMap(board, Vertical.FOUR, getEmptyLine());
        putLineInMap(board, Vertical.THREE, getEmptyLine());
        putLineInMap(board, Vertical.TWO, getPiecesOfSecondLine(Owner.WHITE));
        putLineInMap(board, Vertical.ONE, getPiecesOfFirstLine(Owner.WHITE));
    }

    private static void putLineInMap(final Map<Position, Piece> map, final Vertical vertical, final Piece[] pieces) {
        final Horizontal[] horizontals = Horizontal.values();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            map.put(Position.of(horizontals[i], vertical), pieces[i]);
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
        final Piece[] pieces = new EmptyPiece[BOARD_LENGTH];
        Arrays.fill(pieces, EmptyPiece.getInstance());
        return pieces;
    }
}
