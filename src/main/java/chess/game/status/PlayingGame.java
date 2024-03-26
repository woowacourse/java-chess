package chess.game.status;

import chess.domain.board.TurnTrackerBoard;
import chess.view.input.InputView;
import chess.view.input.command.ClientCommand;
import chess.view.input.command.GameCommand;
import chess.view.input.command.MovePath;
import chess.view.output.OutputView;

public class PlayingGame implements GameStatus {

    private final InputView inputView;
    private final TurnTrackerBoard turnTrackerBoard;

    public PlayingGame(final InputView inputView, final TurnTrackerBoard turnTrackerBoard) {
        this.inputView = inputView;
        this.turnTrackerBoard = turnTrackerBoard;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play() {
        return applyCommand(inputView.getClientCommand());
    }

    private GameStatus applyCommand(final ClientCommand clientCommand) {
        GameCommand gameCommand = clientCommand.getCommand();
        if (gameCommand == GameCommand.START) {
            return new RestartGame(inputView);
        }
        if (gameCommand == GameCommand.MOVE) {
            return movePiece(clientCommand.getMovePath());
        }
        if (gameCommand == GameCommand.END) {
            return new TerminateGame();
        }
        return this;
    }

    private GameStatus movePiece(final MovePath movePath) {
        TurnTrackerBoard turnTrackerBoard = this.turnTrackerBoard.move(movePath.from(), movePath.to());
        OutputView.printBoard(turnTrackerBoard);
        return new PlayingGame(inputView, turnTrackerBoard);
    }
}
