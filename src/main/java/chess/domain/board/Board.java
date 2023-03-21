package chess.domain.board;

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

public class Board {

    private final Map<Position, Piece> board;
    private Color turn;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
        this.turn = Color.WHITE;
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

    public Map<Position, Piece> board() {
        return Map.copyOf(board);
    }

    public Map<Color, Double> calculateScore() {
        final Map<File, List<Position>> piecesInFile = board.keySet().stream()
                .collect(Collectors.groupingBy(Position::file));

        final Map<Color, Double> score = new HashMap<>(
                Map.of(
                        Color.BLACK, 0.0,
                        Color.WHITE, 0.0
                ));

        for (File file : piecesInFile.keySet()) {
            for (Position position : piecesInFile.get(file)) {
                final Piece piece = board.get(position);
                final boolean hasOtherPieceInSameFile = piecesInFile.get(file)
                        .stream()
                        .filter(it -> board.get(it).isTypeOf(PAWN))
                        .count() >= 2;
                final Color color = piece.color();
                final double addedScore = piece.calculateScore(hasOtherPieceInSameFile);
                score.put(color, score.get(color) + addedScore);
            }
        }

        return Map.copyOf(score);
    }
}
