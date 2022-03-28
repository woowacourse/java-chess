package chess.domain;

import chess.domain.command.Command;
import chess.domain.command.GameCommand;
import chess.domain.location.Location;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.OutputView;
import java.util.function.Consumer;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public Consumer<Board> execute(Command command) {
        if (GameCommand.isStart(command.getGameCommand())) {
            start();
            return OutputView::printChessBoard;
        }
        if (GameCommand.isMove(command.getGameCommand())) {
            move(command.getSourceLocation(), command.getTargetLocation());
            return OutputView::printChessBoard;
        }
        if (GameCommand.isStatus(command.getGameCommand())) {
            status();
            return board -> OutputView.printScore(state.getScore());
        }
        end();
        return board -> System.out.println();
    }

    private void move(Location source, Location target) {
        this.state = state.move(source, target);
    }

    private void start() {
        if (isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 실행 중 입니다.");
        }
        this.state = state.start();
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
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
