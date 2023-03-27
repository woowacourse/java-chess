package chess.controller;

import chess.boardstrategy.BoardStrategy;
import chess.dao.ChessGameDao;
import chess.dao.MoveDao;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static chess.controller.Command.*;

public class Controller {
    private static final int NONE_GAME_ID = -1;
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;
    private int gameId = NONE_GAME_ID;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = new ChessGameService(new ChessGameDao(), new MoveDao());
    }

    private final Map<Command, BiConsumer<List<String>, BoardStrategy>> actions =
            Map.of(START, this::start,
                    MOVE, this::move,
                    STATUS, this::status,
                    END, this::end);


    /**
     * 1.start 실행시, 가장 최근의 game id
     */
    public void run(BoardStrategy boardStrategy) {
        outputView.printStartGuideMessage();
        Command command = startGame(boardStrategy);
        while (command != Command.END
                && !chessGameService.isFinished(gameId, boardStrategy)) {
            command = playChessByCommandRequest(boardStrategy);
        }
        finishGameByCommand(command, boardStrategy);
    }

    private Command startGame(BoardStrategy boardStrategy) {
        List<String> commandLine;
        Command command;
        do {
            commandLine = inputView.readCommandLine();
            command = Command.findCommandByCommandLine(commandLine);
        } while (command != Command.START);
        actions.get(command).accept(commandLine, boardStrategy);
        return command;
    }

    private Command playChessByCommandRequest(BoardStrategy boardStrategy) {
        try {
            List<String> commandLine = inputView.readCommandLine();
            Command command = Command.findCommandByCommandLine(commandLine);
            actions.get(command).accept(commandLine, boardStrategy);
            return command;
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return playChessByCommandRequest(boardStrategy);
        }
    }

    public void start(List<String> commandLine, BoardStrategy boardStrategy) {
        gameId = chessGameService.start();
        outputView.printBoard(chessGameService.findChessBoard(gameId, boardStrategy));
    }

    public void move(List<String> commandLine, BoardStrategy boardStrategy) {
        chessGameService.move(gameId, commandLine, boardStrategy);
        outputView.printBoard(chessGameService.findChessBoard(gameId, boardStrategy));
    }

    private void status(final List<String> commandLine, final BoardStrategy boardStrategy) {
        outputView.printStatus(chessGameService.findStatus(gameId, boardStrategy));
    }

    private void end(final List<String> commandLine, final BoardStrategy boardStrategy) {
        chessGameService.end(gameId, boardStrategy);
    }

    private void finishGameByCommand(Command command, BoardStrategy boardStrategy) {
        if (command == MOVE) {
            outputView.printWinner(chessGameService.findWinner(gameId, boardStrategy));
        }
    }

}
