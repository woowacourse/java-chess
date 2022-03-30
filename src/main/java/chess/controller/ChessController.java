package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.dto.RequestDto;
import chess.view.InputOption;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printGameInitMessage();
        while (!chessGame.isFinish()) {
            selectMenu(chessGame, InputView.inputOption());
        }
        try {
            OutputView.printWinner(chessGame.judgeWinner());
        } catch (IllegalStateException exception) {
            OutputView.printError(exception.getMessage());
        }
    }

    public void selectMenu(ChessGame chessGame, RequestDto dto) {
        InputOption option = dto.getInputOption();
        if (option == InputOption.START) {
            initBoard(chessGame);
            return;
        }
        if (option == InputOption.MOVE) {
            move(chessGame, dto.getFromPosition(), dto.getToPosition());
            return;
        }
        if (option == InputOption.STATUS) {
            showStatus(chessGame);
            return;
        }
        if (option == InputOption.END) {
            end(chessGame);
        }
    }

    public static void initBoard(ChessGame chessGame) {
        chessGame.initBoard();
        OutputView.printInitialChessBoard(chessGame.getBoard());
    }

    public static void move(ChessGame chessGame, String fromPosition, String toPosition) {
        try {
            chessGame.movePiece(fromPosition, toPosition);
            OutputView.printInitialChessBoard(chessGame.getBoard());
        } catch (IllegalStateException | IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
        }
    }

    public static void showStatus(ChessGame chessGame) {
        OutputView.printScore(chessGame.calculateScore(Color.WHITE),
            chessGame.calculateScore(Color.BLACK));
    }

    public static void end(ChessGame chessGame) {
        try {
            chessGame.endGame();
        } catch (IllegalStateException exception) {
            OutputView.printError(exception.getMessage());
        }
    }
}

