package chess.domain.strategy;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.TWO;

import chess.domain.ChessBoard;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public final class PawnMoveStrategy implements MoveStrategy {

    @Override
    public void isMovable(Position source, Position target, ChessBoard chessBoard) {
        if (source.isSameRank(target)) {
            throw new IllegalArgumentException("제자리에 머무를 수 없습니다.");
        }

        if (!checkPawnMoveDistance(source, target, chessBoard)) {
            throw new IllegalArgumentException("잘못된 이동입니다.");
        }

        if (source.isDiagonal(target) && (chessBoard.findTeam(source) == chessBoard.findTeam(target))) {
            throw new IllegalArgumentException("잘못된 이동입니다.");
        }

        checkPawnDirection(source, target, chessBoard);
        checkPawnStraightDirection(source, target, chessBoard);
    }

    private boolean checkPawnMoveDistance(Position source, Position target, ChessBoard chessBoard) {
        return (source.calculateDistance(target) == 1)
                || (source.calculateDistance(target) == 2 && source.getRank() == TWO
                && chessBoard.findTeam(source) == WHITE)
                || (source.calculateDistance(target) == 2 && source.getRank() == SEVEN
                && chessBoard.findTeam(source) == BLACK);
    }

    private void checkPawnDirection(Position source, Position target, ChessBoard chessBoard) {
        if (chessBoard.findTeam(source) == WHITE && source.isReductionRank(target)) {
            throw new IllegalArgumentException("잘못된 이동입니다.");
        }

        if (chessBoard.findTeam(source) == BLACK && source.isIncreaseRank(target)) {
            throw new IllegalArgumentException("잘못된 이동입니다.");
        }
    }

    private void checkPawnStraightDirection(Position source, Position target, ChessBoard chessBoard) {
        Direction direction = Direction.of(source, target);

        if (chessBoard.isExist(target) && direction.isLinearDirection()) {
            throw new IllegalArgumentException("잘못된 이동입니다.");
        }
    }
}
