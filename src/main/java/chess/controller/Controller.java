package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    public void run() {
        OutputView.printInitMessage();
        ChessGame chessGame = new ChessGame();
        State state = new Init(chessGame);
        play(state, chessGame);
    }

    private void play(State state, ChessGame chessGame) {
        while (state.isRun()) {
            printChessBoardIfPlay(state, chessGame);
            state = state.go(InputView.inputCommend());
        }
        if (state.isStatus()) {
            OutputView.printStatus(chessGame.getStatus());
        }
    }

    private void printChessBoardIfPlay(State state, ChessGame chessGame) {
        if (state.isPlay()) {
            OutputView.printChessBoard(chessGame.getBoard());
        }
    }
}
