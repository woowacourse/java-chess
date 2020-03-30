package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.piece.PieceState;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.domain.result.Status;

import java.util.Map;

public class EndState implements State {

    private final Status status;

    public EndState(Status status) {
        this.status = status;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
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
    public State status() {
        throw new UnsupportedOperationException("이미 종료 되었습니다.");
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException("이미 종료 되었습니다.");
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Map<Position, PieceState> getRemainPiece(Player player) {
        throw new UnsupportedOperationException("이미 종료 되었습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }


}
