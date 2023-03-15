package chess.domain.board;

public class Position {

    private final Row row;
    private final Col col;

    public Position(final String input) {
        // todo : 정적 팩토리 메서드로 바꾸기
        this.row = Row.fromByInput(input.charAt(1));
        this.col = Col.from(input.charAt(0));
    }

    public int subRowFromArriveRow(final String position) {
        return this.row.subPositionFromArrivePosition(position.charAt(1));
    }

    public int subColFromArriveCol(final String position) {
        return this.col.calculateSubstitutionFromArrivalPosition(position.charAt(0));
    }
}
