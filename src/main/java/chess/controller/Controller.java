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

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;

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

    public void run(BoardStrategy boardStrategy) {
        outputView.printStartGuideMessage();
        do {
            playByCommand(boardStrategy);
        } while (chessGameService.isRunning());
        finishGameByCommand(boardStrategy);
    }

    private void playByCommand(BoardStrategy boardStrategy) {
        try {
            List<String> commandLine = inputView.readCommandLine();
            Command command = Command.findCommandByCommandLine(commandLine);
            actions.get(command).accept(commandLine, boardStrategy);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
        }
    }

    public void start(List<String> commandLine, BoardStrategy boardStrategy) {
        chessGameService.start();
        outputView.printBoard(chessGameService.findChessBoard(boardStrategy));
    }

    public void move(List<String> commandLine, BoardStrategy boardStrategy) {
        chessGameService.move(commandLine, boardStrategy);
        outputView.printBoard(chessGameService.findChessBoard(boardStrategy));
    }

    private void status(final List<String> commandLine, final BoardStrategy boardStrategy) {
        outputView.printStatus(chessGameService.findStatus(boardStrategy));
    }

    private void end(final List<String> commandLine, final BoardStrategy boardStrategy) {
        chessGameService.end();
    }

    private void finishGameByCommand(BoardStrategy boardStrategy) {
        if (chessGameService.isFinished()) {
            outputView.printWinner(chessGameService.findWinner(boardStrategy));
        }
    }


}

