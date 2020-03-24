package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

public class King extends ChessPiece {
    public King(Position position, Team team) {
        super("k", position, team);
    }
}
