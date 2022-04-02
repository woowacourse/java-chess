package chess.domain.gamecommand;

import chess.domain.board.Board;

public class Start implements CommandStrategy {

    @Override
    public Board play(final Board board, final String command) {
        throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
    }
}
