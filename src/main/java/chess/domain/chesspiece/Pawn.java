package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

public class Pawn extends ChessPiece {
    boolean isFirstMove = true;

    public Pawn(Position position, Team team) {
        super("p", position, team);
    }

    @Override
    public boolean canMove(Position position) {

    }
}
