package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;

public class BlackTurn extends Running {

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public State move(Position source, Position target) {
        return new WhiteTurn(board());
    }
}
