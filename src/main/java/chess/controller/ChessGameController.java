package chess.controller;

import chess.status.GameStatus;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.Map;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<CommandType, PrintAction> commandToAction;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commandToAction = new EnumMap<>(CommandType.class);
    }

    public void run() {
        setPrintActions();
        GameStatus gameStatus = GameStatus.getInitialStatus();

        outputView.printInstructions();

        Command command = Command.EMPTY_COMMAND;

        while (command.getCommandType() != CommandType.END) {
            try {
                command = inputView.readCommand();
                final PrintAction printAction = commandToAction.get(command.getCommandType());
                gameStatus = gameStatus.playGame(command, printAction);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setPrintActions() {
        commandToAction.put(CommandType.START, chessBoardDto -> outputView.printChessBoard((ChessBoardDto) chessBoardDto));
        commandToAction.put(CommandType.MOVE, chessBoardDto -> outputView.printChessBoard((ChessBoardDto) chessBoardDto));
        commandToAction.put(CommandType.STATUS, resultDto -> outputView.printResult((ResultDto) resultDto));
    }
}
