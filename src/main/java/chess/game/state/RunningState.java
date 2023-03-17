package chess.game.state;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.dto.SquareResponse;
import java.util.List;

public class RunningState implements GameState {
    private static final String RUNNING_STATE_EXCEPTION_MESSAGE = "[ERROR] 잘못된 게임의 상태 입니다.(상태: 진행중)";
    public static final GameState STATE = new RunningState();

    private RunningState() {
    }

    @Override
    public void startGame(Runnable runnable) {
        throw new IllegalStateException(RUNNING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public void movePiece(Runnable runnable) {
        runnable.run();
    }

    @Override
    public List<SquareResponse> getBoard(Board board) {
        return board.getBoard().entrySet().stream()
                .map(entry -> SquareResponse.of(entry.getKey(), entry.getValue()))
                .collect(toList());
    }
}
