package chess.domain.chess;

import chess.domain.chess.exception.*;
import chess.domain.chess.initializer.Initializer;
import chess.domain.chess.unit.King;
import chess.domain.chess.unit.Knight;
import chess.domain.chess.unit.Pawn;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Position;
import chess.domain.geometric.Vector;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard {
    private static final int KING_INIT_COUNT = 2;
    private static final double PAWN_BASIC_SCORE = 1;
    private static final double PAWN_HALF_SCORE = PAWN_BASIC_SCORE / 2;
    private static final int PAWN_HORIZONTAL_COUNT = 2;
    private static final int ZERO = 0;

    private Map<Position, Unit> units;
    private Team present;

    public ChessBoard(Initializer initializer) {
        this.units = initializer.create();
        this.present = initializer.createTeam();
    }

    public Optional<Unit> getOptionalUnit(Position position) {
        return Optional.ofNullable(units.get(position));
    }

    private Unit getSourceUnit(Position source) {
        return Optional.ofNullable(units.get(source))
                .orElseThrow(() -> new IllegalSourceUnitNotPresentException("해당 위치에는 유닛이 존재하지 않습니다."));
    }

    public void move(Position source, Position target) {
        validateMove(source, target);
        validateInterception(source, target);
        units.put(target, units.get(source));
        units.remove(source);
    }

    private void validateMove(Position source, Position target) {
        Unit sourceUnit = getOptionalUnit(source)
                                .orElseThrow(() -> new IllegalSourceUnitNotPresentException("해당 위치에는 유닛이 존재하지 않습니다."));

        validateTurn(sourceUnit);
        validateTargetUnit(sourceUnit, target);
        validateMoveRule(source, target, sourceUnit);
    }

    private void validateTurn(Unit sourceUnit) {
        if (sourceUnit.getTeam() != present) {
            throw new IllegalTurnException("현재는" +present.name() +" 턴입니다.");
        }
    }

    private void validateTargetUnit(Unit sourceUnit, Position target) {
        Optional<Unit> targetUnit = getOptionalUnit(target);

        if (targetUnit.isPresent() && sourceUnit.isEqualTeam(targetUnit.get())) {
            throw new IllegalSameTeamTargetUnitException("같은 팀을 공격할 수 없습니다.");
        }
    }

    private void validateMoveRule(Position source, Position target, Unit sourceUnit) {
        if (sourceUnit instanceof Pawn) {
            Pawn pawn = (Pawn) sourceUnit;
            validatePawnMoveRule(source, target, pawn);
            return;
        }
        validateOtherMoveRule(source, target, sourceUnit);
    }

    private void validatePawnMoveRule(Position source, Position target, Pawn pawn) {
        if (!pawn.validateDirection(source, target, getOptionalUnit(target).isPresent())) {
            throw new IllegalPawnMovingRuleException("폰의 규칙에 어긋납니다.");
        }
    }

    private void validateOtherMoveRule(Position source, Position target, Unit sourceUnit) {
        if (!sourceUnit.validateDirection(Vector.of(source, target))) {
            throw new IllegalMovingRuleException(sourceUnit.getName() + "의 규칙에 어긋납니다.");
        }
    }

    private void validateInterception(Position source, Position target) {
        if (validateKnight(source)) return;
        validateInterceptionOthers(source, target);
    }

    private boolean validateKnight(Position source) {
        return getSourceUnit(source) instanceof Knight;
    }

    private void validateInterceptionOthers(Position source, Position target) {
        Vector vector = Vector.of(source, target);
        Direction direction = Direction.of(vector);
        Position position = source;

        while (!position.equals(target)) {
            position = direction.createPosition(position);
            checkInterception(target, position);
        }
    }

    private void checkInterception(Position target, Position position) {
        Optional<Unit> unit = Optional.ofNullable(units.get(position));

        if (unit.isPresent() && (!target.equals(position))) {
            throw new IllegalUnitInterceptionAlongPathException("중간 경로에 유닛이 존재합니다.");
        }
    }

    public Map<Team, Double> sumScore() {
        return Arrays.stream(Team.values())
                .collect(Collectors.toMap(key -> key, this::totalScore));
    }

    private Double totalScore(Team team) {
        double score = units.keySet().stream()
                .mapToDouble(position -> singleUnitScore(team, position))
                .sum();

        return score + sumPawnScore(team);
    }

    private double singleUnitScore(Team team, Position position) {
        if (units.get(position).getTeam() == team && (!(units.get(position) instanceof Pawn))) {
            return units.get(position).getScore();
        }
        return ZERO;
    }

    private Double sumPawnScore(Team team) {
        return IntStream.range(Position.MIN_POSITION, Position.MAX_POSITION)
                .map(x -> countPawnOfColumn(x, team))
                .mapToDouble(x -> x * choosePawnScore(x))
                .sum();
    }

    private int countPawnOfColumn(int x, Team team) {
        return (int) units.keySet().stream()
                .filter(key -> key.getX() == x)
                .filter(key -> units.get(key) instanceof Pawn)
                .filter(key -> units.get(key).getTeam() == team)
                .count();
    }

    private double choosePawnScore(int count) {
        if (count < PAWN_HORIZONTAL_COUNT) {
            return PAWN_BASIC_SCORE;
        }
        return PAWN_HALF_SCORE;
    }

    public void changeTeam() {
        present = present.opposite();
    }

    public boolean checkKing() {
        return numberOfKing() != KING_INIT_COUNT;
    }

    private int numberOfKing() {
        return (int) units.keySet().stream()
                .filter(key -> units.get(key) instanceof King)
                .count();
    }

    public Team getAliveKingTeam() {
        return units.values().stream()
                .filter(unit -> unit instanceof King)
                .map(Unit::getTeam)
                .findAny()
                .orElseThrow(() -> new IllegalTeamException("팀이 존재하지 않습니다."));
    }

    public Map<Position, Unit> getUnits() {
        return units;
    }

    public Team getTeam() {
        return present;
    }
}
