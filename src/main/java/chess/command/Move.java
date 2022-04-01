package chess.command;

import chess.ChessGame;
import chess.domain.board.Positions;

public final class Move implements CommandStrategy {
    @Override
    public void execute(final String command, final ChessGame chessGame) {
        final Positions movePositions = Positions.from(command);
        chessGame.move(movePositions);
    }
}
