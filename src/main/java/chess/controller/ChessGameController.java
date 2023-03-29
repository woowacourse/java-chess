package chess.controller;

import chess.status.GameStatus;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.Map;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<CommandType, PrintAction> commandToPrintAction;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commandToPrintAction = new EnumMap<>(CommandType.class);
    }

    public void run() {
        setPrintActions();
        GameStatus gameStatus = GameStatus.getInitialStatus();
        Command gameCommand = Command.EMPTY_COMMAND;

        outputView.printInstructions();

        while (gameCommand.getCommandType() != CommandType.END) {
            try {
                gameCommand = inputView.readCommand();
                final PrintAction printAction = commandToPrintAction.get(gameCommand.getCommandType());
                gameStatus = gameStatus.processCommand(gameCommand, printAction);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setPrintActions() {
        commandToPrintAction.put(CommandType.START, chessBoardDto -> outputView.printChessBoard((ChessBoardDto) chessBoardDto));
        commandToPrintAction.put(CommandType.MOVE, chessBoardDto -> outputView.printChessBoard((ChessBoardDto) chessBoardDto));
        commandToPrintAction.put(CommandType.STATUS, resultDto -> outputView.printResult((ResultDto) resultDto));
    }
}
