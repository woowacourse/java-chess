package chess.game.status;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.view.input.InputView;
import chess.view.input.command.ClientCommand;
import chess.view.input.command.GameCommand;
import chess.view.input.command.MovePath;
import chess.view.output.OutputView;

public class PlayingGame implements GameStatus {
    private final Board board;
    private final Color color;

    public PlayingGame(final Board board, final Color color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(InputView inputView, OutputView outputView) {
        return applyCommand(inputView.getClientCommand(), outputView);
    }

    private GameStatus applyCommand(final ClientCommand clientCommand, final OutputView outputView) {
        GameCommand gameCommand = clientCommand.getCommand();
        if (gameCommand == GameCommand.START) {
            return new RestartGame();
        }
        if (gameCommand == GameCommand.MOVE) {
            return movePiece(clientCommand.getMovePath(), outputView);
        }
        if (gameCommand == GameCommand.END) {
            return new TerminateGame();
        }
        return this;
    }

    private GameStatus movePiece(final MovePath movePath, OutputView outputView) {
        Board board = this.board.move(movePath.from(), movePath.to(), color);
        outputView.printBoard(board);
        return new PlayingGame(board, color.opposite());
    }
}
