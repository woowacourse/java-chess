package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

public class Knight extends ChessPiece {
    public Knight(Position position, Team team) {
        super("n", position, team);
    }
}
