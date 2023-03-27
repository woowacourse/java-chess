package chess.domain;

import chess.domain.chesspiece.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ChessBoard {
    private final Map<Square, Piece> pieces;
    private Turn turn;

    public ChessBoard(final Map<Square, Piece> pieces, final Turn turn) {
        this.pieces = pieces;
        this.turn = turn;
    }

    public ChessBoard(final Map<Square, Piece> pieces) {
        this(pieces, Turn.WHITE);
    }

    public boolean canMove(final Square from, final Square to) {
        final Piece target = pieces.get(from);
        validateEmptySquare(target);
        validateTurn(target);
        if (target.isMovable(from, to, pieces.get(to)) && !hasObstacleAlongPath(from, to)) {
            updateChessBoard(from, to, target);
            turn = turn.changeTurn();
            return true;
        }
        return false;
    }

    private void validateEmptySquare(final Piece target) {
        if (target == EmptyPiece.getInstance()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다");
        }
    }

    private void validateTurn(final Piece target) {
        if (turn == Turn.WHITE && target.isBlack()) {
            throw new IllegalArgumentException("백색 기물의 차례입니다");
        }
        if (turn == Turn.BLACK && target.isWhite()) {
            throw new IllegalArgumentException("흑색 기물의 차례입니다");
        }
    }

    private void updateChessBoard(final Square from, final Square to, final Piece target) {
        pieces.put(to, target);
        pieces.put(from, EmptyPiece.getInstance());
        if (target instanceof InitialPawn) {
            Side targetSide = Side.valueOf(target.getSide());
            pieces.put(to, Pawn.from(targetSide));
        }
    }

    private boolean hasObstacleAlongPath(final Square from, final Square to) {
        return from.calculatePath(to)
                .stream()
                .anyMatch(square -> pieces.get(square) != EmptyPiece.getInstance());
    }

    public double calculateScore(final Side side) {
        double score = pieces.values()
                .stream()
                .filter(piece -> Objects.equals(piece.getSide(), side.name()))
                .mapToDouble(piece -> piece.addPieceScore(0))
                .sum();
        return score - checkSameFilePawns(side);
    }

    private double checkSameFilePawns(final Side side) {
        final Map<String, Long> sameFilePawnCounts = pieces.keySet().stream()
                .filter(key -> Objects.equals(pieces.get(key).getName(), PieceInfo.PAWN.name()) || Objects.equals(pieces.get(key).getName(), PieceInfo.INITIAL_PAWN.name()))
                .filter(key -> Objects.equals(pieces.get(key).getSide(), side.name()))
                .collect(groupingBy(Square::getFileSymbol, counting()));

        return sameFilePawnCounts.values()
                .stream()
                .filter(it -> it >= 2)
                .mapToDouble(it -> it.intValue() * 0.5)
                .sum();
    }

    public List<String> findAliveKing() {
        return pieces.values()
                .stream()
                .filter(piece -> Objects.equals(piece.getName(), PieceInfo.KING.name()))
                .map(Piece::getSide)
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<Square, Piece> getPieces() {
        return Map.copyOf(pieces);
    }

    public Turn getTurn() {
        return turn;
    }
}
