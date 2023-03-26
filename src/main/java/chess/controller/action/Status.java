package chess.controller.action;

import chess.controller.Command;
import chess.domain.Color;
import chess.domain.chessgame.ChessGame;
import chess.view.OutputView;

import java.util.Map;

public class Status implements GameAction {
    @Override
    public ChessGame execute(final Command command, final ChessGame chessGame) {
        final Map<Color, Double> scoreByColor = chessGame.calculateScoreByColor();
        OutputView.printScores(scoreByColor);
        OutputView.printWinner(chessGame.findScoreWinner());
        return chessGame;
    }
}
