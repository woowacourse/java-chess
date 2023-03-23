package chess.controller;

import chess.domain.chessGame.ChessBoard;
import chess.domain.chessGame.ChessBoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.request.CommandDto;
import chess.dto.response.ChessBoardDto;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;
import java.util.function.Supplier;

public final class ChessController {

    public void run() {
        OutputView.printStartMessage();
        CommandDto commandDto = repeat(() -> InputView.readInitialCommand());
        if (commandDto.getGameCommand() == GameCommand.START) {
            startGame();
        }
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return repeat(supplier);
        }
    }

    private void startGame() {
        ChessBoard chessBoard = setUpChessBoard();
        repeat(() -> playGame(chessBoard));
    }

    private void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            repeat(runnable);
        }
    }

    private void playGame(ChessBoard chessBoard) {
        CommandDto commandDto = InputView.readPlayingCommand();
        while (commandDto.getGameCommand() != GameCommand.END) {
            executeMoveCommand(chessBoard, commandDto);
            commandDto = InputView.readPlayingCommand();
        }
    }

    private ChessBoard setUpChessBoard() {
        ChessBoardGenerator generator = new ChessBoardGenerator();
        ChessBoard chessBoard = new ChessBoard(generator.generate());
        showChessBoardStatus(chessBoard);
        return chessBoard;
    }

    private void executeMoveCommand(ChessBoard chessBoard, CommandDto commandDto) {
        String startInput = commandDto.getStartPosition();
        String endInput = commandDto.getEndPosition();

        chessBoard.movePiece(Position.of(startInput), Position.of(endInput));
        showChessBoardStatus(chessBoard);
    }

    private void showChessBoardStatus(ChessBoard chessBoard) {
        Map<Position, Piece> chessBoardStatus = chessBoard.getChessBoard();
        ChessBoardDto chessBoardDto = ChessBoardDto.of(BoardToString.convert(chessBoardStatus));
        OutputView.printChessBoard(chessBoardDto);
    }
}
