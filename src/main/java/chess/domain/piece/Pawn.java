package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.PawnMoveStrategy;
import chess.domain.postion.Position;

import java.util.List;

public class Pawn extends Piece{
    private final String symbol;

    public Pawn(final Team team, final String symbol) {
        super(team, new PawnMoveStrategy());
        this.symbol = symbol;
    }

    @Override
    public List<Direction> possibleDirections() {
        if (team().equals(Team.WHITE)) {

            return Direction.getWhitePawnDirection();
        }

       return Direction.getBlackPawnDirection();
    }

    @Override
    public void checkPawn(Position source, Position target, Direction direction, Piece other) {
        if (source.isTwoRankDifference(target)) {
            validateMoveTwoSquare(source);
        }

        if (source.isDiagonal(target)) {
            validateMoveDiagonal(other);
        }
    }

    private void validateMoveTwoSquare(Position source) {
        if(!source.isInitPawnRank()) {
            throw new IllegalArgumentException("폰은 초기 위치일 때만 두 칸이동 가능합니다.");
        }
    }

    private void validateMoveDiagonal(Piece other) {
        if(!isEnemy(other)) {
            throw new IllegalArgumentException("폰은 적이 있을 때만 대각선으로 이동 가능합니다.");
        }
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
