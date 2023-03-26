package chess.domain.state;

import chess.domain.Board;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.point.Points;
import chess.dto.Command;

public class Status extends State {
    private final Color current;

    public Status(final Chess chess, final Color current) {
        super(chess);
        this.current = current;
    }

    @Override
    public State start() {
        return new Start(Chess.create(Board.create(), Points.create()));
    }

    @Override
    public State move(final Command command) {
        if (current == Color.WHITE) {
            return new White(chess).move(command);
        }
        return new Black(chess).move(command);
    }

    @Override
    public State end() {
        return new End(chess);
    }

    @Override
    public State status() {
        return new Status(chess, current);
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
