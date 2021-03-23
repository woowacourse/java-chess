package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.utils.MoveValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractPiece implements Piece {
    private final Team team;

    protected AbstractPiece(Team team) {
        this.team = team;
    }

    protected boolean isBlackTeam() {
        return team.isBlack(team);
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public void confirmTurn(Team team) {
        if (this.team != team) {
            throw new IllegalArgumentException("[ERROR] 상대 팀의 차례입니다.");
        }
    }

    @Override
    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    @Override
    public List<Position> generate(Path path) {
        final Direction direction = path.computeDirection();
        final Strategy strategy = strategy();
        strategy.moveTowards(direction); // 전략 상 갈 수 있는 방향인지 확인함.
        final int distance = path.computeDistance();
        MoveValidator.validateMoveRange(distance, strategy.moveRange()); // 이동할 수 있는 범위 내의 값인지 확인함.
        return generatePaths(path, direction, distance);
    }

    // 그 방향으로 이동해서 갈 수 있는 값들을 모두 제너레이트 함!
    protected List<Position> generatePaths(Path path, Direction direction, int distance) {
        return IntStream.range(1, distance - 1).mapToObj(i -> path.move(direction, i)).collect(Collectors.toList());
    }
}
