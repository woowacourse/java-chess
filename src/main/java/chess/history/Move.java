package chess.history;

import chess.domain.position.Position;

public final class Move {
    
    private final long id;
    private final Position from;
    private final Position to;
    
    private Move(final long id, final Position from, final Position to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }
    
    public static Move create(final Position from, final Position to) {
        long id = System.currentTimeMillis();
        return new Move(id, from, to);
    }
    
    public static Move create(final long id, final String from, final String to) {
        return new Move(id, Position.from(from), Position.from(to));
    }
    
    public long getID() {
        return this.id;
    }
    
    public Position getFrom() {
        return this.from;
    }
    
    public Position getTo() {
        return this.to;
    }
    
    public String getFromLabel() {
        return this.from.getLabel();
    }
    
    public String getToLabel() {
        return this.to.getLabel();
    }
}