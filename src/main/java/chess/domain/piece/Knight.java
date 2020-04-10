package chess.domain.piece;

import chess.domain.move.KnightMove;
import chess.domain.move.Move;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.TeamStrategy;

import java.util.List;

public class Knight extends Piece {
    public Knight(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    protected boolean isMovablePattern(Move move, Position targetPosition, List<Piece> pieces) {
        return move instanceof KnightMove;
    }

    @Override
    public String getPieceName() {
        return teamStrategy.knightName();
    }
}
