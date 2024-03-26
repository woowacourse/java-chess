package chess.game.status;

import chess.domain.board.Board;
import chess.view.input.InputView;
import chess.view.input.command.ClientCommand;
import chess.view.input.command.GameCommand;
import chess.view.input.command.MovePath;
import chess.view.output.OutputView;

public class PlayingGame implements GameStatus {

    private final InputView inputView;
    private final Board board;

    public PlayingGame(InputView inputView, Board board) {
        this.inputView = inputView;
        this.board = board;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play() {
        return applyCommand(inputView.getClientCommand());
    }

    private GameStatus applyCommand(ClientCommand clientCommand) {
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

    private GameStatus movePiece(MovePath movePath) {
        Board board = this.board.move(movePath.from(), movePath.to());
        OutputView.printBoard(board);
        return new PlayingGame(inputView, board);
    }
}
