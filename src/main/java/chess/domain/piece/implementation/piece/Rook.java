package chess.domain.piece.implementation.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.piece.implementation.Strategy.RookStrategy;
import chess.domain.player.Team;
import chess.domain.position.Position;

public class Rook extends Piece {

    private Rook(Position position, Team team) {
        super(new RookStrategy(team), PieceType.ROOK, position, team);
    }

    public static Rook of(Position position, Team team) {
        return new Rook(position, team);
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Rook(target, team);
    }
}
