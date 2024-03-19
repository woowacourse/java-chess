package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

public class Pawn extends Piece{

    public Pawn(Position position, Team team) {
        super(position, new PieceMeta(team, PieceType.PAWN));
    }

    @Override
    public void move(Position position) {

    }
}
