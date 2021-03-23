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

public final class ChessResult {
    private final Board board;

    public ChessResult(final Board board) {
        this.board = board;
    }

    public double totalScore(final Team team) {
        double total = 0;
        for (final Horizontal column : Horizontal.values()) {
            total += columnTotalScore(team, column.value());
        }
        return total;
    }

    private double columnTotalScore(final Team team, final int column) {
        final Map<Position, Piece> chessBoard = new TreeMap<>(board.unwrap());
        final List<Piece> pieces = chessBoard.keySet().stream()
                .filter(position -> position.horizontal().value() == column)
                .map(chessBoard::get)
                .filter(piece -> piece.friendly(team))
                .collect(Collectors.toList());

        return pieces.stream()
                .mapToDouble(Piece::score)
                .reduce(0, Double::sum) - pawnDiscountScore(pieces);
    }

    private double pawnDiscountScore(final List<Piece> pieces) {
        long count = pieces.stream()
                .filter(piece -> piece instanceof Pawn)
                .count();

        if (count >= 2) {
            return count * Pawn.EXTRA_SCORE;
        }
        return 0;
    }

    public Team winner() {
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
        if (totalScore(Team.BLACK) > totalScore(Team.WHITE)) {
            return Team.BLACK;
        }
        if (totalScore(Team.BLACK) < totalScore(Team.WHITE)) {
            return Team.WHITE;
        }
        return Team.NOTHING;
    }
}
