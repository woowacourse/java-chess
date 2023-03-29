package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.PAWN;

public class PiecePosition {

    private static final int END_OF_SIZE = 1;
    private static final int DEFAULT_KING_COUNT = 2;
    private static final BigDecimal PAWN_REDUCE_NUMBER = new BigDecimal("0.5");

    private final Map<Position, Piece> piecePosition;

    public PiecePosition(final Map<Position, Piece> piecePosition) {
        this.piecePosition = piecePosition;
    }

    public Piece findPieceByPosition(final Position position) {
        return piecePosition.get(position);
    }

    public boolean isKingAvailable() {
        return piecePosition.values().stream()
                .filter(piece -> piece.getType() == KING)
                .count() < DEFAULT_KING_COUNT;
    }

    public Map<Position, Piece> get() {
        return Map.copyOf(piecePosition);
    }

    public void replace(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        piecePosition.put(from, new EmptyPiece());
        piecePosition.put(to, fromPiece);
    }

    public static List<Position> findRoute(final Position from, final Position to) {
        if (from.equals(to)) {
            return Collections.emptyList();
        }

        if (from.getRank() == to.getRank()) {
            return calculateSameRank(from, to);
        }

        if (from.getFile() == to.getFile()) {
            return calculateSameFile(from, to);
        }

        return calculateDiagonal(from, to);
    }

    private static List<Position> calculateSameFile(final Position from, final Position to) {
        List<Position> collect = Rank.sliceBetween(from.getRank(), to.getRank()).stream()
                .map(rank -> Position.of(from.getFile(), rank))
                .collect(Collectors.toList());

        return collect.subList(END_OF_SIZE, collect.size() - END_OF_SIZE);
    }

    private static List<Position> calculateSameRank(final Position from, final Position to) {
        List<Position> collect = File.sliceBetween(from.getFile(), to.getFile()).stream()
                .map(file -> Position.of(file, from.getRank()))
                .collect(Collectors.toList());

        return collect.subList(END_OF_SIZE, collect.size() - END_OF_SIZE);
    }

    private static List<Position> calculateDiagonal(final Position from, final Position to) {
        List<File> files = File.sliceBetweenExcludeEnd(from.getFile(), to.getFile());
        List<Rank> ranks = Rank.sliceBetweenExcludeEnd(from.getRank(), to.getRank());

        if (files.size() != ranks.size()) {
            return Collections.emptyList();
        }

        return files.stream()
                .flatMap(file -> ranks.stream()
                        .map(rank -> Position.of(file, rank)))
                .collect(Collectors.toList());
    }

    public BigDecimal calculateScore(final Team team) {
        BigDecimal ordinalScore = getOrdinalScore(team);
        BigDecimal pawnDifferenceScore = getPawnSubScore(team);
        return ordinalScore.subtract(pawnDifferenceScore);
    }

    private BigDecimal getPawnSubScore(final Team team) {
        List<Position> pawnPositions = toPawnPositions(team);
        return calculatePawnSubScore(pawnPositions);
    }

    private BigDecimal getOrdinalScore(final Team team) {
        return piecePosition.values().stream()
                .filter(piece -> piece.getTeam() == team)
                .map(piece -> piece.getType().getScore())
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new IllegalStateException(team + "은 점수를 계산할 수 없습니다."));
    }

    private BigDecimal calculatePawnSubScore(final List<Position> pawnPositions) {
        BigDecimal result = BigDecimal.ZERO;

        result = pawnPositions.stream()
                .map(Position::getFile)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .filter(count -> count > 1)
                .map(count -> PAWN_REDUCE_NUMBER.multiply(BigDecimal.valueOf(count)))
                .reduce(result, BigDecimal::add);

        return result;
    }

    private List<Position> toPawnPositions(Team team) {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> Position.of(file, rank))
                ).filter(position -> piecePosition.get(position).getType() == PAWN)
                .filter(position -> piecePosition.get(position).getTeam() == team)
                .collect(Collectors.toList());
    }
}
