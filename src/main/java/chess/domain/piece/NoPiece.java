package chess.domain.piece;

import chess.domain.piece.info.Team;
import chess.domain.position.Position;

public class NoPiece extends Piece {

    private static final Piece piece = new NoPiece();

    private NoPiece() {
        super(Team.EMPTY);
    }

    public static Piece getInstance() {
        return piece;
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        throw new UnsupportedOperationException("칸에 기물이 없습니다.");
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        throw new UnsupportedOperationException("칸에 기물이 없습니다.");
    }

    @Override
    public PieceType findType() {
        return PieceType.NOPIECE;
    }
}
