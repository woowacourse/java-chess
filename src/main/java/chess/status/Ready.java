package chess.status;

import chess.game.Board;
import chess.game.BoardInitializer;
import chess.game.Command;
import chess.piece.Color;

public final class Ready {

    private Ready() {
    }

    public static State start(final Command command) {
        if (command.isStart()) {
            final Board board = new Board(BoardInitializer.getBoard());
            return new Move(board, Color.WHITE);
        }
        if (command.isEnd()) {
            return new Finished();
        }

        throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
    }
}
