package techcourse.fp.chess.domain;

public class Position {

    private final String file;
    private final String rank;

    public Position(String file, String rank) {
        this.file = file;
        this.rank = rank;
    }
}
