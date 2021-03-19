package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Optional;

public class ForwardStrategy implements MoveStrategy {

    @Override
    public boolean canGoFrom(Position from, Position to) {
        return false;
    }
}
