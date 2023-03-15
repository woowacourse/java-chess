package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class Board {
    
    private final Map<Position, Piece> board;
    
    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }
    
    
    public static Board create() {
        Map<Position, Piece> board = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Position position = Position.from(file.getLabel() + rank.getLabel());
                board.put(position, Empty.create());
            }
        }
        return new Board(board);
    }
    
    public void initialize() {
        for (Position position : board.keySet()) {
            if (position.isRank(0)) {
                for (Piece general : PieceFactory.createWhiteGenerals()) {
                    board.put(position, general);
                }
            }
            if (position.isRank(1)) {
                for (Piece general : PieceFactory.createWhitePawns()) {
                    board.put(position, general);
                }
            }
            if (position.isRank(6)) {
                for (Piece general : PieceFactory.createBlackPawns()) {
                    board.put(position, general);
                }
            }
            if (position.isRank(7)) {
                for (Piece general : PieceFactory.createBlackGenerals()) {
                    board.put(position, general);
                }
            }
        }
        
    }
    
}