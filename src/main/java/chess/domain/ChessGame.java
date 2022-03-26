package chess.domain;

import chess.GameCommand;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public void execute(List<String> commandList) {
        if (GameCommand.isStart(commandList.get(0))) {
            start();
        }

        if (GameCommand.isEnd(commandList.get(0))) {
            end();
        }

        if (GameCommand.isMove(commandList.get(0))) {
            move(commandList.get(1), commandList.get(2));
        }

        if (GameCommand.isStatus(commandList.get(0))){
            status();
        }
    }

    private void move(String source, String target) {
        Location sourceLocation = Location.of(source);
        Location targetLocation = Location.of(target);
        this.state = state.move(sourceLocation, targetLocation);
        OutputView.printChessBoard(state.getBoard());
    }

    private void start() {
        if (isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 실행 중 입니다.");
        }
        this.state = state.start();
        Board board = state.getBoard();
        OutputView.printChessBoard(board);
    }

    private void end() {
        if (!isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
        }
        this.state = state.end();
    }

    private void status() {
        if (!isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 실행 중일 때만 점수를 출력할 수 있습니다.");
        }

        OutputView.printScore(state.getScore());

    }

}
