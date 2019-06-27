package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.MoveVector;
import chess.domain.Point;

import java.util.List;

public class Pawn extends SingleMovePiece {
    private List<MoveVector> moveDirection;
    private List<MoveVector> attackDirection;

    public Pawn(ChessTeam team) {
        super(team, PieceInfo.Pawn, null);
        if (team.color() == ChessTeam.WHITE.color()) {
            addWhiteDirection();
        }
        if (team.color() == ChessTeam.BLACK.color()) {
            addBlackDirection();
        }

    }

    private void addWhiteDirection() {
        moveDirection = MoveVector.whitePawnMoveDirection();
        attackDirection = MoveVector.whitePawnAttackDirection();
    }


    private void addBlackDirection() {
        moveDirection = MoveVector.blackPawnMoveDirection();
        attackDirection = MoveVector.blackPawnAttackDirection();
    }


    @Override
    public Direction move(Point p1, Point p2) {
        MoveVector vector = MoveVector.findVector(p1.direction(p2));
        if (moveDirection.contains(vector)) {
            initialMove();
            return vector.getDirection();
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Direction attack(Point p1, Point p2) {
        MoveVector vector = MoveVector.findVector(p1.direction(p2));
        if (attackDirection.contains(vector)) {
            initialMove();
            return vector.getDirection();
        }
        throw new IllegalArgumentException();
    }

    private void initialMove() {
        if (moveDirection.size() == 2) {
            moveDirection = moveDirection.subList(0, 1);
        }
    }
}
