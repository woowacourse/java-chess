package chess.controller;

import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.Command;
import chess.domain.point.Point;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStart();

        final ChessGame game = new ChessGame(BoardFactory.createInitialChessBoard());

        while (game.isPlayable()) {
            play(game);
        }
    }

    private void play(final ChessGame game) {
        try {
            final List<String> readCommand = inputView.readCommand();
            final Command command = Command.from(readCommand.get(0));

            executeCommand(game, command, readCommand);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void executeCommand(final ChessGame game, final Command command, final List<String> readCommand) {
        if (command.isEnd()) {
            game.finish();
            return;
        }
        if (command.isStart()) {
            game.start();
        }
        if (command.isMove()) {
            final Point departure = generatePoint(readCommand, 1);
            final Point destination = generatePoint(readCommand, 2);
            game.move(departure, destination);
        }
        outputView.printBoard(game.getBoard());
    }

    private Point generatePoint(final List<String> readCommand, final int index) {
        final String pointInfo = readCommand.get(index);
        final char file = pointInfo.charAt(0);
        final int rank = Character.getNumericValue(pointInfo.charAt(1));

        return Point.of(file, rank);
    }
}
