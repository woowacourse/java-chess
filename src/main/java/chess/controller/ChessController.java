package chess.controller;

import chess.domain.chessGame.ChessBoard;
import chess.domain.chessGame.ChessBoardGenerator;
import chess.domain.position.Position;
import chess.dto.request.CommandDto;
import chess.dto.response.ChessBoardDto;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessController {

    public void run() {
        OutputView.printStartMessage();
        CommandDto commandDto = InputView.readInitialCommand();
        if (commandDto.getGameCommand() == GameCommand.START) {
            startGame();
        }
    }

    private void startGame() {
        ChessBoard chessBoard = setUpChessBoard();
        CommandDto commandDto = InputView.readPlayingCommand();

        while (commandDto.getGameCommand() != GameCommand.END) {
            executeMoveCommand(chessBoard, commandDto);
            commandDto = InputView.readPlayingCommand();
        }
    }

    private ChessBoard setUpChessBoard() {
        ChessBoardGenerator generator = new ChessBoardGenerator();
        ChessBoard chessBoard = new ChessBoard(generator.generate());
        OutputView.printChessBoard(ChessBoardDto.of(chessBoard.getChessBoard()));
        return chessBoard;
    }

    private void executeMoveCommand(ChessBoard chessBoard, CommandDto commandDto) {
        String startInput = commandDto.getStartPosition();
        String endInput = commandDto.getEndPosition();

        chessBoard.movePiece(Position.of(startInput), Position.of(endInput));
        OutputView.printChessBoard(ChessBoardDto.of(chessBoard.getChessBoard()));
    }
}
