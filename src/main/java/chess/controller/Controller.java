package chess.controller;

import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;
import chess.dto.BoardDto;
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
            printStatus(state, chessGame);
            state = state.go(InputView.inputCommend());
        }
    }

    private void printStatus(State state, ChessGame chessGame) {
        if (state.isStatus()) {
            OutputView.printStatus(chessGame.getScoreOfTeams(), chessGame.getWinner());
        }
    }

    private void printPlayingChessBoard(State state, ChessGame chessGame) {
        if (state.isPlay()) {
            OutputView.printChessBoard(new BoardDto(chessGame.getBoard()));
        }
    }
}
