package chess.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;

public abstract class ChessGameState {
    protected ChessBoard chessBoard;
    protected Team currentOrderTeam;
    
    protected ChessGameState(ChessBoard chessBoard, Team currentOrderTeam) {
        this.chessBoard = chessBoard;
        this.currentOrderTeam = currentOrderTeam;
    }
    
    public abstract ChessGameState next();
    
    public boolean runnable() {
        return true;
    }
}
