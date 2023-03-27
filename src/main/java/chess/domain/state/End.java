package chess.domain.state;

import chess.domain.Chess;
import chess.dto.Command;
import chess.dto.MovementDto;

import java.util.List;

public final class End extends State {
    public End(final long gameId, final Chess chess) {
        super(gameId, chess);
    }

    @Override
    public State start(final long gameId) {
        throw new UnsupportedOperationException("End에서 사용할 수 없는 start 메서드입니다.");
    }

    @Override
    public State move(final Command command) {
        throw new UnsupportedOperationException("End에서 사용할 수 없는 move 메서드입니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("End에서 사용할 수 없는 end 메서드입니다.");
    }

    @Override
    public State status() {
        throw new UnsupportedOperationException("End에서 사용할 수 없는 status 메서드입니다.");
    }

    @Override
    public State loadList() {
        return new LoadList(gameId, chess);
    }

    @Override
    public State load(final List<MovementDto> movements) {
        throw new UnsupportedOperationException("End에서 사용할 수 없는 load 메서드입니다.");
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }
}
