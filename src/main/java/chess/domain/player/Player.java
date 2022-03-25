package chess.domain.player;

import chess.domain.Position;
import chess.domain.generator.Generator;
import chess.domain.piece.Piece;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Player {

    private static final int DEFAULT_PLURAL_COUNT = 2;

    private final Set<Piece> pieces;

    public Player(List<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }

    public Player(Generator generator) {
        this(generator.generate());
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
                .move(current, destination);
    }

    public Position capture(final Position current, final Position destination) {
        return findPiece(current)
                .capture(current, destination);
    }

    public void remove(final Position position) {
        pieces.remove(findPiece(position));
    }

    private Piece findPiece(final Position current) {
        return pieces.stream()
                .filter(piece -> piece.exist(current))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스말이 존재하지 않습니다."));
    }

    public int calculatePluralPawnCountByFile() {
        int pawnCountByFile = 0;
        for (char file = 'a'; file <= 'h'; file++) {
            pawnCountByFile += countPluralPawnByFile(file);
        }
        return pawnCountByFile;
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
}
