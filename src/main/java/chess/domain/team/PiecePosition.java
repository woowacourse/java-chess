package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public final class PiecePosition {
    private static final int BLACK_PAWN_COLUMN = 6;
    private static final int BLACK_PAWN_DIRECTION = -1;
    private static final int BLACK_PIECE_COLUMN = 7;

    private static final int WHITE_PAWN_COLUMN = 1;
    private static final int WHITE_PAWN_DIRECTION = 1;
    private static final int WHITE_PIECE_COLUMN = 0;

    private final Map<Position, Piece> piecePosition;

    public PiecePosition(final Map<Position, Piece> piecePosition) {
        this.piecePosition = new HashMap<>(piecePosition);
    }

    private PiecePosition(final int pawnColumn, final int pawnDirection, final int pieceColumn) {
        piecePosition = new HashMap<>();
        initializePawn(pawnColumn, pawnDirection);
        initializePiece(pieceColumn);
    }

    public static PiecePosition initBlackPosition() {
        return new PiecePosition(BLACK_PAWN_COLUMN, BLACK_PAWN_DIRECTION, BLACK_PIECE_COLUMN);
    }

    public static PiecePosition initWhitePosition() {
        return new PiecePosition(WHITE_PAWN_COLUMN, WHITE_PAWN_DIRECTION, WHITE_PIECE_COLUMN);
    }

    private void initializePawn(final int pawnColumn, final int pawnDirection) {
        for (int i = 0; i < 8; i++) {
            piecePosition.put(new Position(i, pawnColumn), new Pawn(pawnDirection));
        }
    }

    private void initializePiece(final int pieceColumn) {
        piecePosition.put(new Position(0, pieceColumn), new Rook());
        piecePosition.put(new Position(1, pieceColumn), new Knight());
        piecePosition.put(new Position(2, pieceColumn), new Bishop());
        piecePosition.put(new Position(3, pieceColumn), new Queen());
        piecePosition.put(new Position(4, pieceColumn), new King());
        piecePosition.put(new Position(5, pieceColumn), new Bishop());
        piecePosition.put(new Position(6, pieceColumn), new Knight());
        piecePosition.put(new Position(7, pieceColumn), new Rook());
    }

    public final Piece choosePiece(final Position position) {
        if (havePiece(position)) {
            return piecePosition.get(position);
        }
        throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
    }

    public final void movePiece(final Position current, final Position destination) {
        if (havePiece(destination)) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
        final Piece chosenPiece = choosePiece(current);
        piecePosition.remove(current);
        piecePosition.put(destination, chosenPiece);
        chosenPiece.moved();
    }

    public void moveCastlingRook(final Position kingDestination) {
        final Position kingSideRook = kingDestination.moveXandY(1, 0);
        if (havePiece(kingSideRook) && choosePiece(kingSideRook).isRook()) {
            movePiece(kingSideRook, kingSideRook.moveXandY(-2, 0));
            return;
        }
        final Position queenSideRook = kingDestination.moveXandY(-2, 0);
        if (havePiece(queenSideRook) && choosePiece(queenSideRook).isRook()) {
            movePiece(queenSideRook, queenSideRook.moveXandY(3, 0));
        }
    }

    public final boolean havePiece(final Position position) {
        return piecePosition.containsKey(position);
    }

    public final Piece deletePiece(final Position position) {
        return piecePosition.remove(position);
    }

    public void promote(final Position position) {
        if (havePiece(position) && choosePiece(position).isPawn()) {
            deletePiece(position);
            piecePosition.put(position, new Queen());
        }
    }

    public final Map<Position, Piece> getPiecePosition() {
        return new HashMap<>(piecePosition);
    }
}
