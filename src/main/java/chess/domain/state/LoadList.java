package chess.domain.state;

import chess.domain.Board;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.point.Points;
import chess.dto.Command;
import chess.dto.MovementDto;

import java.util.List;

public class LoadList extends State {
    public LoadList(final long gameId, final Chess chess) {
        super(gameId, chess);
    }

    @Override
    public State start(final long gameId) {
        return new Start(gameId, Chess.create(Board.create(), Points.create()));
    }

    @Override
    public State move(final Command command) {
        throw new UnsupportedOperationException("LoadList에서 사용할 수 없는 move 메서드입니다.");
    }

    @Override
    public State end() {
        return new End(gameId, chess);
    }

    @Override
    public State status() {
        throw new UnsupportedOperationException("LoadList에서 사용할 수 없는 status 메서드입니다.");
    }

    @Override
    public State loadList() {
        return new LoadList(gameId, chess);
    }

    @Override
    public State load(final List<MovementDto> movements) {
        final Chess newChess = Chess.create(Board.create(), Points.create());
        movements.stream()
                .filter(movement -> movement.getPlayer().isNotEmpty())
                .forEach(movement -> newChess.move(movement.getSource(), movement.getTarget(), movement.getPlayer()));
        final Color player = movements.get(movements.size() - 1).getPlayer();
        if (player.isBlack()) {
            return new White(gameId, newChess);
        }
        return new Black(gameId, newChess);
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
