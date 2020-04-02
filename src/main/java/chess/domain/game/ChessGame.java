package chess.domain.game;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.player.Team;
import chess.domain.state.State;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGame {

    private State state;

    public void start(String param) {
        state = state.start(param);
    }

    public void move(MoveParameter moveParameter) {
        state = state.move(moveParameter);
    }

    public void end(String param) {
        state = state.end(param);
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public Map<Team, Double> getStatus() {
        return Arrays.stream(Team.values())
                .collect(Collectors.toMap(
                        value -> value,
                        value -> state.getPoints(value)
                ));
    }

    public Team getWinner() {
        return state.getWinner();
    }
}
