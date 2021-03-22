package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;

public class WhiteTurn extends Running {

    public WhiteTurn(Board board) {
        super(board);
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
