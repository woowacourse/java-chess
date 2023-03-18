package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.exception.ChessGameException;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.request.CommandType;
import chess.view.request.RequestInfo;
import chess.view.response.PieceResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGame chessGame;
    private final Map<CommandType, Consumer<List<String>>> commandTypeToAction = Map.of(
            CommandType.START, ignored -> start(),
            CommandType.END, ignored -> end(),
            CommandType.MOVE, this::move
    );

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        chessGame = new ChessGame();
    }

    private void start() {
        chessGame.start();
        printBoard();
    }

    private void end() {
        chessGame.end();
    }

    public void run() {
        outputView.printInitialMessage();
        while (chessGame.isRunning()) {
            execute();
        }
    }

    private void execute() {
        try {
            RequestInfo requestInfo = repeat(inputView::inputGameCommand);
            Consumer<List<String>> action = commandTypeToAction.get(requestInfo.getCommandType());
            action.accept(requestInfo.getCommands());
        } catch (ChessGameException e) {
            outputView.printError(e);
        }
    }

    private void move(List<String> command) {
        try {
            chessGame.move(command);
            printBoard();
        } catch (ChessGameException e) {
            outputView.printError(e);
        }
    }

    private void printBoard() {
        List<List<PieceResponse>> boardResponse = makeBoardResponse();
        outputView.printBoard(boardResponse);
    }

    private List<List<PieceResponse>> makeBoardResponse() {
        return chessGame.getPieces()
                .stream()
                .map(row -> row.stream()
                        .map(PieceResponse::from)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private <T> T repeat(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = returnIfNoError(supplier);
        }
        return result.get();
    }

    private <T> Optional<T> returnIfNoError(Supplier<T> supplier) {
        Optional<T> result;
        try {
            result = Optional.of(supplier.get());
        } catch (Exception e) {
            outputView.printError(e);
            result = Optional.empty();
        }
        return result;
    }
}
