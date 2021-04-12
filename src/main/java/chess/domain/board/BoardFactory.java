package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.chess.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;

public class BoardFactory {

    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 7;

    private static final int WHITE_PAWN_INDEX = 1;
    private static final int BLACK_PAWN_INDEX = 6;

    private static final int BLANK_START_INDEX = 2;
    private static final int BLANK_END_INDEX = 5;

    private BoardFactory() {}

    private static void createBlanks(Map<Position, Piece> board, int minIndex, int maxIndex) {
        for (int yPoint = minIndex; yPoint <= maxIndex; yPoint++) {
            createPiecesAtRow(board, yPoint, Blank.INSTANCE);
        }
    }

    private static void createPiecesAtRow(Map<Position, Piece> board, int yPoint, Piece piece) {
        for (int xPoint = MIN_INDEX; xPoint <= MAX_INDEX; xPoint++) {
            board.put(Position.of(xPoint, yPoint), piece);
        }
    }

    public static class InitializedBoard {
        public static Board create() {
            final Map<Position, Piece> board = new HashMap<>();
            createNonPawns(board, MIN_INDEX, Color.WHITE);
            createPawns(board, WHITE_PAWN_INDEX, Color.WHITE);

            createNonPawns(board, MAX_INDEX, Color.BLACK);
            createPawns(board, BLACK_PAWN_INDEX, Color.BLACK);

            createBlanks(board, BLANK_START_INDEX, BLANK_END_INDEX);
            return new Board(board);
        }

        private static void createNonPawns(Map<Position, Piece> board, int yPoint, Color color) {
            board.put(Position.of(0, yPoint), Rook.from(color));
            board.put(Position.of(1, yPoint), Knight.from(color));
            board.put(Position.of(2, yPoint), Bishop.from(color));
            board.put(Position.of(3, yPoint), Queen.from(color));
            board.put(Position.of(4, yPoint), King.from(color));
            board.put(Position.of(5, yPoint), Bishop.from(color));
            board.put(Position.of(6, yPoint), Knight.from(color));
            board.put(Position.of(7, yPoint), Rook.from(color));
        }

        private static void createPawns(Map<Position, Piece> board, int yPoint, Color color) {
            createPiecesAtRow(board, yPoint, Pawn.from(color));
        }
    }

    public static class EmptyBoard {
        public static Board create() {
            final Map<Position, Piece> board = new HashMap<>();
            createBlanks(board, MIN_INDEX, MAX_INDEX);
            return new Board(board);
        }
    }
}
