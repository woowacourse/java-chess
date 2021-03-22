package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.team.Color;

public class WhiteTurn extends Running {
    private final Color color = Color.WHITE;

    public WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    @Override
    public State passTurn() {
        return new BlackTurn(board());
    }

    @Override
    public void move(Position source, Position target) {
        board().moveIfValid(source, target);
    }
}
