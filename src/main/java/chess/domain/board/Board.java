package chess.domain.board;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.PAWN;

import chess.domain.path.Path;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private final Map<Position, Piece> board;
    private Color turn;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
        this.turn = WHITE;
    }

    public void move(final Position from, final Position to) {
        validateCanMove(from, to);
        movePiece(from, to);
        turn = turn.opposite();
    }

    private void validateCanMove(final Position from, final Position to) {
        validateSamePosition(from, to);
        validateIsFromEmpty(from);
        validateIsDifferentColor(from);

        Piece destination = board.getOrDefault(to, null);

        Path path = board.get(from)
                .searchPathTo(from, to, destination);
        path.validateObstacle(board.keySet());
    }

    private static void validateSamePosition(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("말을 다른 곳으로 이동시켜 주세요");
        }
    }

    private void validateIsFromEmpty(final Position from) {
        if (!board.containsKey(from)) {
            throw new IllegalArgumentException("출발점에 말이 없습니다.");
        }
    }

    private void validateIsDifferentColor(final Position from) {
        if (!board.get(from).isSameColor(turn)) {
            throw new IllegalArgumentException("차례에 맞는 말을 선택해 주세요");
        }
    }

    private void movePiece(final Position from, final Position to) {
        Piece piece = board.remove(from);
        board.put(to, piece);
    }

    public boolean isEnd() {
        final long numberOfKing = board.values().stream()
                .filter(piece -> piece.isTypeOf(KING))
                .count();
        return numberOfKing == 1;
    }

    public Color winner() {
        if (!isEnd()) {
            final Map<Color, Double> score = calculateScore();
            if (score.get(WHITE) > score.get(BLACK)) {
                return WHITE;
            }
            return BLACK;
        }
        final List<Color> colorsOfKing = collectColorsOfKing();
        if (colorsOfKing.size() != 1) {
            throw new IllegalStateException("승자를 결정할 수 없습니다!");
        }
        return colorsOfKing.get(0);
    }

    private List<Color> collectColorsOfKing() {
        return board.values().stream()
                .filter(piece -> piece.isTypeOf(KING))
                .map(Piece::color)
                .collect(Collectors.toList());
    }

    public Map<Color, Double> calculateScore() {
        final Map<File, List<Position>> piecesByFile = collectPiecesByFile();
        final Map<Color, Double> score = new HashMap<>();

        for (File file : piecesByFile.keySet()) {
            for (Position position : piecesByFile.get(file)) {
                final Piece piece = board.get(position);
                final Color color = piece.color();
                final double addedScore = calculateAddedScore(piecesByFile, file, piece);
                score.put(color, score.getOrDefault(color, 0.0) + addedScore);
            }
        }

        return Map.copyOf(score);
    }

    private Map<File, List<Position>> collectPiecesByFile() {
        return board.keySet().stream()
                .collect(Collectors.groupingBy(Position::file));
    }

    private double calculateAddedScore(
            final Map<File, List<Position>> piecesByFile,
            final File file,
            final Piece piece) {
        final boolean hasOtherPieceInSameFile = piecesByFile.get(file)
                .stream()
                .filter(it -> board.get(it).isTypeOf(PAWN))
                .filter(it -> board.get(it).color().isSameColor(piece.color()))
                .count() >= 2;
        return piece.calculateScore(hasOtherPieceInSameFile);
    }

    public Map<Position, Piece> board() {
        return Map.copyOf(board);
    }
}
