package chess.domain.game;

import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;

public class MoveCommand {
    private final TeamColor teamColor;
    private final MoveRoute moveRoute;

    public MoveCommand(TeamColor teamColor, MoveRoute moveRoute) {
        this.teamColor = teamColor;
        this.moveRoute = moveRoute;
    }

    public TeamColor teamColor() {
        return teamColor;
    }

    public MoveRoute moveRoute() {
        return moveRoute;
    }

    public Position startPosition() {
        return moveRoute.startPosition();
    }

    public Position destination() {
        return moveRoute.destination();
    }
}
