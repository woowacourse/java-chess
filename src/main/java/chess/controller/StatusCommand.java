package chess.controller;

import chess.domain.game.state.ChessGame;
import chess.domain.piece.Color;
import chess.view.OutputView;

public class StatusCommand implements Command{
    @Override
    public ChessGame run(ChessGame chessGame) {
        OutputView.printScore(chessGame.calculateScore(Color.WHITE),
                chessGame.calculateScore(Color.BLACK));
        return chessGame;
    }
}
