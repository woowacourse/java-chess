package chess.domain.player;

import chess.domain.Position;
import chess.domain.generator.Generator;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

    private static final double DUPLICATE_PAWN_SCORE = 0.5;
    private static final int DEFAULT_PLURAL_COUNT = 2;

    private final List<Piece> pieces;
    private final Team team;

    public Player(List<Piece> pieces, Team team) {
        this.pieces = new ArrayList<>(pieces);
        this.team = team;
    }

    public Player(Generator generator, Team team) {
        this(generator.generate(), team);
    }

    public boolean hasPiece(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.exist(position));
    }

    public List<Piece> findAll() {
        return pieces.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public Position move(final Position current, final Position destination) {
        return findPiece(current)
                .move(current, destination, team);
    }

    public Position capture(final Position current, final Position destination) {
        return findPiece(current)
                .capture(current, destination, team);
    }

    public void remove(final Position position) {
        pieces.remove(findPiece(position));
    }

    private Piece findPiece(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.exist(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스말이 존재하지 않습니다."));
    }

    public double calculateScore() {
        return calculateAllPieceScore() - calculateDuplicatePawnScore();
    }

    private double calculateAllPieceScore() {
        return pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculateDuplicatePawnScore() {
        int pawnCountByFile = 0;
        for (char file = 'a'; file <= 'h'; file++) {
            pawnCountByFile += countPluralPawnByFile(file);
        }
        return DUPLICATE_PAWN_SCORE * pawnCountByFile;
    }

    private int countPluralPawnByFile(final char file) {
        final int count = (int) pieces.stream()
                .filter(piece -> piece.isPawn() && piece.isSameFile(file))
                .count();
        if (count >= DEFAULT_PLURAL_COUNT) {
            return count;
        }
        return 0;
    }

    public boolean hasKing() {
        return pieces.stream()
                .anyMatch(Piece::isKing);
    }

    public String getTeamName() {
        return team.getName();
    }
}
