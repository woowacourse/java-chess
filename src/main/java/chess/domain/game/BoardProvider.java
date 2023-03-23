package chess.domain.game;

import chess.domain.board.PieceProvider;

public interface BoardProvider {
    
    PieceProvider getBoard();
    
}
