package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Team {
    protected final Map<Position, Piece> piecePosition;
    protected final List<Piece> capturedPieces;
    private final Score score;

    public Team() {
        piecePosition = new HashMap<>();
        capturedPieces = new ArrayList<>();
        score = new Score();
    }

    protected void initializePawn(final int pawnColumn, final int pawnDirection) {
        for (int i = 0; i < 8; i++) {
            piecePosition.put(new Position(i, pawnColumn), new Pawn(pawnDirection));
        }
    }

    protected void initializePiece(final int pieceColumn) {
        piecePosition.put(new Position(0, pieceColumn), new Rook());
        piecePosition.put(new Position(1, pieceColumn), new Knight());
        piecePosition.put(new Position(2, pieceColumn), new Bishop());
        piecePosition.put(new Position(3, pieceColumn), new Queen());
        piecePosition.put(new Position(4, pieceColumn), new King());
        piecePosition.put(new Position(5, pieceColumn), new Bishop());
        piecePosition.put(new Position(6, pieceColumn), new Knight());
        piecePosition.put(new Position(7, pieceColumn), new Rook());
    }

    public Piece choosePiece(final Position position) {
        if (havePiece(position)) {
            return piecePosition.get(position);
        }
        throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
    }

    public boolean havePiece(final Position position) {
        return piecePosition.containsKey(position);
    }

    public Piece deletePiece(Position destination) {
        return piecePosition.remove(destination);
    }

    public void catchPiece(Piece piece) {
        capturedPieces.add(piece);
    }

    public abstract void move(final Position current, final Position destination);

    public Map<Position, Piece> getPiecePosition() {
        return new HashMap<>(piecePosition);
    }

    public double calculateScore() {
        return score.calculateScore(piecePosition);
    }
}
