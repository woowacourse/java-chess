package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.OutputView;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public void execute(Command command) {
        if (command.isStart()) {
            start();
        }

        if (command.isMove()) {
            move(command.getSource(), command.getTarget());
        }

        if (command.isStatus()) {
            status();
        }

        if (command.isEnd()) {
            end();
        }
    }

    private void start() {
        if (isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 실행 중 입니다.");
        }
        this.state = state.start();
        Board board = state.getBoard();
        OutputView.printChessBoard(board);
    }

    private void move(Location source, Location target) {
        this.state = state.move(source, target);
        OutputView.printChessBoard(state.getBoard());
    }

    private void status() {
        if (!isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 실행 중일 때만 점수를 출력할 수 있습니다.");
        }
        OutputView.printScore(state.getScore());
    }

    private void end() {
        if (!isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
        }
        this.state = state.end();
    }
}
