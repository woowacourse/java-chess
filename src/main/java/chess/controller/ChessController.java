package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.point.Point;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStart();

        ChessGame game = new ChessGame(BoardFactory.createInitialChessBoard());

        while (game.isPlayable()) {
            play(game);
        }
    }

    private void play(final ChessGame game) {
        try {
            List<String> readCommand = inputView.readCommand();
            Command command = Command.from(readCommand.get(0));

            executeCommand(game, command, readCommand);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void executeCommand(ChessGame game, Command command, List<String> readCommand) {
        if (command.isEnd()) {
            game.finish();
            return;
        }
        if (command.isStart()) {
            game.start();
        }
        if (command.isMove()) {
            Point departure = generatePoint(readCommand, 1);
            Point destination = generatePoint(readCommand, 2);
            game.move(departure, destination);
        }
        outputView.printBoard(game.getBoard());
    }

    private Point generatePoint(List<String> readCommand, int index) {
        String pointInfo = readCommand.get(index);
        char file = pointInfo.charAt(0);
        int rank = Character.getNumericValue(pointInfo.charAt(1));

        return new Point(file, rank);
    }
}
