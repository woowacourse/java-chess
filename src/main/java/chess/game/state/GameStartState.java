package chess.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;

public class GameStartState extends ChessGameState {
    public GameStartState(Team currentOrderTeam) {
        super(ChessBoard.create(), currentOrderTeam);
    }
    
    @Override
    public ChessGameState next() {
        return new OutputChessBoardState(chessBoard, currentOrderTeam);
    }
}
