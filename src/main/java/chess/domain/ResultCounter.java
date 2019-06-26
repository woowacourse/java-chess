package chess.domain;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultCounter {
    private final Map<Piece, Count> resultCounter;

    private ResultCounter(final Map<Piece, Count> resultCounter) {
        this.resultCounter = resultCounter;
    }

    public static ResultCounter init() {
        Map<Piece, Count> resultCounter = new HashMap<>();
        pieces(Team.WHITE).forEach(piece -> resultCounter.put(piece, new Count()));
        pieces(Team.BLACK).forEach(piece -> resultCounter.put(piece, new Count()));

        return new ResultCounter(resultCounter);
    }

    public static ResultCounter load(Map<Piece, Count> existCounter) {
        Map<Piece, Count> resultCounter = new HashMap<>(existCounter);
        pieces(Team.WHITE).stream()
                .filter(piece -> !resultCounter.containsKey(piece))
                .forEach(piece -> resultCounter.put(piece, new Count()));
        pieces(Team.BLACK).stream()
                .filter(piece -> !resultCounter.containsKey(piece))
                .forEach(piece -> resultCounter.put(piece, new Count()));
        return new ResultCounter(resultCounter);
    }

    private static List<Piece> pieces(Team team) {
        return Arrays.asList(
                new Pawn(team),
                new Rook(team),
                new Bishop(team),
                new Knight(team),
                new Queen(team),
                new King(team)
        );
    }

    public Count pieceCount(final Piece piece) {
        return resultCounter.get(piece);
    }

    public void addCount(final Piece piece) {
        if (piece != null) {
            pieceCount(piece).add();
        }
    }

    public double totalScore(final Team team) {
        return pieces(team).stream()
                .mapToDouble(this::pieceScore)
                .sum();
    }

    private double pieceScore(final Piece piece) {
        Count count = pieceCount(piece);
        return count.score(piece);
    }
}
