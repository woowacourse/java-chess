package chess.domain.board;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chessboard {
    private static final double PAWN_RATE = 0.5;
    private final Map<Square, Piece> board;

    public Chessboard() {
        this.board = new HashMap<>();
        for (File file : File.values()) {
            putFile(file);
        }
    }

    private void putFile(final File file) {
        for (final Rank rank : Rank.values()) {
            board.put(new Square(file, rank), new Empty(Camp.NONE));
        }
    }

    public void putPiece(final Rank rank, final List<Piece> pieces) {
        final List<File> values = Arrays.asList(File.values());
        for (int i = 0, end = values.size(); i < end; i++) {
            board.put(new Square(values.get(i), rank), pieces.get(i));
        }
    }

    public Piece getPieceAt(final Square square) {
        return board.get(square);
    }

    public void swapPiece(final Square source, final Square target) {
        board.put(target, board.get(source));
        board.put(source, PieceType.EMPTY.createPiece(Camp.NONE));
    }

    public boolean isEmptyInRoute(final Square source, final Square target) {
        return getMovableRoute(source, target)
                .stream()
                .filter(square -> board.get(square).getPieceType() != PieceType.EMPTY)
                .findAny()
                .isEmpty();
    }

    private List<Square> getMovableRoute(final Square source, final Square target) {
        if (source.isSameFile(target)) {
            return source.getSquaresInSameFile(target);
        }

        if (source.isSameRank(target)) {
            return source.getSquaresInSameRank(target);
        }

        return source.getDiagonalSquares(target);
    }

    public double countScore(final Camp camp) {
        return board.values().stream()
                .filter(piece -> piece.isCamp(camp))
                .map(Piece::getScore)
                .reduce(0.0, Double::sum) - calculatePawn(camp);
    }

    private double calculatePawn(final Camp camp) {
        return Square.getEachFileSquares()
                .stream()
                .map(squares -> multiplePawnScore(countPawnOnFile(squares, camp)))
                .reduce(0.0, Double::sum);
    }

    private int countPawnOnFile(final List<Square> squares, final Camp camp) {
        return (int) squares.stream()
                .filter(square -> board.get(square).isSamePieceType(PieceType.PAWN) && board.get(square).isCamp(camp))
                .count();
    }

    private double multiplePawnScore(final int count) {
        if (count == 1) {
            return 0;
        }
        return count * PAWN_RATE;
    }

    public boolean isKingSurvive() {
        return board.values()
                .stream()
                .filter(piece -> piece.getPieceType().equals(PieceType.KING))
                .count() == 2;
    }
}
