package chess.domain.gamecommand;

import chess.domain.board.Board;

public interface CommandStrategy {

    Board play(final Board board, final String command);
}
