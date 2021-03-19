package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;

public class BishopStrategy implements MoveStrategy {

    @Override
    public boolean canGoFrom(Position from, Position to) {
        return false;
    }
}
