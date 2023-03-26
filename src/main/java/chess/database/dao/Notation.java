package chess.database.dao;

public class Notation {

    private String source;
    private String target;
    private String turn;

    public Notation(String source, String target, String turn) {
        this.source = source;
        this.target = target;
        this.turn = turn;
    }
}
