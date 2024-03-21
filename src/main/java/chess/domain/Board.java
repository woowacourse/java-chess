package chess.domain;

import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;
    private final InitialPiecePosition initialPiecePosition;

    public Board(final InitialPiecePosition initialPiecePosition) {
        this.initialPiecePosition = initialPiecePosition;
        this.board = initialPiecePosition.getInitialPiecePositions();
    }

    public void move(final Position from, final Position to) {
        Piece currentPiece = board.get(from);
        if (!currentPiece.canMove(from, to, isFirstMove(from, currentPiece), getBoard())) {
            throw new IllegalArgumentException("이동이 불가능한 위치입니다.");
        }

        board.put(to, currentPiece);
        board.put(from, new Piece(PieceType.EMPTY, Color.NONE));
    }

    private boolean isFirstMove(final Position from, final Piece currentPiece) {
        return initialPiecePosition.isFirstMove(from, currentPiece);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
