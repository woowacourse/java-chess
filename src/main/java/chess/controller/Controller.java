package chess.controller;

import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Init;
import chess.domain.game.state.AbstractState;
import chess.dto.BoardDto;
import chess.dto.CommandDto;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    public void run() {
        OutputView.printInitMessage();
        ChessGame chessGame = new ChessGame(new InitBoardStrategy());
        play(new Init(chessGame), chessGame);
    }

    private void play(AbstractState abstractState, ChessGame chessGame) {
        while (abstractState.isRun()) {
            printPlayingChessBoard(abstractState, chessGame);
            abstractState = abstractState.go(new CommandDto(InputView.inputCommend()));
        }
        printStatusEnd(abstractState, chessGame);
    }

    private void printPlayingChessBoard(AbstractState abstractState, ChessGame chessGame) {
        if (abstractState.isPlay()) {
            OutputView.printChessBoard(new BoardDto(chessGame.getBoard()));
        }
    }

    private void printStatusEnd(AbstractState abstractState, ChessGame chessGame) {
        if (abstractState.isStatusFinished()) {
            OutputView.printStatus(chessGame.getStatus());
        }
    }
}
