package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;

public class Result {

    private static final String WINNER_NOT_CHOSEN_ERROR_MESSAGE = "아직 승자가 정해지지 않았습니다.";
    private static final String WINNING_GUIDE_MESSAGE = "이 이겼습니다.";
    private static final double PARALLEL_PAWN_SCORE = 0.5;
    private static final int PARALLEL_PAWN_MIN_COUNT = 2;

    private final Board board;

    public Result(Board board) {
        this.board = board;
    }

    public Score calculateTotalScore(PieceColor color) {
        Map<Piece, Position> pieces = board.remainPieces(color);
        Map<Piece, Position> pawns = board.remainPawns(pieces);

        Score scoreWithDefaultPawns = calculateAllScore(pieces);
        Score pawnScore = calculatePawnScore(pawns);
        return scoreWithDefaultPawns.minus(pawnScore);
    }

    private Score calculateAllScore(Map<Piece, Position> pieces) {
        return pieces.keySet()
            .stream()
            .map(Piece::score)
            .reduce(Score::add)
            .orElse(new Score(0))
            ;
    }

    private Score calculatePawnScore(Map<Piece, Position> pawns) {
        int parallelPawnCont = Arrays.stream(Column.values())
            .mapToInt(column ->
                (int) pawns.entrySet()
                    .stream().filter(pawn -> pawn.getValue().isOn(column))
                    .count())
            .filter(count -> count >= PARALLEL_PAWN_MIN_COUNT)
            .sum();
        return new Score(parallelPawnCont * PARALLEL_PAWN_SCORE);
    }

    public String findWinner() {
        if (!board.kingDead()) {
            return WINNER_NOT_CHOSEN_ERROR_MESSAGE;
        }
        return board.getCoordinates()
            .keySet()
            .stream()
            .filter(Piece::isKing)
            .map(piece -> piece.color() + WINNING_GUIDE_MESSAGE)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("우승자가 없습니다."))
            ;
    }
}
