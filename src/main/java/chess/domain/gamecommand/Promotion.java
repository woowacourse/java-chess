package chess.domain.gamecommand;

import static chess.domain.gamecommand.Movement.COMMAND_DELIMITER;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class Promotion implements CommandStrategy {

    @Override
    public Board play(final Board board, final String command) {
        final int sourcePositionIndex = 1;
        final int promotionKindIndex = 2;

        String sourcePosition = command.split(COMMAND_DELIMITER)[sourcePositionIndex];
        String promotionKind = command.split(COMMAND_DELIMITER)[promotionKindIndex];
        return board.promotePawn(Position.from(sourcePosition), promotionKind);
    }
}
