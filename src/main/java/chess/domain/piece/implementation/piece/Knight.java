package chess.domain.piece.implementation.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.piece.implementation.Strategy.KnightStrategy;
import chess.domain.player.Team;
import chess.domain.position.Position;

public class Knight extends Piece {

    private Knight(Position position, Team team) {
        super(new KnightStrategy(team), PieceType.KNIGHT, position, team);
    }

    public static Knight of(Position position, Team team) {
        return new Knight(position, team);
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Knight(target, team);
    }
}
