package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.view.OutputView;
import java.util.Map;

public class StatusController implements Controller {

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        Map<Color, Double> currentScore = chessGame.calculateCurrentScore();
        outputView.printStatus(currentScore);
    }
}
