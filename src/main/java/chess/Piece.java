package chess;

abstract class Piece {
    protected Team team;
    protected Trace trace;

    protected Piece(final Team team) {
        this.team = team;
        this.trace = new Trace();
    }

    abstract boolean isMovable(Position startPosition, Position endPosition);

    public void addTrace(final int turn, final Position lastPosition) {
        trace.add(turn, lastPosition);
    }
}
