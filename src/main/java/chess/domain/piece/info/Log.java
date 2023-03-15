package chess.domain.piece.info;

import chess.domain.position.Position;

public class Log {

    private final int turn;
    // TODO: 2023/03/15 원시값 포장하기
    private final Position position;

    Log(final int turn, final Position position) {
        this.turn = turn;
        this.position = position;
    }
}
