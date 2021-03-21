package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Horizontal;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessResult {

    private static final int PAWN_EXTRA_SCORE_MINIMUM_COUNT = 2;
    private final Board board;

    public ChessResult(final Board board) {
        this.board = board;
    }

    public double calculateScore(final Team team) {
        double total = 0;
        for (final Horizontal column : Horizontal.values()) {
            total += calculateColumnTotalScore(team, column.value());
        }
        return total;
    }

    private double calculateColumnTotalScore(final Team team, final int column) {
        final Map<Position, Piece> chessBoard = board.unwrap();
        final List<Piece> pieces = chessBoard.keySet().stream()
            .filter(position -> position.horizontal().value() == column)
            .map(chessBoard::get)
            .filter(piece -> piece.isSameTeam(team))
            .collect(Collectors.toList());

        return pieces.stream()
            .mapToDouble(Piece::score)
            .reduce(0, Double::sum) - calculatePawnDiscountScore(pieces);
    }

    private double calculatePawnDiscountScore(final List<Piece> pieces) {
        long count = pieces.stream()
            .filter(Piece::isPawn)
            .count();

        if (count >= PAWN_EXTRA_SCORE_MINIMUM_COUNT) {
            return count * Pawn.EXTRA_SCORE;
        }
        return 0;
    }

    public Team winner() {
        final Map<Position, Piece> chessBoard = board.unwrap();
        if (board.isKingDead()) {
            return kingSlayerTeam(chessBoard);
        }

        return scoreWinner();
    }

    private Team kingSlayerTeam(final Map<Position, Piece> chessBoard) {
        return chessBoard.values().stream()
            .filter(Piece::isKing)
            .map(Piece::team)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    private Team scoreWinner() {
        if (calculateScore(Team.BLACK) > calculateScore(Team.WHITE)) {
            return Team.BLACK;
        }
        if (calculateScore(Team.BLACK) < calculateScore(Team.WHITE)) {
            return Team.WHITE;
        }
        return Team.NOTHING;
    }

}
