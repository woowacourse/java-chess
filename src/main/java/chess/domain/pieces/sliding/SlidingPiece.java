package chess.domain.pieces.sliding;

import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.pieces.Piece;
import java.util.List;

public abstract class SlidingPiece extends Piece {

    protected static final String INVALID_TEAM = "해당 기물은 중립일 수 없습니다.";
    static final String INVALID_MOVE_JUMP_OTHER_PIECE = "해당 기물은 다른 기물을 뛰어넘을 수 없습니다.";

    public SlidingPiece(final Team team, final List<Direction> directions) {
        super(team, List.copyOf(directions));
    }

    @Override
    protected void validateTeam(final Team team) {
        if (team.isNeutrality()) {
            throw new IllegalArgumentException(INVALID_TEAM);
        }
    }

    @Override
    public void validateMove(final Direction correctDirection, final List<Piece> onRoutePieces) {
        validateDirection(correctDirection);
        validateOnRoutePiecesExistAlly(onRoutePieces);
        validateJumpOtherPiece(onRoutePieces);
    }

    private void validateJumpOtherPiece(final List<Piece> onRoutePieces) {
        for (int i = 0; i < onRoutePieces.size() - 1; i++) {
            Piece onRoutePiece = onRoutePieces.get(i);
            validateJump(onRoutePiece);
        }
    }

    private void validateJump(final Piece onRoutePiece) {
        if (!onRoutePiece.isNeutrality()) {
            throw new IllegalArgumentException(INVALID_MOVE_JUMP_OTHER_PIECE);
        }
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
