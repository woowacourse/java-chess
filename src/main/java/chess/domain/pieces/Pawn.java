package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.MoveVector;
import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private List<Direction> moveDirection;
    private List<Direction> attackDirection;

    public Pawn(ChessTeam team) {
        super(team, PieceInfo.Pawn, null);
        moveDirection = new ArrayList<>();
        attackDirection = new ArrayList<>();
        if(team.color() == ChessTeam.WHITE.color()){
            addWhiteDirection();
        }
        if(team.color() == ChessTeam.BLACK.color()){
            addBlackDirection();
        }

    }

    private void addWhiteDirection() {
        moveDirection.add(MoveVector.North.getDirection());
        moveDirection.add(MoveVector.PawnFirstNorth.getDirection());

        attackDirection.add(MoveVector.NorthEast.getDirection());
        attackDirection.add(MoveVector.NorthWest.getDirection());
    }


    private void addBlackDirection() {
        moveDirection.add(MoveVector.South.getDirection());
        moveDirection.add(MoveVector.PawnFirstSouth.getDirection());

        attackDirection.add(MoveVector.SouthEast.getDirection());
        attackDirection.add(MoveVector.SouthWest.getDirection());
    }


    @Override
    public Direction move(Point p1, Point p2) {
        Direction direction = p1.direction(p2);
        if (moveDirection.contains(direction)) {
            initialMove();
            return direction;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Direction attack(Point p1, Point p2) {
        Direction direction = p1.direction(p2);
        if (attackDirection.contains(direction)) {
            initialMove();
            return direction;
        }
        throw new IllegalArgumentException();
    }

    private void initialMove() {
        if (moveDirection.size() == 2) {
            moveDirection.remove(1);
        }
    }
}
