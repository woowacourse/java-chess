package chess.domain.moveStrategy;

import chess.domain.game.Board;
import chess.domain.game.Command;
import chess.domain.location.Position;
import chess.domain.piece.Color;

import java.util.List;

public class PawnMove implements MoveStrategy{
    private Color color;

    public PawnMove(Color color) {
        this.color = color;
    }

    @Override
    public List<Position> movablePositions(Position from, Board board) {
        return null;
    }
}
