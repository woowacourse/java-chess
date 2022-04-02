package chess.controller;

import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;
import chess.domain.game.state.attribute.StateType;
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

    private void play(State state, ChessGame chessGame) {
        while (state.getType() != StateType.END) {
            printPlayingChessBoard(state, chessGame);
            printStatus(state, chessGame);
            state = go(state, InputView.inputCommend());
        }
    }

    private void printPlayingChessBoard(State state, ChessGame chessGame) {
        if (state.getType() == StateType.PLAY) {
            OutputView.printChessBoard(new BoardDto(chessGame.getBoard()));
        }
    }

    private void printStatus(State state, ChessGame chessGame) {
        if (state.getType() == StateType.STATUS) {
            OutputView.printStatus(chessGame.getScoreOfTeams(), chessGame.getWinner());
        }
    }

    private State go(State state, String input) {
        try {
            return state.execute(new CommandDto(input));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return state;
        }
    }
}
