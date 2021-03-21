package chess.domain.game;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class InitializedBoard {
    
    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 7;
    
    private static final int WHITE_PAWN_INDEX = 1;
    private static final int BLACK_PAWN_INDEX = 6;
    
    private static final int BLANK_START_INDEX = 2;
    private static final int BLANK_END_INDEX = 5;
    
    public static Board create() {
        final Map<Position, Piece> board = new HashMap<>();
        createNonPawns(board, MIN_INDEX, Color.WHITE);
        createPawns(board, WHITE_PAWN_INDEX, Color.WHITE);
        
        createNonPawns(board, MAX_INDEX, Color.BLACK);
        createPawns(board, BLACK_PAWN_INDEX, Color.BLACK);
        
        createBlanks(board);
        return new Board(board);
    }
    
    private static void createNonPawns(Map<Position, Piece> board, int yPoint, Color color) {
        board.put(Position.of(0, yPoint), new Rook(color));
        board.put(Position.of(1, yPoint), new Knight(color));
        board.put(Position.of(2, yPoint), new Bishop(color));
        board.put(Position.of(3, yPoint), new Queen(color));
        board.put(Position.of(4, yPoint), new King(color));
        board.put(Position.of(5, yPoint), new Bishop(color));
        board.put(Position.of(6, yPoint), new Knight(color));
        board.put(Position.of(7, yPoint), new Rook(color));
    }
    
    private static void createPawns(Map<Position, Piece> board, int yPoint, Color color) {
        createPieces(board, yPoint, Pawn.from(color));
    }
    
    private static void createBlanks(Map<Position, Piece> board) {
        for (int yPoint = BLANK_START_INDEX; yPoint <= BLANK_END_INDEX; yPoint++) {
            createPieces(board, yPoint, new Blank(Color.BLANK));
        }
    }
    
    private static void createPieces(Map<Position, Piece> board, int yPoint, Piece piece) {
        for (int xPoint = MIN_INDEX; xPoint <= MAX_INDEX; xPoint++) {
            board.put(Position.of(xPoint, yPoint), piece);
        }
    }
}
