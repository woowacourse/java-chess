package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.piece.Bishop.SCORE_BISHOP;
import static chess.domain.piece.King.SCORE_KING;
import static chess.domain.piece.Knight.SCORE_KNIGHT;
import static chess.domain.piece.Pawn.*;
import static chess.domain.piece.Queen.SCORE_QUEEN;
import static chess.domain.piece.Rook.SCORE_ROOK;

public abstract class Team {
    private static final Map<Piece, Double> scoreByPiece = new HashMap<>();
    private Team enemy;
    private String name;
    private boolean isCurrentTurn;

    static {
        scoreByPiece.put(new King(), SCORE_KING);
        scoreByPiece.put(new Queen(), SCORE_QUEEN);
        scoreByPiece.put(new Knight(), SCORE_KNIGHT);
        scoreByPiece.put(new Bishop(), SCORE_BISHOP);
        scoreByPiece.put(new Rook(), SCORE_ROOK);
        scoreByPiece.put(new Pawn(DIRECTION_WHITE), SCORE_PAWN);
        scoreByPiece.put(new Pawn(DIRECTION_BLACK), SCORE_PAWN);
    }

    protected final Map<Position, Piece> piecePosition;

    public Team(String name) {
        this.name = name;
        this.isCurrentTurn = false;
        this.piecePosition = new HashMap<>();
    }

    public Team(String name, boolean isCurrentTurn, Map<Position, Piece> piecePosition) {
        this.name = name;
        this.isCurrentTurn = isCurrentTurn;
        this.piecePosition = new HashMap<>(piecePosition);
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

    public void move(final Position current, final Position destination) {
        final Piece chosenPiece = piecePosition.get(current);
        piecePosition.remove(current);
        piecePosition.put(destination, chosenPiece);
    }

    public Piece choosePiece(final Position position) {
        if (havePiece(position)) {
            return piecePosition.get(position);
        }
        throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
    }

    public Piece killPiece(Position destination) {
        return piecePosition.remove(destination);
    }

    public boolean havePiece(final Position position) {
        return piecePosition.containsKey(position);
    }

    public Map<Position, Piece> getPiecePosition() {
        return new HashMap<>(piecePosition);
    }

    final public void setEnemy(Team enemy) {
        this.enemy = enemy;
    }

    public Team getEnemy() {
        return enemy;
    }

    public String getName() {
        return name;
    }

    public void startTurn() {
        this.isCurrentTurn = true;
    }

    public void endTurn() {
        this.isCurrentTurn = false;
    }

    public boolean isCurrentTurn() {
        return isCurrentTurn;
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
