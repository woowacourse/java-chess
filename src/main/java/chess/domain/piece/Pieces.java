package chess.domain.piece;

import chess.domain.board.Board;
import chess.exception.NoSuchPermittedChessPieceException;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Pieces<T extends ChessPiece> {
    private static final double PAWN_EXCEPTED_CONDITION_RATIO = 0.5;

    private final List<T> pieces;

    public Pieces(List<T> pieces) {
        this.pieces = pieces;
    }

    public void movePiece(Position source, Position target, Board board) {
        findPieceByPosition(source).move(target, board);
    }

    private T findPieceByPosition(final Position source) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(source))
                .findAny()
                .orElseThrow(NoSuchPermittedChessPieceException::new);
    }

    public void removeIf(Predicate<? super ChessPiece> filter) {
        pieces.removeIf(filter);
    }

    public boolean isKingExist() {
        return pieces.stream()
                .anyMatch(ChessPiece::isKing);
    }

    public double getScore() {
        double totalScore = getTotalScore();
        long countOfExpectedPawn = getCountOfExpectedPawn();

        return totalScore - PAWN_EXCEPTED_CONDITION_RATIO * countOfExpectedPawn;
    }

    private double getTotalScore() {
        return pieces.stream()
                .mapToDouble(ChessPiece::getScore)
                .sum();
    }

    private long getCountOfExpectedPawn() {
        return IntStream.range(0, Board.getColumn())
                .mapToObj(column -> pieces.stream()
                        .filter(piece -> piece.getColumn() == column)
                        .filter(ChessPiece::isPawn)
                ).flatMap(Function.identity())
                .count();
    }

    public List<ChessPiece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

}
