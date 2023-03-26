package chess.controller;

import chess.boardstrategy.BoardStrategy;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static chess.controller.Command.COMMAND_INDEX_IN_COMMANDLINE;

public class Controller {

    private static final Map<Command, BiConsumer<List<String>, BoardStrategy>> actionsByCommand = new EnumMap<>(Command.class);
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;

    public Controller(ChessGame chessGame) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.chessGame = chessGame;
        putActions();
    }

    private void putActions() {
        actionsByCommand.put(Command.START, this::start);
        actionsByCommand.put(Command.MOVE, this::move);
        actionsByCommand.put(Command.END, (ignored,ignored2) -> {});
        actionsByCommand.put(Command.STATUS, this::status);
    }

    public void playChessGame(BoardStrategy boardStrategy) {
        outputView.printStartGuideMessage();
        Command command = Command.START;
        while (command != Command.END && !chessGame.isFinished()) {
            command = playChessByCommand(boardStrategy);
        }
    }

    private Command playChessByCommand(BoardStrategy boardStrategy) {
        try {
            List<String> commandLine = inputView.readCommand();
            Command command = Command.findCommandByString(commandLine.get(COMMAND_INDEX_IN_COMMANDLINE));
            actionsByCommand.get(command).accept(commandLine, boardStrategy);
            return command;
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return playChessByCommand(boardStrategy);
        }
    }

    private void start(List<String> commandLine, BoardStrategy boardStrategy) {
        chessGame.start(boardStrategy);
        outputView.printBoard(chessGame.getChessBoard());
    }

    private void move(List<String> commandLine, BoardStrategy boardStrategy) {
        chessGame.move(commandLine);
        outputView.printBoard(chessGame.getChessBoard());
    }

    private void status(List<String> commandLine, BoardStrategy boardStrategy) {
        outputView.printStatus(chessGame.status());
    }

}
