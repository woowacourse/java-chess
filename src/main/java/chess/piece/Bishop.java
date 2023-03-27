package chess.piece;

import java.util.Map;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team, PieceType.BISHOP);
    }

    @Override
    protected void validatePathByType(final Position from, final Position to, final Map<Position, Piece> board) {
        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = Rank.calculateInterval(from.getRank(), to.getRank());
        if (fileInterval != rankInterval) {
            throw new IllegalArgumentException("Bishop이 이동할 수 없는 경로입니다.");
        }
        validateDiagonal(from, to, board);
    }
}
