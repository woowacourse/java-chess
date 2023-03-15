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
        List<Piece> whiteGenerals = PieceFactory.createWhiteGenerals();
        List<Piece> whitePawns = PieceFactory.createWhitePawns();
        List<Piece> blackPawns = PieceFactory.createBlackPawns();
        List<Piece> blackGenerals = PieceFactory.createBlackGenerals();
        for (Position position : board.keySet()) {
            if (position.isRank(0)) {
                board.put(position, whiteGenerals.get(position.getFile().getIndex()));
            }
            if (position.isRank(1)) {
                board.put(position, whitePawns.get(position.getFile().getIndex()));
            }
            if (position.isRank(6)) {
                board.put(position, blackPawns.get(position.getFile().getIndex()));
            }
            if (position.isRank(7)) {
                board.put(position, blackGenerals.get(position.getFile().getIndex()));
            }
        }
        
    }
    
}