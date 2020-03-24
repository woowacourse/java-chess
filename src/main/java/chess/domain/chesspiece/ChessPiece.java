package chess.domain.chesspiece;

import chess.Position;
import chess.Team;

public class ChessPiece {
    private Position position;
    private final Team team;

    public ChessPiece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

}
