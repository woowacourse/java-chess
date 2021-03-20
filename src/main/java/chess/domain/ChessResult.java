package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Horizontal;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ChessResult {

    private static final int PAWN_EXTRA_SCORE_MINIMUM_COUNT = 2;
    private final Board board;

    public ChessResult(final Board board) {
        this.board = board;
    }

    public double calculateScore(final Team team) {
        double total = 0;
        for (final Horizontal column : Horizontal.values()) {
            total += getColumnTotalScore(team, column.getValue());
        }
        return total;
    }

    private double getColumnTotalScore(final Team team, final int column) {
        final Map<Position, Piece> chessBoard = new TreeMap<>(board.unwrap());
        final List<Piece> pieces = chessBoard.keySet().stream()
            .filter(position -> position.getHorizontal().getValue() == column)
            .map(chessBoard::get)
            .filter(piece -> piece.isSameTeam(team))
            .collect(Collectors.toList());

        return pieces.stream()
            .mapToDouble(Piece::getScore)
            .reduce(0, Double::sum) - getPawnDiscountScore(pieces);
    }

    private double getPawnDiscountScore(final List<Piece> pieces) {
        long count = pieces.stream()
            .filter(piece -> piece instanceof Pawn)
            .count();

        if (count >= PAWN_EXTRA_SCORE_MINIMUM_COUNT) {
            return count * Pawn.EXTRA_SCORE;
        }
        return 0;
    }

    public Team getWinner() {
        final Map<Position, Piece> chessBoard = new TreeMap<>(board.unwrap());
        if (board.isKingDead()) {
            return kingSlayerTeam(chessBoard);
        }

        return scoreWinner();
    }

    private Team kingSlayerTeam(Map<Position, Piece> chessBoard) {
        return chessBoard.values().stream()
            .filter(piece -> piece instanceof King)
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
