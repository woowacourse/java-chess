package chess.domain.piece.implementation.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.piece.implementation.Strategy.QueenStrategy;
import chess.domain.player.Team;
import chess.domain.position.Position;

public class Queen extends Piece {

    private Queen(Position position, Team team) {
        super(new QueenStrategy(team), PieceType.QUEEN, position, team);
    }

    public static Queen of(Position position, Team team) {
        return new Queen(position, team);
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Queen(target, team);
    }
}
