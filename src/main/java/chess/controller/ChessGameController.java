package chess.controller;

import chess.domain.Board;
import chess.domain.ChessGame;
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
        while (isRunning) {
            isRunning = processGame(chessGame);
        }
    }

    private boolean processGame(ChessGame chessGame) {
        try {
            CommendDto commendDto = InputView.readCommend();
            Commend commend = commendDto.commend();
            if (commend == Commend.START) {
                handleStartCommend(chessGame);
            }
            if (commend == Commend.MOVE) {
                handleMoveCommend(chessGame, commendDto.from(), commendDto.to());
            }
            if (commend == Commend.END) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException error) {
            OutputView.printError(error);
            return processGame(chessGame);
        }
    }

    private void handleStartCommend(ChessGame chessGame) {
        OutputView.printBoard(chessGame.getBoard());
    }

    private void handleMoveCommend(ChessGame chessGame, String fromValue, String toValue) {
        Position from = Position.from(fromValue);
        Position to = Position.from(toValue);

        List<Position> movablePositions = chessGame.generateMovablePositions(from);
        chessGame.movePiece(movablePositions, from, to);

        OutputView.printBoard(chessGame.getBoard());
    }
}
