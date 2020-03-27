package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece {
    protected final TeamColor teamColor;
    private final String name;
    private Position position;

    public Piece(String name, TeamColor teamColor, Position position) {
        this.name = name;
        this.teamColor = teamColor;
        this.position = position;
    }

    public String getName() {
        return teamColor.getPieceName(name);
    }
}
