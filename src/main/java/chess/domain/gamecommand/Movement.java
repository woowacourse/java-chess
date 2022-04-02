package chess.domain.gamecommand;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class Movement implements CommandStrategy {
    static final String COMMAND_DELIMITER = " ";

    @Override
    public Board play(final Board board, final String command) {
        final int sourcePositionIndex = 1;
        final int targetPositionIndex = 2;

        String sourcePosition = command.split(COMMAND_DELIMITER)[sourcePositionIndex];
        String targetPosition = command.split(COMMAND_DELIMITER)[targetPositionIndex];
        return board.movePiece(Position.from(sourcePosition), Position.from(targetPosition));
    }
}
