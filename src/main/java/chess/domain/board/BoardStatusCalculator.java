package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BoardStatusCalculator {

    private static final int THERE_IS_NO_PAWN_SIZE = 0;
    private static final int SCORE_WHEN_NO_PAWN_IN_COLUMN = 0;
    private static final double SCORE_WHEN_PAWN_MORE_THAN_TWO_IN_COLUMN = 0.5;
    private static final int CHANGE_PAWN_SCORE_CRITERIA = 2;

    private final Map<Position, Piece> value;

    public BoardStatusCalculator(Board board) {
        value = board.getBoard();
    }

    public double calculate(final Predicate<Piece> isBlackPredicate) {
        return Arrays.stream(Column.values())
            .map(column -> collectPiecesIn(column, isBlackPredicate))
            .mapToDouble(pieces -> calculatePawnScoreIn(pieces) + calculateScoreWithoutPawnIn(pieces))
            .sum();
    }

    private List<Piece> collectPiecesIn(Column column, final Predicate<Piece> isBlackPredicate) {
        return Arrays.stream(Row.values())
            .map(row -> value.get(Position.of(column, row)))
            .filter(piece -> !piece.isNullPiece())
            .filter(isBlackPredicate)
            .collect(Collectors.toList());
    }

    private double calculateScoreWithoutPawnIn(List<Piece> pieces) {
        List<Piece> piecesWithoutPawn = collectPiecesWithoutPawnIn(pieces);
        return piecesWithoutPawn.stream()
            .mapToDouble(Piece::getScore)
            .sum();
    }

    private List<Piece> collectPiecesWithoutPawnIn(List<Piece> pieces) {
        return pieces.stream()
            .filter(piece -> piece.pieceName() != PieceName.PAWN)
            .collect(Collectors.toList());
    }

    private double calculatePawnScoreIn(List<Piece> pieces) {
        List<Piece> pawns = collectPawnsIn(pieces);
        if (pawns.size() == THERE_IS_NO_PAWN_SIZE) {
            return SCORE_WHEN_NO_PAWN_IN_COLUMN;
        }
        if (pawns.size() >= CHANGE_PAWN_SCORE_CRITERIA) {
            return SCORE_WHEN_PAWN_MORE_THAN_TWO_IN_COLUMN * pawns.size();
        }
        return pawns.get(0).getScore() * pawns.size();
    }

    private List<Piece> collectPawnsIn(List<Piece> pieces) {
        return pieces.stream()
            .filter(it -> it.pieceName() == PieceName.PAWN)
            .collect(Collectors.toList());
    }
}
