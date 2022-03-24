package chess.domain.board.state;

public enum Winner {

    WHITE("백팀"),
    BLACK("흑팀"),
    TERMINATE("강제종료");

    private final String name;

    Winner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
