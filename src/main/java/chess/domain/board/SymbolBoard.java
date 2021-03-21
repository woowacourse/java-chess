package chess.domain.board;

import chess.domain.Chess;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class SymbolBoard {
    private final String[][] board;
    
    public SymbolBoard(String[][] board) {
        this.board = board;
    }
    
    public static SymbolBoard from(Chess chess) {
        final Map<Position, Piece> pieceBoard = chess.getBoard()
                                                     .getBoard();
        final String[][] board = new String[8][8];
        for (Map.Entry<Position, Piece> entry : pieceBoard.entrySet()) {
            final int x = entry.getKey()
                               .getX()
                               .getPoint();
            
            final int y = entry.getKey()
                               .getY()
                               .getPoint();
            
            board[x][y] = entry.getValue()
                               .getSymbol();
        }
        
        return new SymbolBoard(board);
    }
    
    public String[][] getBoard() {
        return board;
    }
}
