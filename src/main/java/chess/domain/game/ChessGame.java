package chess.domain.game;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.AutomatedBoardInitializer;
import chess.domain.player.Team;
import chess.domain.state.ReadyState;
import chess.domain.state.State;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGame {

    private State state;
    private Turn turn = Turn.from(Team.WHITE);

    public ChessGame() {
        this.state = new ReadyState(new AutomatedBoardInitializer());
    }

    public void start() {
        state = state.start();
    }

    public void move(MoveParameter moveParameter) {
        state = state.move(moveParameter, turn);
    }

    public void end() {
        state = state.end();
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
}
