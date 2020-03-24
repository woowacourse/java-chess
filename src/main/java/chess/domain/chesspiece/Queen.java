package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

public class Queen extends ChessPiece {
    public Queen(Position position, Team team) {
        super("q", position, team);
    }
}
