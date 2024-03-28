package chess.util;

import chess.model.board.ChessBoardGenerator;
import chess.model.piece.Piece;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class TestChessBoardGenerator implements ChessBoardGenerator {
    private static final int BOARD_SIZE = 64;

    private final List<Piece> pieces;

    public TestChessBoardGenerator(List<Piece> pieces) {
        validate(pieces);
        this.pieces = pieces;
    }

    private void validate(List<Piece> pieces) {
        if (pieces.size() != BOARD_SIZE) {
            throw new IllegalArgumentException("체스 보드의 크기는 64입니다.");
        }
    }

    @Override
    public Map<Position, Piece> create() {
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        List<File> files = List.of(File.values());
        Iterator<Piece> pieceIterator = pieces.iterator();

        Stream<Position> positions = positionStream(ranks, files);
        return positions.collect(toMap(
                position -> position,
                position -> pieceIterator.next()));
    }

    private Stream<Position> positionStream(List<Rank> ranks, List<File> files) {
        return ranks.stream()
                .flatMap(rank -> positionStreamWithRank(files, rank));
    }

    private Stream<Position> positionStreamWithRank(List<File> files, Rank rank) {
        return files.stream()
                .map(file -> Position.of(file, rank));
    }
}
