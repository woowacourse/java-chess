package chess.controller;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Position;
import chess.view.Commend;
import chess.view.CommendDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        OutputView.printStartMessage();
        process();
    }

    private void process() {
        boolean isRunning = true;
        ChessGame chessGame = new ChessGame(new Board());
        Color turnColor = Color.BLACK;
        while (isRunning) {
            isRunning = processGame(chessGame, turnColor);
            turnColor = turnColor.opposite();
        }
    }

    private boolean processGame(ChessGame chessGame, Color currentTurn) {
        try {
            CommendDto commendDto = InputView.readCommend();
            Commend commend = commendDto.commend();
            if (commend == Commend.START) {
                handleStartCommend(chessGame);
            }
            if (commend == Commend.MOVE) {
                handleMoveCommend(chessGame, commendDto, currentTurn);
            }
            if (commend == Commend.END) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException error) {
            OutputView.printError(error);
            return processGame(chessGame, currentTurn);
        }
    }

    private void handleStartCommend(ChessGame chessGame) {
        OutputView.printBoard(chessGame.getBoard());
    }

    private void handleMoveCommend(ChessGame chessGame, CommendDto commendDto, Color currentTurn) {
        chessGame.handleMove(commendDto, currentTurn);
        OutputView.printBoard(chessGame.getBoard());
    }
}
