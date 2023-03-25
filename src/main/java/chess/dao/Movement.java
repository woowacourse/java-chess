package chess.dao;

public class Movement {

    private final String source;
    private final String target;

    public Movement(final String source, final String target) {
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
