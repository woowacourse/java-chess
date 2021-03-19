package chess.domain.board;

import chess.domain.board.position.Position;

public class Path {

    private final Board board;
    private final Position position;

    public Path(Board board, Position position) {
        this.board = board;
        this.position = position;
    }
}
