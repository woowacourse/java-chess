package chess.model.board;

import chess.model.Type;

public enum DefaultType implements Type {

    EMPTY;

    @Override
    public boolean isPawn() {
        return false;
    }
}
