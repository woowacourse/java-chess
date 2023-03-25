package controller;

import controller.adapter.inward.Command;
import controller.adapter.inward.CommandArguments;
import controller.adapter.inward.CoordinateAdapter;
import controller.adapter.outward.RenderingAdapter;
import domain.board.ChessGame;
import domain.piece.move.Coordinate;
import service.ChessGameService;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;


    private final Map<Command, BiConsumer<ChessGame, CommandArguments>> commander;

    public ChessController(
            final InputView inputView,
            final OutputView outputView,
            final ChessGameService chessGameService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
        this.commander = new HashMap<>();
        initializeCommander();
    }

    private void initializeCommander() {
        commander.put(Command.START, this::start);
        commander.put(Command.END, this::end);
        commander.put(Command.MOVE, this::move);
        commander.put(Command.STATUS, this::status);
    }

    public void run() {
        outputView.printGameStartMessage();
        repeat(this::interact);
    }

    private void repeat(Runnable target) {
        try {
            target.run();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            repeat(target);
        }
    }

    private void interact() {
        ChessGame chessGame;
        Command command;
        do {
            chessGame = chessGameService.loadGame();
            List<String> pureArguments = inputView.readCommand();
            command = Command.of(pureArguments);
            CommandArguments commandArguments = CommandArguments.of(pureArguments);
            commander.get(command).accept(chessGame, commandArguments);
        } while (command.isNotEnd() && chessGame.isGameNotOver());
        printGameResultIfOver(chessGame);
    }

    private void start(final ChessGame chessGame, final CommandArguments ignored) {
        ChessGame newChessGame = chessGameService.startGame();
        printBoard(newChessGame);
    }

    private void end(final ChessGame chessGame, final CommandArguments ignored) {
        outputView.printGameEndMessage();
    }

    private void move(final ChessGame chessGame, final CommandArguments arguments) {
        Coordinate startCoordinate = CoordinateAdapter.convert(arguments.getRawStartCoordinate());
        Coordinate endCoordinate = CoordinateAdapter.convert(arguments.getRawEndCoordinate());
        chessGame.move(startCoordinate, endCoordinate);
        chessGameService.updateMove(startCoordinate, endCoordinate);
        printBoard(chessGame);
    }

    private void status(final ChessGame chessGame, final CommandArguments ignored) {
        outputView.printGameStatus(RenderingAdapter.unpackGameResult(chessGame.collectPoint()));
        printBoard(chessGame);
    }

    private void printBoard(final ChessGame chessGame) {
        String boardMessage = RenderingAdapter.unpackBoard(chessGame.getBoard());
        outputView.printBoard(boardMessage);
    }

    private void printGameResultIfOver(final ChessGame chessGame) {
        if (chessGame.isGameNotOver()) {
            return;
        }
        String gameResultMessage = RenderingAdapter.unpackGameResult(chessGame.collectPoint());
        String winningColorMessage = RenderingAdapter.convertWinningColor(chessGame.getWinningColor());
        outputView.printGameResult(gameResultMessage);
        outputView.printWinner(winningColorMessage);
    }
}
