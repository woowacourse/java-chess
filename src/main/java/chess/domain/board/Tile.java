package chess.domain.board;

import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Tile {
    private final Position position;
    private final PieceType pieceType;
    private final Team team;

    public Tile(Position position, PieceType pieceType, Team team) {
        this.position = position;
        this.pieceType = pieceType;
        this.team = team;
    }

    public String getPosition() {
        return this.position.toString();
    }

    public String getPieceImageUrl() {
        return this.pieceType.name().toLowerCase()
                + "_"
                + this.team.name().toLowerCase();
    }
}
