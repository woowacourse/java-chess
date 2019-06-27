package chess.domain.piece;

import chess.domain.*;
import chess.domain.direction.Direction;
import chess.domain.direction.LeftDiagonalDirection;
import chess.domain.direction.RightDiagonalDirection;
import chess.domain.direction.VerticalDirection;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {
    private static final int LIMIT_MOVE_COUNT = 1;
    private static final int LIMIT_FIRST_MOVE_COUNT = 2;
    private static final Character WHITE_INIT_ROW = '2';
    private static final Character BLACK_INIT_ROW = '7';
    private static final int ATTACKABLE_ROW_DIFF = 1;
    private static final int ATTACKABLE_RIGNT_COLUMN_DIFF = 1;
    private static final int ATTACKABLE_LEFT_COLUMN_DIFF = -1;

    private final MoveRule rule;
    private final MoveRule firstRule;
    private final MoveRule attactRule;

    public Pawn(Aliance aliance, PieceValue pieceValue) {
        super(aliance, pieceValue);

        List<Direction> possibleDirections = Arrays.asList(VerticalDirection.getInstance());
        List<Direction> attackDirections = Arrays.asList(VerticalDirection.getInstance(),
                RightDiagonalDirection.getInstance(), LeftDiagonalDirection.getInstance());
        this.rule = new MoveRule(possibleDirections, LIMIT_MOVE_COUNT);
        this.firstRule = new MoveRule(possibleDirections, LIMIT_FIRST_MOVE_COUNT);
        this.attactRule = new MoveRule(attackDirections, LIMIT_MOVE_COUNT);
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

    private boolean isReverse(Navigator navigator) {
        if (this.aliance == Aliance.WHITE) {
            return navigator.isReverse();
        }
        return !navigator.isReverse();
    }

    private boolean isFirstMove(Position startPosition) {
        if (this.aliance == Aliance.WHITE) {
            return startPosition.toString().charAt(1) == WHITE_INIT_ROW;
        }
        return startPosition.toString().charAt(1) == BLACK_INIT_ROW;
    }

    private boolean isAttackMove(Board board, Position startPosition) {
        Position attackablePosition1 = startPosition.movePosition(ATTACKABLE_ROW_DIFF, ATTACKABLE_RIGNT_COLUMN_DIFF);
        Position attackablePosition2 = startPosition.movePosition(ATTACKABLE_ROW_DIFF, ATTACKABLE_LEFT_COLUMN_DIFF);
        Optional<Piece> attackablePiece1 = getAttackablePiece(board, attackablePosition1);
        Optional<Piece> attackablePiece2 = getAttackablePiece(board, attackablePosition2);

        return ((attackablePiece1.isPresent()) && (attackablePiece1.get().getAliance() != board.getThisTurn()))
                || ((attackablePiece2.isPresent()) && (attackablePiece2.get().getAliance() != board.getThisTurn()));
    }

    private Optional<Piece> getAttackablePiece(Board board, Position position) {
        Optional<Position> maybePosition = Optional.ofNullable(position);
        if(maybePosition.isPresent()){
            return board.pieceValueOf(position.toString());
        }
        Optional<Piece> emptyPiece = Optional.empty();
        return emptyPiece;
    }

    @Override
    public String toString() {
        if (aliance == Aliance.BLACK) {
            return pieceValue.getName();
        }
        return pieceValue.getName().toLowerCase();
    }
}

