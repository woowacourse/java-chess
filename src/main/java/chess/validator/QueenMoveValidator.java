package chess.validator;

import chess.Board;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.List;
import java.util.stream.Collectors;

public class QueenMoveValidator extends MoveValidator {

    @Override
    protected boolean isNotPermittedMovement(Board board, Position source, Position target) {
        return source.isNotStraight(target) && source.isNotDiagonal(target);
    }

    @Override
    protected List<Position> movePathExceptSourceAndTarget(Position source, Position target) {
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
