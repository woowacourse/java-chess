package chess.controller;

import chess.chessboard.*;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.function.Function;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessBoard chessBoard = startGame();
        GameStatus gameStatus = GameStatus.getInitialGameStatus();
        while (gameStatus.isPlaying()) {
            gameStatus = repeatUntilNoIAE(this::playTurn, chessBoard);
        }
    }

    private ChessBoard startGame() {
        outputView.printInstructions();
        return new ChessBoardFactory().generate();
    }

    private GameStatus playTurn(final ChessBoard chessBoard) {
        outputView.printChessBoard(ChessBoardDto.of(chessBoard));
        CommandDto commandDto = inputView.readCommand();
        GameStatus gamestatus = GameStatus.getNextStatus(commandDto.getCommand());
        if (gamestatus.isPlaying()) {
            movePiece(chessBoard, (MoveCommandDto) commandDto);
        }
        return gamestatus;
    }

    private void movePiece(final ChessBoard chessBoard, final MoveCommandDto moveCommandDto) {
        final Square source = getSourceSquare(moveCommandDto);
        final Square destination = getDestinationSquare(moveCommandDto);
        if (!chessBoard.executeTurnMove(source, destination)) {
            outputView.printInvalidMoveMessage();
        }
    }

    private Square getSourceSquare(final MoveCommandDto moveCommandDto) {
        final Rank sourceRank = moveCommandDto.getSourceRank();
        final File sourceFile = moveCommandDto.getSourceFile();
        return Square.of(sourceRank, sourceFile);
    }

    private Square getDestinationSquare(final MoveCommandDto moveCommandDto) {
        final Rank destinationRank = moveCommandDto.getDestinationRank();
        final File destinationFile = moveCommandDto.getDestinationFile();
        return Square.of(destinationRank, destinationFile);
    }

    private <T, R> R repeatUntilNoIAE(Function<T, R> function, T arg) {
        while (true) {
            try {
                return function.apply(arg);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
