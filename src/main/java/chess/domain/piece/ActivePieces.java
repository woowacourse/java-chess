package chess.domain.piece;

import static chess.util.PositionUtil.FILES_TOTAL_SIZE;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class ActivePieces {

    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "해당 위치에 체스 말이 존재하지 않습니다.";
    private static final double SAME_FILE_PAWN_DISADVANTAGE = 0.5;

    private final List<Piece> pieces;

    public ActivePieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> findAll() {
        return Collections.unmodifiableList(pieces);
    }

    public Piece findByPosition(Position position) {
        return pieces.stream()
                .filter(piece -> piece.isAt(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_EXCEPTION_MESSAGE));
    }

    public void delete(Piece piece) {
        pieces.remove(piece);
    }

    public boolean isOccupied(Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.isAt(position));
    }

    public int countKings() {
        return (int) pieces.stream()
                .filter(Piece::isKing)
                .count();
    }

    public double calculateScore(Color color) {
        double defaultScore = defaultScore(color);
        int sameFilePawnCount = countAllSameFilePawns(color);

        return defaultScore - (sameFilePawnCount * SAME_FILE_PAWN_DISADVANTAGE);
    }

    private double defaultScore(Color color) {
        return pieces.stream()
                .filter(piece -> piece.hasColorOf(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private int countAllSameFilePawns(Color color) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .map(fileIdx -> countPawnsOfSameFile(color, fileIdx))
                .filter(sameFilePawnCount -> sameFilePawnCount > 1)
                .sum();
    }

    private int countPawnsOfSameFile(Color color, int fileIdx) {
        return (int) pieces.stream()
                .filter(Piece::isPawn)
                .filter(piece -> piece.hasColorOf(color))
                .map(Piece::position)
                .filter(position -> position.hasFileIdxOf(fileIdx))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActivePieces that = (ActivePieces) o;
        return Objects.equals(pieces, that.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}
