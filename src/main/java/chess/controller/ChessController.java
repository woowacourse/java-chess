package chess.controller;

import chess.board.ChessBoard;
import chess.board.Position;
import chess.dto.ChessBoardDto;
import chess.game.ChessGame;
import chess.game.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionConvertor;

import java.util.List;

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
        List<String> commands = inputView.readGameCommand();
        Command firstCommand = Command.from(commands.get(0));

        if (firstCommand == Command.START) {
            renderChessBoard();

            while (true) {
                List<String> inputs = inputView.readGameCommand();
                if (Command.from(inputs.get(0)) == Command.END) {
                    break;
                }

                Position from = PositionConvertor.convert(inputs.get(1));
                Position to = PositionConvertor.convert(inputs.get(2));
                chessGame.movePiece(from, to);
                renderChessBoard();
            }
        }
    }

    private void renderChessBoard() {
        ChessBoard chessBoard = chessGame.getChessBoard();
        ChessBoardDto chessBoardDto = ChessBoardDto.toView(chessBoard);
        outputView.printChessBoard(chessBoardDto);
    }

//    private <T> T repeat(final Supplier<T> function) {
//        Optional<T> input;
//        do {
//            input = a(function);
//        } while (input.isEmpty());
//        return input.get();
//    }
//
//    private <T> Optional<T> a(final Supplier<T> function) {
//        try {
//            return Optional.of(function.get());
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return Optional.empty();
//        }
//    }
}
