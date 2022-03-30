package chess.domain.position;

public class UnitPosition {

    private final int unitRow;
    private final int unitColumn;

    public UnitPosition(int unitRow, int unitColumn) {
        this.unitRow = unitRow;
        this.unitColumn = unitColumn;
    }

    public UnitPosition multiply(int times) {
        return new UnitPosition(unitRow * times, unitColumn * times);
    }

    public int getUnitRow() {
        return unitRow;
    }

    public int getUnitColumn() {
        return unitColumn;
    }
}
