package chess.domain.piece;

import chess.domain.move.CrossMove;
import chess.domain.move.Move;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.TeamStrategy;

import java.util.Optional;

public class Bishop extends Piece {
    public Bishop(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    protected boolean isMovablePattern(Move move, Optional<Piece> targetPiece) {
        return move instanceof CrossMove;
    }

    @Override
    public String getPieceName() {
        return teamStrategy.bishopName();
    }
}
