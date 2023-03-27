package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.piece.PieceType.KING;

public class PiecePosition {

    private static final int END_OF_SIZE = 1;
    private static final int DEFAULT_KING_COUNT = 2;

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
}
