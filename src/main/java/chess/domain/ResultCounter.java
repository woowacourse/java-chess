package chess.domain;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultCounter {
    private final Map<AbstractPiece, Count> resultCounter;

    private List<AbstractPiece> pieces(Team team) {
        return Arrays.asList(
                new Pawn(team),
                new Rook(team),
                new Bishop(team),
                new Knight(team),
                new Queen(team),
                new King(team)
        );
    }

    private static ResultCounter instance;

    private ResultCounter() {
        resultCounter = new HashMap<>();
        pieces(Team.WHITE).forEach(piece -> resultCounter.put(piece, new Count()));
        pieces(Team.BLACK).forEach(piece -> resultCounter.put(piece, new Count()));
    }

    public static ResultCounter getInstance() {
        if (instance == null) {
            instance = new ResultCounter();
        }
        return instance;
    }

    public Count pieceCount(final AbstractPiece abstractPiece) {
        return resultCounter.get(abstractPiece);
    }

    public void addCount(final AbstractPiece abstractPiece) {
        if (abstractPiece != null) {
            resultCounter.get(abstractPiece).add();
        }

    }

    public double totalScore(final Team team) {
        return pieces(team).stream()
                .mapToDouble(piece -> resultCounter.get(piece).score(piece))
                .sum();
    }
}
