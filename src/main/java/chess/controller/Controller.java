package chess.controller;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public interface Controller {
    void execute(ChessGame chessGame, OutputView outputView);
}
