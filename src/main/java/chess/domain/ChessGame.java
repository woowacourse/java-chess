package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    private State state;
    public boolean isEnd = false;

    public ChessGame() {
        this.state = new Ready();
    }

    public void run() {
        while (!isEnd()) {
            final String command = InputView.inputCommand();
            final GameCommand gameCommand = GameCommand.findByInput(command);

            //gameCommand.execute(command);
            if (gameCommand == GameCommand.START) {
                start();
                OutputView.printBoard(board().getValue());
            }
            if (gameCommand == GameCommand.MOVE) {
                final Positions movePositions = Positions.from(command);
                move(movePositions.get(0), movePositions.get(1));

                OutputView.printBoard(board().getValue());
                if (isNotRunning()) {
                    OutputView.printFinishMessage();
                    OutputView.printStatus(statusOfWhite(), statusOfBlack());
                    OutputView.printResultMessage(getResultMessage());
                }
            }
            if (gameCommand == GameCommand.END) {
                OutputView.printFinishMessage();
                if (isNotRunning()) {
                    turnOff();
                    return;
                }
                end();
                OutputView.printStatus(statusOfWhite(), statusOfBlack());
                OutputView.printResultMessage(getResultMessage());
            }
            if (gameCommand == GameCommand.STATUS) {
                OutputView.printStatus(statusOfWhite(), statusOfBlack());
            }
        }
    }

    public void start() {
        this.state = this.state.start();
    }

    public void move(Position beforePosition, Position afterPosition) {
        this.state = this.state.move(beforePosition, afterPosition);
    }

    public void end() {
        this.state = this.state.end();
    }

    public double statusOfBlack() {
        return this.state.statusOfBlack();
    }

    public double statusOfWhite() {
        return this.state.statusOfWhite();
    }

    public boolean isNotRunning() {
        return !this.state.isRunning();
    }

    public Board board() {
        return this.state.getBoard();
    }

    public void turnOff() {
        this.isEnd = true;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public String getResultMessage() {
        return GameResult.from(this.state.getResult()).getMessage();
    }
}
