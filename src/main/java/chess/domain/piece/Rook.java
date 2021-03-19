package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {
    private static final String ROOK_NAME = "R";
    private static final double SCORE = 5;

    public Rook(Team team) {
        super(ROOK_NAME, team, SCORE);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();
        Vertical targetVertical = target.getVertical();
        Horizontal targetHorizontal = target.getHorizontal();

        for (Horizontal horizontal : Horizontal.values()) {
            movablePositions.add(Position.of(horizontal, targetVertical));
        }
        for (Vertical vertical : Vertical.values()) {
            movablePositions.add(Position.of(targetHorizontal, vertical));
        }

        movablePositions.removeAll(Collections.singletonList(target));

        return movablePositions;
    }

    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Direction direction = target.directionToDestination(destination);
        Position movedPosition = target;
        while (true) {
            movedPosition = movedPosition.moveTowardDirection(direction);

            if (movedPosition != destination) {
                if (board.getBoard().get(movedPosition) != null) {
                    return false;
                }
            }
            if (movedPosition == destination) {
                Piece targetPiece = board.getBoard().get(target);
                Piece destinationPiece = board.getBoard().get(movedPosition);
                return destinationPiece == null || !destinationPiece.isSameTeam(targetPiece);
            }
        }
    }
}
