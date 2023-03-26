package chess.controller.action;

import chess.controller.Command;
import chess.domain.Position;
import chess.domain.chessgame.ChessGame;
import chess.view.OutputView;

public class Move implements GameAction {
    @Override
    public ChessGame execute(final Command command, ChessGame chessGame) {
        command.validateCommandSize();
        final Position currentPosition = command.getCurrentPosition();
        final Position targetPosition = command.getTargetPosition();

        chessGame = chessGame.move(currentPosition, targetPosition);

        OutputView.printBoard(chessGame.getPieces());
        return chessGame;
    }
}
