package chess.game.status;

import chess.domain.board.Board;
import chess.domain.board.BoardCreator;
import chess.view.input.InputView;
import chess.view.input.command.ClientCommand;
import chess.view.input.command.GameCommand;
import chess.view.output.OutputView;

public class InitialGame implements GameStatus {

    private final InputView inputView;

    public InitialGame(InputView inputView) {
        this.inputView = inputView;
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
            Board board = BoardCreator.create();
            OutputView.printBoard(board);
            return new MainGame(inputView, board);
        }
        if (gameCommand == GameCommand.END) {
            return new TerminateGame();
        }
        throw new IllegalArgumentException("보드를 초기화 하지 않았습니다. start 명령어로 시작 할 수 있습니다.");
    }
}
