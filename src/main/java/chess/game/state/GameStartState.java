package chess.game.state;

import chess.domain.board.ChessBoard;

public class GameStartState extends ChessGameState {
    public GameStartState() {
        super(ChessBoard.create());
    }
    
    @Override
    public ChessGameState next() {
        return new OutputChessBoardState(chessBoard);
    }
}
