package chess.controller.state;

import chess.domain.piece.Camp;

public class Ready extends State {
    public Ready() {
        super(Camp.WHITE);
    }

    @Override
    public Running start() {
        return new Running(turn());
    }

    @Override
    public End end() {
        return new End(turn());
    }
}
