package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class EmptyBoard {
    
    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 7;
    
    public static Board create() {
        final Map<Position, Piece> board = new HashMap<>();
        createBlanks(board);
        return new Board(board);
    }
    
    private static void createBlanks(Map<Position, Piece> board) {
        for (int yPoint = MIN_INDEX; yPoint <= MAX_INDEX; yPoint++) {
            createPiecesAtRow(board, yPoint, Blank.INSTANCE);
        }
    }
    
    private static void createPiecesAtRow(Map<Position, Piece> board, int yPoint, Piece piece) {
        for (int xPoint = MIN_INDEX; xPoint <= MAX_INDEX; xPoint++) {
            board.put(Position.of(xPoint, yPoint), piece);
        }
    }
}
