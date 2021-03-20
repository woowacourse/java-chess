package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Team {
    private static final Map<Piece, Double> scoreByPiece = new HashMap<>();

    static {
        scoreByPiece.put(new King(), 0.0);
        scoreByPiece.put(new Queen(), 9.0);
        scoreByPiece.put(new Knight(), 2.5);
        scoreByPiece.put(new Bishop(), 3.0);
        scoreByPiece.put(new Rook(), 5.0);
        scoreByPiece.put(new Pawn(1), 1.0);
        scoreByPiece.put(new Pawn(-1), 1.0);
    }

    protected final Map<Position, Piece> piecePosition;
    protected final List<Piece> capturedPieces;

    public Team() {
        piecePosition = new HashMap<>();
        capturedPieces = new ArrayList<>();
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

    public abstract void move(final Position current, final Position destination);

    public Piece deletePiece(Position destination) {
        return piecePosition.remove(destination);
    }

    public void catchPiece(Piece piece) {
        capturedPieces.add(piece);
    }

    public boolean havePiece(final Position position) {
        return piecePosition.containsKey(position);
    }

    public Map<Position, Piece> getPiecePosition() {
        return new HashMap<>(piecePosition);
    }

    public double calculateTotalScore() {
        double totalScore = 0;
        for (int x = 0; x < 8; x++) {
            List<Piece> pieces = getPiecesInYaxis(x);
            totalScore += calculateScore(pieces);
        }
        return totalScore;
    }

    private List<Piece> getPiecesInYaxis(int x) {
        return piecePosition.keySet().stream()
                .filter(position -> x == position.getX())
                .map(piecePosition::get)
                .collect(Collectors.toList());
    }

    private double calculateScore(final List<Piece> pieces) {
        final double scoreWithoutPawn = calculateScoreByIsPawn(pieces, false);
        final double pawnScore = calculateScoreByIsPawn(pieces, true);
        if (pawnScore > 1.0) {
            return scoreWithoutPawn + (pawnScore / 2.0);
        }
        return scoreWithoutPawn + pawnScore;
    }

    private double calculateScoreByIsPawn(final List<Piece> pieces, final boolean isPawn) {
        final double score = pieces.stream()
                .filter(piece -> piece.isPawn() == isPawn)
                .mapToDouble(scoreByPiece::get)
                .sum();
        return score;
    }
}
