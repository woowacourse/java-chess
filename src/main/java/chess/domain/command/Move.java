package chess.domain.command;

import chess.ChessGame;
import chess.domain.board.Positions;

public final class Move implements CommandGenerator {
    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable printBoardInfoToState) {
        final Positions movePositions = Positions.from(command);
        chessGame.move(movePositions);

        printBoardInfoToState.run();
    }
}
