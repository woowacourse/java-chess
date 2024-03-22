package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class BlackState extends MoveState {

    @Override
    public GameState move(Board board, Position source, Position target) {
        board.move(source, target, Color.BLACK);

        return new WhiteState();
    }
}
