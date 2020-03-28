package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private static final int PAWN_ON_SAME_FILE = 1;
    private static final double PAWN_SCORE_STRATEGY = 0.5;

    private final Map<Position, Piece> board;

    public Board() {
        this(BoardInitializer.initializeAll());
    }

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<String, String> parse() {
        Map<String, String> parseResult = board.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(),
                        entry -> entry.getValue().toSymbol(),
                        (e1, e2) -> e1, LinkedHashMap::new));
        
        return Collections.unmodifiableMap(parseResult);
    }

    public void updateBoard(final Position sourcePosition, final Position targetPosition) {
        Piece selectedPiece = this.board.get(sourcePosition);
        this.board.put(targetPosition, selectedPiece);
        this.board.remove(sourcePosition);
    }

    public Team checkWinner() {
        if (checkWhiteKing() && !checkBlackKing()) {
            return Team.WHITE;
        }
        if (!checkWhiteKing() && checkBlackKing()) {
            return Team.BLACK;
        }
        return null;
    }

    private boolean checkWhiteKing() {
        return this.board.values().stream()
                .anyMatch(Piece::isWhiteKing);
    }

    private boolean checkBlackKing() {
        return this.board.values().stream()
                .anyMatch(Piece::isBlackKing);
    }

    public boolean isEmpty(final Position position) {
        return !this.board.containsKey(position);
    }

    public Piece getPiece(final Position position) {
        return this.board.get(position);
    }

    public double calculateScore(final Team team) {
        double totalScore = calculateTotalScore(team);
        return calculatePawnScore(team, totalScore);
    }

    private double calculateTotalScore(final Team team) {
        return board.values().stream()
                .filter(piece -> team.isSameTeamWith(piece.getTeam()))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculatePawnScore(final Team team, double score) {
        for (File file : File.values()) {
            List<Map.Entry<Position, Piece>> sameFile = this.board.entrySet().stream()
                    .filter(entry -> File.of(entry.getKey().getFile()).equals(file))
                    .filter(entry -> entry.getValue().isPawn() && !entry.getValue().isEnemy(team))
                    .collect(Collectors.toList());

            if (sameFile.size() > PAWN_ON_SAME_FILE) {
                score -= sameFile.size() * PAWN_SCORE_STRATEGY;
            }
        }
        return score;
    }
}