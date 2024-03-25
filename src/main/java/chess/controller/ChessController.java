package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardFactory;
import chess.domain.Position;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final ChessBoard chessBoard = ChessBoardFactory.makeChessBoard();
        outputView.printCommandInformation();
        playGame(chessBoard);
    }

    private void playGame(final ChessBoard chessBoard) {
        List<String> commandArguments = repeat(this::readInitialCommand);
        String gameCommand = commandArguments.get(COMMAND_INDEX);

        while (!GameCommand.isEndCommand(gameCommand)) {
            outputView.printChessBoard(chessBoard);
            playTurn(chessBoard, gameCommand, commandArguments);

            commandArguments = repeat(this::readInGameCommand);
            gameCommand = commandArguments.get(COMMAND_INDEX);
        }
    }

    private List<String> readInitialCommand() {
        List<String> commandArguments = inputView.readGameCommand();
        String gameCommand = commandArguments.get(COMMAND_INDEX);

        if (GameCommand.isMoveCommand(gameCommand)) {
            throw new IllegalArgumentException("[ERROR] 먼저 게임을 시작해야 합니다.");
        }

        return commandArguments;
    }

    private List<String> readInGameCommand() {
        List<String> commandArguments = inputView.readGameCommand();
        String gameCommand = commandArguments.get(COMMAND_INDEX);

        if (GameCommand.isStartCommand(gameCommand)) {
            throw new IllegalArgumentException("[ERROR] 이미 게임이 시작된 상태입니다.");
        }

        return commandArguments;
    }

    private void playTurn(final ChessBoard chessBoard,
                          final String gameCommand,
                          final List<String> commandArguments) {
        if (GameCommand.isMoveCommand(gameCommand)) {
            Position source = parsePosition(commandArguments.get(SOURCE_POSITION_INDEX));
            Position target = parsePosition(commandArguments.get(TARGET_POSITION_INDEX));
            chessBoard.move(source, target);
            outputView.printChessBoard(chessBoard);
        }
    }

    private Position parsePosition(final String rawPosition) {
        char file = rawPosition.substring(0, 1).charAt(0);
        int rank = Integer.parseInt(rawPosition.substring(1, 2));

        return Position.of(file, rank);
    }

    private <T> T repeat(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return repeat(supplier);
        }
    }
}
