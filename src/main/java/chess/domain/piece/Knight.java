package chess.domain.piece;

import chess.domain.move.KnightMove;
import chess.domain.move.Move;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.TeamStrategy;

import java.util.Optional;

public class Knight extends Piece {
    public Knight(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    protected boolean isMovablePattern(Move move, Optional<Piece> targetPiece) {
        return move instanceof KnightMove;
    }

    @Override
    public String getPieceName() {
        return teamStrategy.knightName();
    }
}
