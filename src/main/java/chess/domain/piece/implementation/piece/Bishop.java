package chess.domain.piece.implementation.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.piece.implementation.Strategy.BishopStrategy;
import chess.domain.player.Team;
import chess.domain.position.Position;

public class Bishop extends Piece {

    private Bishop(Position position, Team team) {
        super(new BishopStrategy(team), PieceType.BISHOP, position, team);
    }

    public static Bishop of(Position position, Team team) {
        return new Bishop(position, team);
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Bishop(target, team);
    }
}
