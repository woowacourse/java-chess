package chess.domain;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultCounter {
    private final Map<AbstractPiece, Count> resultCounter;

    private List<AbstractPiece> abstractPieces = Arrays.asList(
            new Pawn(Team.BLACK),
            new Rook(Team.BLACK),
            new Bishop(Team.BLACK),
            new Knight(Team.BLACK),
            new Queen(Team.BLACK),
            new King(Team.BLACK),
            new Pawn(Team.WHITE),
            new Rook(Team.WHITE),
            new Bishop(Team.WHITE),
            new Knight(Team.WHITE),
            new Queen(Team.WHITE),
            new King(Team.WHITE)
    );

    private static ResultCounter instance;

    private ResultCounter() {
        resultCounter = new HashMap<>();
        abstractPieces.forEach(piece -> resultCounter.put(piece, new Count()));
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
        return abstractPieces.stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(piece -> resultCounter.get(piece).score(piece))
                .sum();
    }
}
