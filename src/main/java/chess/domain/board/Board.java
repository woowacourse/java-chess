package chess.domain.board;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static final List<Rank> INITIAL_PIECES_RANKS = Arrays.asList(ONE, TWO, SEVEN, EIGHT);

    private final List<Piece> pieces;

    public Board() {
        pieces = INITIAL_PIECES_RANKS.stream()
                .map(this::generateOf)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Piece> generateOf(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Piece.create(file, rank))
                .collect(Collectors.toList());
    }

    public boolean hasPieceInPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.hasPosition(position));
    }

    public Piece findPieceInPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.hasPosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    public void move(final Position currentPosition, final Position movingPosition) {

    }
}
