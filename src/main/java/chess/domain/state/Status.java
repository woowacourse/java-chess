package chess.domain.state;

import chess.domain.Board;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.point.Points;
import chess.dto.Command;
import chess.dto.MovementDto;

import java.util.List;

public class Status extends State {
    private final Color current;

    public Status(final long gameId, final Chess chess, final Color current) {
        super(gameId, chess);
        this.current = current;
    }

    @Override
    public State start(final long gameId) {
        return new Start(gameId, Chess.create(Board.create(), Points.create()));
    }

    @Override
    public State move(final Command command) {
        if (current == Color.WHITE) {
            return new White(gameId, chess).move(command);
        }
        return new Black(gameId, chess).move(command);
    }

    @Override
    public State end() {
        return new End(gameId, chess);
    }

    @Override
    public State status() {
        return new Status(gameId, chess, current);
    }

    @Override
    public State loadList() {
        return new LoadList(gameId, chess);
    }

    @Override
    public State load(final List<MovementDto> movements) {
        throw new UnsupportedOperationException("Status에서 사용할 수 없는 load 메서드입니다.");
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public Color getCurrentPlayer() {
        return current;
    }
}
