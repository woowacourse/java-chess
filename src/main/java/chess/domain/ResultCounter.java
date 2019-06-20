package chess.domain;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultCounter {
    private final Map<Piece, Count> resultCounter;

    private List<Piece> pieces = Arrays.asList(
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

    private ResultCounter() {
        resultCounter = new HashMap<>();
        pieces.forEach(piece -> resultCounter.put(piece, new Count()));
    }

    public static ResultCounter init() {
        return new ResultCounter();
    }

    public Count pieceCount(final Piece piece) {
        return resultCounter.get(piece);
    }

    public void addCount(final Piece piece) {
        resultCounter.get(piece).add();
    }

    public double totalScore(final Team team) {
        return pieces.stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(piece -> resultCounter.get(piece).score(piece))
                .sum();
    }
}
