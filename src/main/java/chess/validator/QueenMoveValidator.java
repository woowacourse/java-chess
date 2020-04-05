package chess.validator;

import chess.Board;
import chess.exception.InvalidMovementException;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.List;
import java.util.stream.Collectors;

public class QueenMoveValidator extends MoveValidator {
    private boolean isPermittedMovement(Board board, Position source, Position target) {
        return source.isDiagonal(target) || source.isStraight(target);
    }

    @Override
    protected boolean isNotPermittedMovement(Board board, Position source, Position target) {
        return !isPermittedMovement(board, source, target);
    }

    @Override
    protected List<Position> movePathExceptSourceAndTarget(Position source, Position target) {
        if (source.isNotStraight(target) && source.isNotDiagonal(target)) {
            throw new InvalidMovementException("해당 위치로 이동할 수 없습니다.");
        }
        if (source.isSameRank(target)) {
            List<File> files = File.valuesBetween(source.getFile(), target.getFile());
            return files.stream()
                    .map(file -> Position.of(file, source.getRank()))
                    .collect(Collectors.toList());
        }
        if (source.isDiagonal(target)) {
            return Position.findDiagonalTrace(source, target);
        }
        List<Rank> ranks = Rank.valuesBetween(source.getRank(), target.getRank());
        return ranks.stream()
                .map(rank -> Position.of(source.getFile(), rank))
                .collect(Collectors.toList());
    }

    @Override
    protected boolean isKingKilledIfMoves(Board board, Position source, Position target) {
        return board.isKingKilledIfMoves(source, target);
    }
}
