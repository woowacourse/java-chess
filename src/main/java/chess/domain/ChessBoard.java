package chess.domain;

import chess.domain.exception.*;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Position;
import chess.domain.geometric.Vector;
import chess.domain.unit.Knight;
import chess.domain.unit.Pawn;
import chess.domain.unit.Unit;

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
        Optional<Unit> targetUnit = Optional.ofNullable(units.get(target));
        Optional<Unit> sourceUnit = Optional.ofNullable(units.get(source));
        Vector vector = Vector.of(source, target);

        if (sourceUnit.isPresent() == false) {
            throw new SourceUnitNotPresentException();
        }

        if (targetUnit.isPresent()) {
            if (sourceUnit.get().isEqualTeam(targetUnit.get())) {
                throw new SameTeamTargetUnitException();
            }
        }

        if (sourceUnit.get() instanceof Pawn) {
            Pawn pawn = (Pawn) sourceUnit.get();
            if (!pawn.validateDirection(source, target, targetUnit.isPresent())) {
                throw new PawnIllegalMovingRuleException();
            }
            return;
        }

        if ((targetUnit.isPresent() == false) || (targetUnit.get().getTeam() != sourceUnit.get().getTeam())) {
            if (sourceUnit.get().validateDirection(vector) == false) {
                throw new IllegalMovingRuleException();
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
            if (unit.isPresent()) {
                throw new UnitInterceptionAlongPathException();
            }
        }

    }

    public void move(Position source, Position target) {
        validateMove(source, target);
        validateInterception(source,target);
        units.put(target, units.get(source));
        units.remove(source);
    }
}
