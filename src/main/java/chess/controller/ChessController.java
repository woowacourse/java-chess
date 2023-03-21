package chess.controller;

import chess.controller.command.*;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ChessController {
    private static final Map<String, CommandAction> commands = new HashMap<>();
    private static final int SOURCE_SQUARE_INDEX = 0;
    private static final int TARGET_SQUARE_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private Board board;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.board = BoardFactory.create();
        commands.put("start", new StartCommandAction(ignored -> initializeBoard()));
        commands.put("move", new MoveCommandAction(this::movePiece));
        commands.put("end", new EndCommandAction());
    }

    public void run() {
        showStartMessage();
        CommandAction currentAction = new WaitingCommandAction();

        while (currentAction.isRunnable()) {
            currentAction = repeat(this::play);
        }
    }

    private CommandAction play() {
        CommandAction currentAction;
        String inputCommand = inputView.readCommand();
        Command command = new Command(inputCommand);
        CommandAction action = commands.get(command.getName());
        action.execute(command);
        currentAction = action;
        return currentAction;
    }

    private CommandAction repeat(Supplier<CommandAction> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return repeat(supplier);
        }
    }

    public void initializeBoard() {
        board = BoardFactory.create();
        showBoard();
    }

    private void movePiece(final List<String> parameters) {
        Square sourceSquare = convertSquare(parameters.get(SOURCE_SQUARE_INDEX));
        Square targetSquare = convertSquare(parameters.get(TARGET_SQUARE_INDEX));
        board.makeMove(sourceSquare, targetSquare);
        showBoard();
    }

    private Square convertSquare(final String square) {
        return Square.from(square);
    }

    private void showStartMessage() {
        outputView.printStartMessage();
    }

    private void showBoard() {
        outputView.printBoard(convertBoard());
    }

    private List<List<Piece>> convertBoard() {
        List<List<Piece>> pieces = new ArrayList<>();
        List<File> files = List.of(File.values());
        List<Rank> ranks = List.of(Rank.values());
        for (Rank rank : ranks) {
            List<Piece> rankPieces = files.stream()
                    .map(file -> board.findPiece(file, rank))
                    .collect(Collectors.toList());
            pieces.add(rankPieces);
        }
        return pieces;
    }
}
