package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Board generateBoard() {
        Map<Position, Piece> board = initBoard();
        return new Board(board);
    }

    private static Map<Position, Piece> initBoard() {
        Map<Position, Piece> board = new HashMap<>();
        initEmpty(board);

        initNonPawns(1, Side.BLACK, board);
        initPawns(2, Side.BLACK, board);

        initPawns(7, Side.WHITE, board);
        initNonPawns(8, Side.WHITE, board);

        return board;
    }

    private static void initEmpty(final Map<Position, Piece> board) {
        for (int rank = Board.LOWER_BOUNDARY; rank <= Board.UPPER_BOUNDARY; rank++) {
            for (int file = Board.LOWER_BOUNDARY; file <= Board.UPPER_BOUNDARY; file++) {
                board.put(Position.of(rank, file), new Empty(Type.EMPTY, Side.NEUTRALITY));
            }
        }
    }

    private static void initPawns(final int rank, final Side side, final Map<Position, Piece> board) {
        for (int file = 1; file <= 8; file++) {
            board.put(Position.of(rank, file), new Pawn(Type.PAWN, side));
        }
    }

    private static void initNonPawns(final int rank, final Side side, final Map<Position, Piece> board) {
        board.put(Position.of(rank,1), new Rook(Type.ROOK, side));
        board.put(Position.of(rank,2), new Knight(Type.KNIGHT, side));
        board.put(Position.of(rank,3), new Bishop(Type.BISHOP, side));
        board.put(Position.of(rank,4), new Queen(Type.QUEEN, side));
        board.put(Position.of(rank,5), new King(Type.KING, side));
        board.put(Position.of(rank,6), new Bishop(Type.BISHOP, side));
        board.put(Position.of(rank,7), new Knight(Type.KNIGHT, side));
        board.put(Position.of(rank,8), new Rook(Type.ROOK, side));
    }
}
