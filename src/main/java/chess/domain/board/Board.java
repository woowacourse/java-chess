package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;

public interface Board {
    
    
    void checkColor(Position from, Position to, Color turn);
    
    void checkRoute(Position from, Position to);
    
    void move(Position from, Position to);
    
    List<Piece> getRankPieces(Rank rank);
    
    List<Piece> getFilePieces(File file);
    
    boolean isKingDead();
    
}
