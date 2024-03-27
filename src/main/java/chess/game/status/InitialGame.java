package chess.game.status;

import chess.domain.board.BoardFactory;
import chess.domain.board.TurnTrackerBoard;
import chess.domain.piece.Color;
import chess.view.input.InputView;
import chess.view.input.command.ClientCommand;
import chess.view.input.command.GameCommand;
import chess.view.output.OutputView;

public class InitialGame implements GameStatus {

    private static final Color FIRST_TURN_COLOR = Color.WHITE;

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(final InputView inputView, final OutputView outputView) {
        return applyCommand(inputView.getClientCommand(), outputView);
    }

    private GameStatus applyCommand(final ClientCommand clientCommand, final OutputView outputView) {
        GameCommand gameCommand = clientCommand.getCommand();
        if (gameCommand == GameCommand.START) {
            TurnTrackerBoard turnTrackerBoard = new TurnTrackerBoard(BoardFactory.create(), FIRST_TURN_COLOR);
            outputView.printBoard(turnTrackerBoard);
            return new PlayingGame(turnTrackerBoard);
        }
        if (gameCommand == GameCommand.END) {
            return new TerminateGame();
        }
        throw new IllegalArgumentException("보드를 초기화 하지 않았습니다. start 명령어로 시작 할 수 있습니다.");
    }
}
