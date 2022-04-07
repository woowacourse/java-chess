package console.controller;

import chess.domain.board.Board;
import chess.domain.board.InitialBoardGenerator;
import chess.domain.game.Game;
import chess.domain.piece.Color;
import chess.request.BoardAndTurnInfo;
import chess.request.ScoreResponse;
import console.view.InputView;
import console.view.OutputView;

public class ConsoleController {

    public void run() {
        OutputView.printIntroduction();
        boolean isRunning = false;
        Game game = null;
        while (!isRunning) {
            game = tryStart(game);
            if (game != null) {
                isRunning = true;
            }
        }
        OutputView.printBoardAndTurn(new BoardAndTurnInfo(game.getPointPieces(), game.getTurnColor()));
        OutputView.printIntroduction();
        while (game.isRunnable() && isRunning) {
            isRunning = tryExecute(isRunning, game);
        }
        OutputView.printEnd();
    }

    private Game tryStart(Game game) {
        OutputView.printEnterCommand();
        Request request = Request.of(InputView.inputCommand());
        Command command = Command.find(request.getCommand());
        if (command.equals(Command.START)) {
            game = new Game(new Board(InitialBoardGenerator.generate()), Color.WHITE);
        }
        else {
            OutputView.printUnavailableExceptStart();
        }
        return game;
    }

    private boolean tryExecute(boolean isRunning, Game game) {
        OutputView.printEnterCommand();
        Request request = Request.of(InputView.inputCommand());
        Command command = Command.find(request.getCommand());
        if (command.equals(Command.START)) {
            OutputView.printAlreadyStarted();
        }
        if (command.equals(Command.MOVE)) {
            tryExecuteMove(game, request);
        }
        if (command.equals(Command.STATUS)) {
            OutputView.printStatus(new ScoreResponse(game.getPointPieces(), game.calculateScore()));
        }
        if (command.equals(Command.FINISH)) {
            isRunning = false;
        }
        return isRunning;
    }

    private void tryExecuteMove(Game game, Request request) {
        try {
            game.move(request.getArguments());
            OutputView.printBoardAndTurn(new BoardAndTurnInfo(game.getPointPieces(), game.getTurnColor()));
        } catch (RuntimeException e) {
            OutputView.printException(e);
        }
    }
}
