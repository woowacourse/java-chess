package chess.game.status;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.view.input.InputView;
import chess.view.input.command.ClientCommand;
import chess.view.input.command.GameCommand;
import chess.view.output.OutputView;
import java.util.List;

public class MainGame implements GameStatus {

    private final InputView inputView;
    private final Board board;

    public MainGame(InputView inputView, Board board) {
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
            return movePiece(clientCommand.getFromToPositions());
        }
        if (gameCommand == GameCommand.END) {
            return new TerminateGame();
        }
        return this;
    }

    private GameStatus movePiece(List<Position> fromToPositions) {
        Board board = this.board.move(fromToPositions.get(0), fromToPositions.get(1));
        OutputView.printBoard(board);
        if (board.isGameOver()) {
            return new EndGame(board);
        }
        return new MainGame(inputView, board);
    }
}
