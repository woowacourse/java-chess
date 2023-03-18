package chess.controller;

import chess.domain.game.Board;
import chess.domain.game.Position;
import chess.domain.piece.exception.IllegalPieceMoveException;
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

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int ORIGIN_INDEX = 1;
    private static final int DEST_INDEX = 2;

    private final OutputView outputView;
    private final InputView inputView;
    private Board board;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printInitialMessage();
        while (true) {
            RequestInfo requestInfo = repeat(inputView::inputGameCommand);
            if (requestInfo.getCommandType() == CommandType.END) {
                return;
            }
            if (requestInfo.getCommandType() == CommandType.START) {
                gameStart();
                return;
            }
            outputView.printUnsuitableCommand();
        }
    }

    private void gameStart() {
        board = new Board();
        printBoard();
        RequestInfo requestInfo;
        do {
            requestInfo = repeat(inputView::inputGameCommand);
            if (requestInfo.getCommandType() == CommandType.END) {
                return;
            }
            if (requestInfo.getCommandType() == CommandType.MOVE) {
                move(requestInfo.getCommands());
                continue;
            }
            outputView.printUnsuitableCommand();
        } while (requestInfo.isRunning());
    }

    private void move(List<String> command) {
        try {
            board.movePiece(makePosition(command.get(ORIGIN_INDEX)), makePosition(command.get(DEST_INDEX)));
        } catch (IllegalPieceMoveException e) {
            outputView.printError(e);
        }
        printBoard();
    }

    private Position makePosition(String input) {
        return Position.of(input.charAt(FILE_INDEX), input.charAt(RANK_INDEX));
    }

    private void printBoard() {
        List<List<PieceResponse>> pieceResponses = makePieceResponses();
        outputView.printBoard(pieceResponses);
    }

    private List<List<PieceResponse>> makePieceResponses() {
        return board.getPieces()
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
