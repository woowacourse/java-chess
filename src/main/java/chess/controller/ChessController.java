package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ChessController {
    private final ChessGame chessGame;
    private final Map<String, GameAction> commandMapper = new HashMap<>();

    public ChessController() {
        this.chessGame = new ChessGame();
        commandMapper.put("start", arguments -> start());
        commandMapper.put("move", arguments -> move(arguments));
        commandMapper.put("status", arguments -> status());
        commandMapper.put("end", arguments -> end());
    }

    public void execute() {
        OutputView.printGameStartMessage();

        while (!chessGame.isEnd() && !chessGame.isCatch()) {
            runGame();
        }

        if (chessGame.isCatch()) {
            OutputView.printResultWhenKingCatch(chessGame.getTurn().reverse());
            return;
        }
        showStatus(chessGame);
    }

    private void runGame() {
        try {
            CommandLine commandLine = new CommandLine(InputView.readCommand());
            commandMapper.get(commandLine.getCommand())
                    .execute(commandLine.getArguments());
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void start() {
        chessGame.start();
        OutputView.printBoard(BoardDto.create(chessGame.getBoard()));
    }

    private void move(List<String> arguments) {
        chessGame.move(arguments);
        OutputView.printBoard(BoardDto.create(chessGame.getBoard()));
    }

    private void status() {
        showStatus(chessGame);
    }

    private void end() {
        chessGame.end();
    }

    private void showStatus(ChessGame chessGame) {
        Map<Color, Double> status = chessGame.status();
        OutputView.printStatus(status.get(Color.WHITE), status.get(Color.BLACK));
    }
}
