package chess.database.dao;

public class Notation {

    private String source;
    private String target;

    public Notation(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
