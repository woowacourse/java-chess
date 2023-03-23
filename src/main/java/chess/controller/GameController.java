package chess.controller;

import static chess.domain.Command.*;
import static chess.domain.Team.WHITE;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class GameController {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        start();
        ChessGame chessGame = createGame();
        play(chessGame);
    }

    public void start() {
        outputView.printGameStartNotice();
        readUntilValidate(this::enterStartCommand);
    }

    private Command enterStartCommand() {
        List<String> gameCommand = inputView.readGameCommand();
        Command command = Command.from(gameCommand);
        command.validateContains(START);
        return command;
    }

    public ChessGame createGame() {
        ChessGame chessGame = new ChessGame(new Board(), WHITE);
        printChessBoard(chessGame);
        return chessGame;
    }

    public void play(final ChessGame chessGame) {
        Command command;
        do {
            command = readUntilValidate(() -> progressGame(chessGame));
        } while (command != END);
    }

    private Command progressGame(final ChessGame chessGame) {
        List<String> gameCommand = inputView.readGameCommand();
        Command command = getCommandForPlaying(gameCommand);
        if (command == END) {
            return command;
        }
        move(chessGame, gameCommand);
        return command;
    }

    private Command getCommandForPlaying(final List<String> gameCommand) {
        Command command = Command.from(gameCommand);
        command.validateContains(MOVE, END);
        return command;
    }

    private void move(final ChessGame chessGame, final List<String> gameCommand) {
        chessGame.movePiece(gameCommand.get(SOURCE_INDEX), gameCommand.get(TARGET_INDEX));
        printChessBoard(chessGame);
    }

    private void printChessBoard(final ChessGame chessGame) {
        Board board = chessGame.getBoard();
        BoardDto boardDto = BoardDto.of(board);
        outputView.printChessBoard(boardDto.getPieces());
    }

    private <T> T readUntilValidate(Supplier<T> supplier) {
        T input;
        do {
            input = readUserInput(supplier);
        } while (input == null);
        return input;
    }

    private <T> T readUserInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
