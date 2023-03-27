package chess.domain.state;

import chess.domain.Chess;
import chess.domain.Color;
import chess.dto.Command;
import chess.dto.MovementDto;

import java.util.List;

import static chess.domain.Color.WHITE;

public final class Start extends State {
    public Start(final long gameId, final Chess chess) {
        super(gameId, chess);
    }

    @Override
    public State start(final long gameId) {
        return new White(gameId, chess);
    }

    @Override
    public State move(final Command command) {
        return new White(gameId, chess).move(command);
    }

    @Override
    public State end() {
        return new End(gameId, chess);
    }

    @Override
    public State status() {
        return new Status(gameId, chess, WHITE);
    }

    @Override
    public State loadList() {
        return new LoadList(gameId, chess);
    }

    @Override
    public State load(final List<MovementDto> movements) {
        throw new UnsupportedOperationException("Start에서 사용할 수 없는 load 메서드입니다.");
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public Color getCurrentPlayer() {
        return WHITE;
    }
}
