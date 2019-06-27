package chess.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

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

    private ResultCounter() {
        resultCounter = new HashMap<>();
        pieces(Team.WHITE).forEach(piece -> resultCounter.put(piece, new Count()));
        pieces(Team.BLACK).forEach(piece -> resultCounter.put(piece, new Count()));
    }

    public ResultCounter(Map<AbstractPiece, Count> resultCounter) {
        this.resultCounter = resultCounter;
    }

    public static ResultCounter init() {
        return new ResultCounter();
    }

    public Count pieceCount(final AbstractPiece abstractPiece) {
        return resultCounter.get(abstractPiece);
    }

    public ResultCounter addCount(final AbstractPiece abstractPiece) {
        ResultCounter resultCounter = new ResultCounter(this.resultCounter);
        if (abstractPiece != null) {
            resultCounter.get(abstractPiece).add();
        }
        return resultCounter;
    }

    public double totalScore(final Team team) {
        return pieces(team).stream()
                .mapToDouble(piece -> resultCounter.get(piece).score(piece))
                .sum();
    }

    public Set<AbstractPiece> keySet() {
        return resultCounter.keySet();
    }

    public Count get(AbstractPiece key) {
        return resultCounter.get(key);
    }
}
