package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final String ROOK_NAME = "R";

    public Rook(Team team) {
        super(ROOK_NAME, team);
    }

    @Override
    public List<Position> movablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();
        Vertical targetVertical = target.getVertical();
        Horizontal targetHorizontal = target.getHorizontal();

        for (Horizontal horizontal : Horizontal.values()) {
            movablePositions.add(Position.of(horizontal, targetVertical));
        }
        for (Vertical vertical : Vertical.values()) {
            movablePositions.add(Position.of(targetHorizontal, vertical));
        }

        return movablePositions;
    }
}