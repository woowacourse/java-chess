package chess.controller.action;

import chess.controller.Command;
import chess.domain.chessgame.ChessGame;
import chess.view.OutputView;

public class Start implements GameAction {
    @Override
    public ChessGame execute(final Command command, ChessGame chessGame) {
        chessGame = chessGame.start();
        OutputView.printBoard(chessGame.getPieces(), chessGame.getCurrentTurnColor());
        return chessGame;
    }
}
