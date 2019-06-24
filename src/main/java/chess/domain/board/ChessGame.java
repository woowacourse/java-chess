package chess.domain.board;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.stream.IntStream;

public class ChessGame {
    private Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public ChessRound createChessRound() {
        return new ChessRound(calculateScore(Team.WHITE), calculateScore(Team.BLACK));
    }

    public boolean isGameOver() {
        return !board.hasPiece(Team.WHITE, Type.KING) || !board.hasPiece(Team.BLACK, Type.KING);
    }

    public Team findWinner() {
        if (!isGameOver()) {
            throw new IllegalArgumentException("게임이 아직 진행 중 입니다.");
        }
        return board.hasPiece(Team.WHITE, Type.KING) ? Team.WHITE : Team.BLACK;
    }

    double calculateScore(Team team) {
        return IntStream.range(Board.MIN_SIZE, Board.MAX_SIZE)
                .mapToObj(column -> this.calculateScore(team, column))
                .reduce(0.0, Double::sum);
    }

    double calculateScore(Team team, int column) {
        double totalScore = IntStream.range(Board.MIN_SIZE, Board.MAX_SIZE)
                .filter(rank -> board.hasPiece(Square.of(column, rank), team))
                .mapToObj(rank -> board.getPiece(Square.of(column, rank)).getScore())
                .reduce(0.0, Double::sum);
        return totalScore - calculateDuplicateScore(team, column);
    }

    double calculateDuplicateScore(Team team, int column) {
        int pawnCount = countDuplicate(team, Type.PAWN, column);
        if (pawnCount > 1) {
            return pawnCount * Type.PAWN.getScore() / 2;
        }
        return 0;
    }

    int countDuplicate(Team team, Type type, int column) {
        return (int) IntStream.range(Board.MIN_SIZE, Board.MAX_SIZE)
                .filter(rank -> board.hasPiece(Square.of(column, rank), team, type))
                .count()
                ;
    }
}
