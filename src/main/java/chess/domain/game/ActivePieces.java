package chess.domain.game;

import static chess.domain.position.util.PositionUtil.FILES_TOTAL_SIZE;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ActivePieces {

    private static final int SAME_FILE_PAWN_DISADVANTAGE = 2;
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "해당 위치에 체스 말이 존재하지 않습니다.";

    private final List<Piece> pieces = new ArrayList<>(32);

    public ActivePieces() {
        for (Color color : Color.values()) {
            pieces.addAll(initStrongmen(color));
            pieces.addAll(initPawnsOf(color));
        }
    }

    private List<Piece> initPawnsOf(Color color) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .mapToObj(fileIdx -> Pawn.of(color, fileIdx))
                .collect(Collectors.toList());
    }

    private List<Piece> initStrongmen(Color color) {
        return List.of(new King(color), new Queen(color),
                Rook.ofLeft(color), Rook.ofRight(color),
                Bishop.ofLeft(color), Bishop.ofRight(color),
                Knight.ofLeft(color), Knight.ofRight(color));
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
        double defaultScore = calculateDefaultScore(findByColor(color));
        double sameFilePawnCount = calculateSameFilePiecesSum(findPawnPositionsByColor(color));

        return defaultScore - (sameFilePawnCount / SAME_FILE_PAWN_DISADVANTAGE);
    }

    private Double calculateDefaultScore(List<Piece> sameColorPieces) {
        return sameColorPieces.stream()
                .map(Piece::score)
                .reduce(0.0, Double::sum);
    }

    private List<Piece> findByColor(Color color) {
        return pieces.stream()
                .filter(piece -> piece.hasColorOf(color))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Piece> findPawnPositionsByColor(Color color) {
        return pieces.stream()
                .filter(Piece::isPawn)
                .filter(piece -> piece.hasColorOf(color))
                .collect(Collectors.toUnmodifiableList());
    }

    private double calculateSameFilePiecesSum(List<Piece> pieces) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .map(fileIdx -> countPiecesOfSameFile(pieces, fileIdx))
                .filter(count -> count > 1)
                .reduce(0, Integer::sum);
    }

    private int countPiecesOfSameFile(List<Piece> pieces, int fileIdx) {
        return (int) pieces.stream()
                .filter(piece -> piece.isAtFileOrColumnIdxOf(fileIdx))
                .count();
    }
}
