package chess.domain;

import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultCalculator {
    private static final double DUPLICATE_PAWN_SCORE = 0.5;

    private final Board board;

    public ResultCalculator(Board board) {
        this.board = board;
    }

    public Map<Aliance, Double> calculateResult(){
        List<Piece> pieces = board.getPieces();
        Map<Aliance,Double> result = new HashMap<>();
        double whiteScore = getTotalScore(pieces, Aliance.WHITE);
        double blackScore = getTotalScore(pieces, Aliance.BLACK);
        result.put(Aliance.WHITE, whiteScore - DUPLICATE_PAWN_SCORE * board.getDuplicatePawnCount(Aliance.WHITE));
        result.put(Aliance.BLACK, blackScore - DUPLICATE_PAWN_SCORE * board.getDuplicatePawnCount(Aliance.BLACK));
        return result;
    }

    private Double getTotalScore(List<Piece> pieces, Aliance aliance) {
        return pieces.stream()
                .filter(p -> p.getAliance() == aliance)
                .map(p -> p.getPieceValue().getScore())
                .reduce(0.0, (s1, s2) -> s1 + s2);
    }
}
