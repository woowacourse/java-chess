package chess.domain.piece.implementation.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.piece.implementation.Strategy.KingStrategy;
import chess.domain.player.Team;
import chess.domain.position.Position;

public class King extends Piece {

    private King(Position position, Team team) {
        super(new KingStrategy(team), PieceType.KING, position, team);
    }

    public static King of(Position position, Team team) {
        return new King(position, team);
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new King(target, team);
    }
}
