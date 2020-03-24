package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends ChessPiece {
    public Bishop(Position position, Team team) {
        super("b", position, team);
    }
}
