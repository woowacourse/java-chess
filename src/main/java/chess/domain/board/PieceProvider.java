package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import java.util.List;

public interface PieceProvider {
    
    List<Piece> getRankPieces(Rank rank);
    
    List<Piece> getFilePieces(File file);
    
}
