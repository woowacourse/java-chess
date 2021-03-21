package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;

public class ChessGame {
    private final State state;

    public ChessGame(Board board) {
        this.state = new Init(board);
    }

    public void start() {
        state.start();
    }

    //    // board로 이동 예정
//    public Set<Position> movable(Position source) {
//        Piece piece = board.pieceOfPosition(source);
//        MoveStrategy moveStrategy = piece.moveStrategy();
//        return moveStrategy.movable(board, source);
//    }

    public boolean isNotFinished() {
        return state.isNotFinished();
    }
}
