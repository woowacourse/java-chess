package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Map;

public class EndState implements State {

    private final Board board;

    public EndState(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new RunningState(board);
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("이미 종료 되었습니다.");
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public Map<Position, PieceState> getRemainPiece(Team team) {
        return board.getRemainPieces(team);
    }
}
