package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

public class Rook extends ChessPiece {
    public Rook(Position position, Team team) {
        super("r", position, team);
    }
}
