package chess.controller;

import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;

    public Controller(final InputView inputView, final OutputView outputView, ChessGameService chessGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
    }

    public void run() {
        outputView.printStartGuideMessage();
        GameCommandInvoker gameCommandInvoker = new GameCommandInvoker(chessGameService, outputView);
        GameCommand gameCommand = readStartCommand(gameCommandInvoker);
        while(gameCommand != GameCommand.END){
            gameCommand = runByCommand(gameCommandInvoker);
            gameCommand = checkGameFinished(gameCommand);
        }
    }

    private GameCommand readStartCommand(GameCommandInvoker gameCommandInvoker) {
        try {
            List<String> commandLine = inputView.readCommandLine();
            GameCommand gameCommand = GameCommand.of(commandLine);
            if(gameCommand == GameCommand.MOVE || gameCommand == GameCommand.STATUS) {
                throw new IllegalArgumentException("게임이 시작되지 않았습니다");
            }
            gameCommandInvoker.generate(gameCommand, commandLine);
            return gameCommand;
        }catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readStartCommand(gameCommandInvoker);
        }
    }

    private GameCommand runByCommand(GameCommandInvoker gameCommandInvoker) {
        try {
            List<String> commandLine = inputView.readCommandLine();
            GameCommand gameCommand = GameCommand.of(commandLine);
            gameCommandInvoker.generate(gameCommand, commandLine);
            return gameCommand;
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printExceptionMessage(e.getMessage());
            return runByCommand(gameCommandInvoker);
        }
    }

    private GameCommand checkGameFinished(GameCommand gameCommand) {
        if(chessGameService.isFinished()) {
            return GameCommand.END;
        }
        return gameCommand;
    }

}

