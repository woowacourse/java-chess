package chess.domain.piece;

import chess.domain.*;
import chess.domain.direction.Direction;
import chess.domain.direction.LeftDiagonalDirection;
import chess.domain.direction.RightDiagonalDirection;
import chess.domain.direction.VerticalDirection;

import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    private final MoveRule rule;
    private final MoveRule firstRule;
    private final MoveRule attactRule;

    public Pawn(Aliance aliance, PieceValue pieceValue) {
        super(aliance, pieceValue);

        List<Direction> possibleDirections = Arrays.asList(VerticalDirection.getInstance());
        List<Direction> attackDirections = Arrays.asList(VerticalDirection.getInstance(),
                RightDiagonalDirection.getInstance(), LeftDiagonalDirection.getInstance());
        this.rule = new MoveRule(possibleDirections, 1);
        this.firstRule = new MoveRule(possibleDirections,2);
        this.attactRule = new MoveRule(attackDirections,1);
    }

    @Override
    public void checkPossibleMove(Board board, Position startPosition, Navigator navigator) {
        if (isReverse(navigator)) {
            throw new RuntimeException("폰은 뒤로 움직일 수 없습니다.");
        }

        if (isFirstMove(startPosition)) {
            firstRule.judge(navigator);
            return;
        }

        if (isAttackMove(board, startPosition)) {
            attactRule.judge(navigator);
            return;
        }
        rule.judge(navigator);
    }

    private boolean isReverse(Navigator navigator){
        if(this.aliance == Aliance.WHITE){
            return navigator.isReverse();
        }
        return !navigator.isReverse();
    }

    private boolean isFirstMove(Position startPosition) {
        if (this.aliance == Aliance.WHITE) {
            return startPosition.toString().charAt(1) == '2';
        }
        return startPosition.toString().charAt(1) == '7';
    }

    private boolean isAttackMove(Board board, Position startPosition) {
        Position attackablePosition1 = startPosition.movePosition(1,1);
        Position attackablePosition2 = startPosition.movePosition(1,-1);
        Piece attackablePiece1 = getAttackablePiece(board,attackablePosition1);
        Piece attackablePiece2 = getAttackablePiece(board,attackablePosition2);

        return ((attackablePiece1 != null) && (attackablePiece1.getAliance() != board.getThisTurn()))
                || ((attackablePiece2 != null) && (attackablePiece2.getAliance() != board.getThisTurn()));
    }

    private Piece getAttackablePiece(Board board,Position position){
        if(position == null){
            return null;
        }
        return board.pieceValueOf(position.toString());
    }

    @Override
    public String toString() {
        if (aliance == Aliance.BLACK) {
            return pieceValue.getName();
        }
        return pieceValue.getName().toLowerCase();
    }

}

