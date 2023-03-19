package chess.game.state;

import chess.domain.board.ChessBoard;

public abstract class ChessGameState {
    protected ChessBoard chessBoard;
    
    protected ChessGameState(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }
    
    public abstract ChessGameState next();
    
    public boolean runnable() {
        return true;
    };
}
