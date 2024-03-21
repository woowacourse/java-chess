package chess.domain.board;

import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;
    private final BoardInitializer boardInitializer;

    public Board(final BoardInitializer boardInitializer) {
        this.boardInitializer = boardInitializer;
        this.board = boardInitializer.initialize();
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
        return boardInitializer.isFirstMove(from, currentPiece);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
