package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PiecePosition {
    private final Map<Position, Piece> piecePosition;

    public PiecePosition(final int pawnColumn, final int pawnDirection, final int pieceColumn) {
        piecePosition = new HashMap<>();
        initializePawn(pawnColumn, pawnDirection);
        initializePiece(pieceColumn);
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
        final Piece chosenPiece = choosePiece(current);
        piecePosition.remove(current);
        piecePosition.put(destination, chosenPiece);
        chosenPiece.moved();
    }

    public final boolean havePiece(final Position position) {
        return piecePosition.containsKey(position);
    }

    public final Piece deletePiece(final Position position) {
        return piecePosition.remove(position);
    }

    public final Map<Position, Piece> getPiecePosition() {
        return new HashMap<>(piecePosition);
    }
}
