package chess.controller;

import chess.board.ChessBoard;
import chess.dto.ChessBoardDto;
import chess.game.ChessGame;
import chess.game.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Optional;
import java.util.function.Supplier;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printInitGame();
        repeatCommand();
    }

    private void repeatCommand() {
        Command command = Command.START;
        while (Command.from(inputView.readGameCommand()) != Command.END) {
            ChessBoard chessBoard = chessGame.receiveCommand(command);
            ChessBoardDto chessBoardDto = ChessBoardDto.toView(chessBoard);
            outputView.printChessBoard(chessBoardDto);
        }
    }

    private <T> T repeat(Supplier<T> function) {
        Optional<T> input;
        do {
            input = a(function);
        } while (input.isEmpty());
        return input.get();
    }

    private <T> Optional<T> a(final Supplier<T> function) {
        try {
            return Optional.of(function.get());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
