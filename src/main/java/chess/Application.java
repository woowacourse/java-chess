package chess;

import chess.controller.ChessGameController;
import chess.domain.Score;
import chess.domain.Team;
import chess.domain.state.BoardInitialize;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController();
        chessGameController.run();
    }
}

