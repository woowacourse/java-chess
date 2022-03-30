package chess.domain;

import chess.domain.location.File;
import chess.domain.location.Location;
import chess.domain.location.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TeamScore {
    private static final double DUPLICATE_PAWN_SCORE = 0.5;

    private final Team team;
    private final double score;

    public TeamScore(Team team, Board board) {
        this.team = team;
        this.score = computeTotalScore(board);
    }

    private double computeTotalScore(Board board) {
        double score = 0;
        for (File file : File.values()) {
            List<Piece> pieceList = collectFilePieceByTeam(file, team, board);
            score += computeScore(pieceList);
            score -= computeDuplicatePawnScore(pieceList);
        }
        return score;
    }

    private List<Piece> collectFilePieceByTeam(File file, Team team, Board board) {
        return Arrays.stream(Rank.values())
                .map(rank -> Location.of(file, rank))
                .map(board::get)
                .filter(piece -> piece.isSameTeam(team))
                .collect(Collectors.toList());
    }

    private double computeScore(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double computeDuplicatePawnScore(List<Piece> pieceList) {
        if (countPawn(pieceList) > 1) {
            return countPawn(pieceList) * DUPLICATE_PAWN_SCORE;
        }
        return 0;
    }

    private long countPawn(List<Piece> pieceList) {
        return pieceList.stream()
                .filter(Piece::isPawn)
                .count();
    }

    public String getTeam() {
        return team.getTeamName();
    }


    public double getScore() {
        return score;
    }
}
