package chess.model.board;

import chess.model.Team;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.position.File;
import chess.model.position.Position;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece get(Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void move(Piece sourcePiece, Position source, Position target) {
        board.replace(target, sourcePiece);
        board.replace(source, new Empty());
    }

    public boolean isKingDead() {
        return countKing() == 1;
    }

    public long countKing() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count();
    }

    public double getTotalScore(Team team) {
        double scoreExcludingPawn = board.values()
                .stream()
                .filter(piece -> !piece.isPawn())
                .filter(piece -> piece.isTeam(team))
                .mapToDouble(Piece::getScore)
                .sum();

        return scoreExcludingPawn + pawnScore(team);
    }

    private double pawnScore(Team team) {
        Map<File, Long> collect = board.entrySet()
                .stream()
                .filter((entry) -> entry.getValue().isPawn())
                .filter((entry) -> entry.getValue().isTeam(team))
                .collect(Collectors.groupingBy(entry -> entry.getKey().getFile(), counting()));

        return collect.values()
                .stream()
                .mapToDouble(this::getScore)
                .sum();
    }

    private double getScore(Long count) {
        if (count == 1) {
            return count * 1.0;
        }
        return count * 0.5;
    }
}
