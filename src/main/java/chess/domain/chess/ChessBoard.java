package chess.domain.chess;

import chess.domain.chess.exception.*;
import chess.domain.chess.initializer.Initializer;
import chess.domain.chess.unit.Knight;
import chess.domain.chess.unit.Pawn;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Position;
import chess.domain.geometric.Vector;

import java.util.Map;
import java.util.Optional;

public class ChessBoard {
    private Map<Position, Unit> units;

    public ChessBoard(Initializer initializer) {
        this.units = initializer.create();
    }

    public static String map(Unit unit) {
        if (unit.getTeam() == Team.BLACK) {
            return unit.getClass().getSimpleName().substring(0, 1);
        }
        return unit.getClass().getSimpleName().substring(0, 1).toLowerCase();
    }

    public Optional<Unit> getUnit(Position position) {
        return Optional.ofNullable(units.get(position));
    }

    public void validateMove(Position source, Position target) {
        Optional<Unit> targetUnit = getUnit(target);
        Optional<Unit> sourceUnit = getUnit(source);
        Vector vector = Vector.of(source, target);

        if (!sourceUnit.isPresent()) {
            throw new SourceUnitNotPresentException("해당 위치에는 유닛이 존재하지 않습니다.");
        }

        if (targetUnit.isPresent()) {
            if (sourceUnit.get().isEqualTeam(targetUnit.get())) {
                throw new SameTeamTargetUnitException("같은 팀을 공격할 수 없습니다.");
            }
        }

        if (sourceUnit.get() instanceof Pawn) {
            Pawn pawn = (Pawn) sourceUnit.get();
            if (!pawn.validateDirection(source, target, targetUnit.isPresent())) {
                throw new PawnIllegalMovingRuleException("폰의 규칙에 어긋납니다.");
            }
            return;
        }

        if ((!targetUnit.isPresent()) || (sourceUnit.get().isEqualTeam(targetUnit.get()))) {
            if (!sourceUnit.get().validateDirection(vector)) {
                throw new IllegalMovingRuleException(sourceUnit.toString() + "의 규칙에 어긋납니다.");
            }
        }
    }

    private void validateInterception(Position source, Position target) {
        Optional<Unit> sourceUnit = Optional.ofNullable(units.get(source));
        if (sourceUnit.get() instanceof Knight) {
            return;
        }

        Vector vector = Vector.of(source, target);
        Direction direction = Direction.of(vector);
        Position position = source;
        while (position.equals(target) == false) {
            position = direction.apply(position);
            Optional<Unit> unit = Optional.ofNullable(units.get(position));
            if (unit.isPresent() && (target.equals(position) == false)) {
                throw new UnitInterceptionAlongPathException();
            }
        }
    }

    public void move(Position source, Position target) {
        validateMove(source, target);
        validateInterception(source, target);
        units.put(target, units.get(source));
        units.remove(source);
    }
}
