package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.PieceColor;
import chess.domain.square.Square;
import chess.view.Command;
import chess.view.CommandType;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Optional;
import java.util.function.Supplier;

public class ChessGame {

    private static final String ERROR_NOT_STARTED = "아직 게임이 시작되지 않았습니다.";
    private static final String ERROR_ALREADY_STARTED = "이미 게임이 시작되었습니다.";
    private static final PieceColor START_TURN = PieceColor.WHITE;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Command command = processStartAndGetCommand();
        Board board = BoardFactory.createBoard();
        outputView.printBoard(board.generatePieceDrawings());
        processMove(command, board);
    }

    private Command processStartAndGetCommand() {
        Command command;
        do {
            command = requestUntilValid(this::requestStart);
        } while (!command.isType(CommandType.START));
        return command;
    }

    private void processMove(Command command, Board board) {
        PieceColor turn = START_TURN;
        while (!command.isType(CommandType.END)) {
            command = requestUntilValid(this::requestMove);
            turn = processMoveAndGetNextTurn(command, board, turn);
        }
    }

    private Command requestStart() {
        Command command = requestCommand();
        if (command.isType(CommandType.MOVE)) {
            throw new IllegalArgumentException(ERROR_NOT_STARTED);
        }
        return command;
    }

    private Command requestCommand() {
        return requestUntilValid(() -> Command.from(inputView.readCommand()));
    }

    private Command requestMove() {
        Command command = requestCommand();
        if (command.isType(CommandType.START)) {
            throw new IllegalArgumentException(ERROR_ALREADY_STARTED);
        }
        return command;
    }

    private PieceColor processMoveAndGetNextTurn(Command command, Board board, PieceColor turn) {
        try {
            Square source = Square.from(command.getArgument(1));
            Square target = Square.from(command.getArgument(2));
            board.move(source, target, turn);
            outputView.printBoard(board.generatePieceDrawings());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return turn;
        }
        return turn.opposite();
    }

    private <T> T requestUntilValid(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = tryGet(supplier);
        }
        return result.get();
    }

    private <T> Optional<T> tryGet(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
