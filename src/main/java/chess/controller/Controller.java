package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.stateLauncher.Init;
import chess.domain.game.stateLauncher.StateLauncher;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {

    public void run() {
        OutputView.printInitMessage();
        ChessGame chessGame = new ChessGame();
        StateLauncher stateLauncher = new Init(chessGame);
        play(stateLauncher, chessGame);
    }

    private void play(StateLauncher stateLauncher, ChessGame chessGame) {
        while (stateLauncher.isRun()) {
            printChessBoardIfPlay(stateLauncher, chessGame);
            stateLauncher = stateLauncher.go(InputView.inputCommend());
        }
        if (stateLauncher.isStatus()) {
            OutputView.printStatus(chessGame.getStatus());
        }
    }

    private void printChessBoardIfPlay(StateLauncher stateLauncher, ChessGame chessGame) {
        if (stateLauncher.isPlay()) {
            OutputView.printChessBoard(chessGame.getBoard());
        }
    }
}
