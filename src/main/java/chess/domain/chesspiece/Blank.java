package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

public class Blank extends ChessPiece {
    public Blank( Position position, Team team) {
        super(".", position, team);
    }
}
