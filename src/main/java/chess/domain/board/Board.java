package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Board {

    public static final int LOWER_BOUNDARY = 1;
    public static final int UPPER_BOUNDARY = 8;

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position source, final Position target) {
        final List<Position> movablePosition = findMovablePosition(source);

        if (!movablePosition.contains(target)) {
            throw new IllegalArgumentException("해당 위치로 기물을 움직일 수 없습니다.");
        }

        final Piece sourcePiece = board.get(source);

        if (sourcePiece.isPawn()) {
            sourcePiece.changePawnMoveState();
        }

        board.put(target, sourcePiece);
        board.put(source, new Empty());
    }

    private List<Position> findMovablePosition(final Position position) {
        try {
            final Piece piece = board.get(position);
            return piece.findMovablePosition(position, this);
        } catch (UnsupportedOperationException e) {
            throw new IllegalArgumentException("기물이 있는 위치를 선택해주세요.", e);
        }
    }

    public static boolean isInRange(final int fileIndex, final int rankIndex) {
        return isIndexInRange(fileIndex) && isIndexInRange(rankIndex);
    }

    private static boolean isIndexInRange(final int index) {
        return index >= LOWER_BOUNDARY && index <= UPPER_BOUNDARY;
    }

    public Side findSideByPosition(final Position position) {
        final Piece piece = board.get(position);
        return piece.side();
    }

    public Piece findPieceByPosition(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
