package chess.domain;

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

    public void validateMove(Position source, Position target) throws Exception {
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

        if (isPathOpened(source, target) == false && !(sourceUnit.get() instanceof Knight) ) {

        }

        if ((targetUnit.isPresent() == false) || (targetUnit.get().getTeam() != sourceUnit.get().getTeam())) {
            if (sourceUnit.get().validateDirection(vector) == false) {
                throw new IllegalMovingRuleException();
            }
        }
    }

    private boolean isPathOpened(Position source, Position target) {
        return false;
    }

    public void move(Position source, Position target) throws Exception {
        validateMove(source, target);
        units.put(target, units.get(source));
        units.remove(source);
    }
}
