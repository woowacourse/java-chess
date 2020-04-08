package chess.domain.piece;

import chess.domain.move.Move;
import chess.domain.piece.position.Position;

import java.util.List;

public interface PieceAbility {
    void validateMovePattern(Move move, Position targetPosition, List<Piece> pieces);

    void move(Position position);

    boolean isEqualPosition(Position position);

    boolean isBlackTeam();

    boolean isNotKnight();

    String getPieceName();
}
