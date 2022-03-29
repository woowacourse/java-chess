package chess.controller;

import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    public void run() {
        OutputView.printInitMessage();
        ChessGame chessGame = new ChessGame(new InitBoardStrategy());
        play(new Init(chessGame), chessGame);
    }

    private void play(State state, ChessGame chessGame) {
        while (state.isRun()) {
            printPlayingChessBoard(state, chessGame);
            state = state.go(InputView.inputCommend());
        }
        printStatusEnd(state, chessGame);
    }

    private void printPlayingChessBoard(State state, ChessGame chessGame) {
        if (state.isPlay()) {
            OutputView.printChessBoard(chessGame.getBoard().getSquares());
        }
    }

    private void printStatusEnd(State state, ChessGame chessGame) {
        if (state.isStatusFinished()) {
            OutputView.printStatus(chessGame.getStatus());
        }
    }
}
