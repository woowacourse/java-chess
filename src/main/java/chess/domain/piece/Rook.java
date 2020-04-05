package chess.domain.piece;

import chess.domain.move.Move;
import chess.domain.move.StraightMove;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.TeamStrategy;

import java.util.Optional;

public class Rook extends Piece {
    public Rook(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    protected boolean isMovablePattern(Move move, Optional<Piece> targetPiece) {
        return move instanceof StraightMove;
    }

    @Override
    public String getPieceName() {
        return teamStrategy.rookName();
    }
}
