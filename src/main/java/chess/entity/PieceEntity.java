package chess.entity;

import chess.domain.piece.PieceType;
import chess.domain.team.Team;

public class PieceEntity {

    private final long id;
    private final long gameId;
    private final PieceType pieceType;
    private final Team team;
    private final int x;
    private final int y;

    public PieceEntity(final long id, final long gameId, final PieceType pieceType,
        final Team team, final int x, final int y) {

        this.id = id;
        this.gameId = gameId;
        this.pieceType = pieceType;
        this.team = team;
        this.x = x;
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Team getTeam() {
        return team;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
