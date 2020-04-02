package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.EnumRepositoryBoardInitializer;
import chess.domain.game.Turn;
import chess.domain.player.Team;

public class ReadyState implements State {

    @Override
    public State start(String param) {
        if ("new".equals(param)) {
            return new RunningState(Board.of(new EnumRepositoryBoardInitializer()), Turn.from(Team.WHITE));
        }
        if ("load".equals(param)) {
            // 게임 불러오기
        }
        throw new IllegalArgumentException("잘못된 시작입니다.");
    }

    @Override
    public State move(MoveParameter moveParameter) {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }

    @Override
    public State end(String param) {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getPoints(Team team) {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Team getWinner() {
        throw new UnsupportedOperationException("게임이 아직 시작되지 않았습니다.");
    }
}
