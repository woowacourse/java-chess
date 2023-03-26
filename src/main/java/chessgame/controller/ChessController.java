package chessgame.controller;

import chessgame.domain.Command;
import chessgame.domain.Game;
import chessgame.service.ChessService;
import chessgame.view.InputView;
import chessgame.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessService chessService;

    public ChessController(InputView inputView, OutputView outputView, ChessService chessService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessService = chessService;
    }

    public void run() {
        Game game = setGame();
        playGame(game);
    }

    public Game setGame() {
        try {
            outputView.printSetGameMessage();
            String gameName = inputView.readGameName();
            return chessService.setGame(gameName);
        } catch (Exception e) {
            outputView.printErrorMsg(e.getMessage());
            return setGame();
        }
    }

    private void playGame(Game game) {
        outputView.printStartMessage();
        do {
            eachTurn(game);
            printResult(game);
        } while (!game.isEnd());
        if (game.isEndByKing()) {
            outputView.printWinner(game.winTeam());
            chessService.removeGame(game);
            return;
        }
        saveGame(game);
    }

    private void saveGame(Game game) {
        chessService.removeGame(game);
        chessService.saveGame(game);
    }

    private void eachTurn(Game game) {
        try {
            Command command = readCommand();
            setState(game, command);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            eachTurn(game);
        }
    }

    private Command readCommand() {
        try {
            return Command.of(inputView.readCommand());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            return readCommand();
        }
    }

    private void setState(Game game, Command command) {
        try {
            game.setState(command);
            printStatusResult(game, command);
        } catch (UnsupportedOperationException e) {
            outputView.printErrorMsg(e.getMessage());
            setState(game, readCommand());
        }
    }

    private void printStatusResult(Game game, Command command) {
        if (command.isStatus()) {
            outputView.printScore(game.scoreBoard());
            outputView.printScoreWinner(game.scoreBoard());
        }
    }

    private void printResult(Game game) {
        if (!game.isEnd()) {
            outputView.printChessBoard(game.board());
        }
    }
}
