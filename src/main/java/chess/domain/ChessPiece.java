package chess.domain;

public class ChessPiece {
    private final Team team;
    private final Type type;
    private final ChessBoardPosition chessBoardPosition;

    public ChessPiece(Team team, Type type, ChessBoardPosition chessBoardPosition) {
        this.team = team;
        this.type = type;
        this.chessBoardPosition = chessBoardPosition;
    }
}
