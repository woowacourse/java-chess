package chess.controller.command;

import chess.domain.game.ChessGame;
import chess.domain.piece.property.Color;
import chess.view.OutputView;

public final class StatusCommand implements Command {

    private final OutputView outputView = new OutputView();

    @Override
    public void execute(final ChessGame chessGame) {
        double whiteScore = chessGame.computeScore(Color.WHITE);
        double blackScore = chessGame.computeScore(Color.BLACK);
        outputView.printScore(whiteScore, blackScore);
    }
}
