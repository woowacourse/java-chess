package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.utils.MoveValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractPiece implements Piece {
    private final Team team;
    private final String name;

    protected AbstractPiece(Team team, String name) {
        this.team = team;
        this.name = name;
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
    public List<Position> generate(Path path, boolean target) {
        final Direction direction = path.computeDirection();
        final Strategy strategy = strategy();
        strategy.moveTowards(direction);
        final int distance = path.computeDistance();
        MoveValidator.validateDistance(distance, strategy.moveRange());
        return generatePaths(path, direction, distance);
    }

    protected List<Position> generatePaths(Path path, Direction direction, int distance) {
        return IntStream.range(1, distance - 1).mapToObj(i -> path.move(direction, i)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name + "_" + team.toString();
    }
}
