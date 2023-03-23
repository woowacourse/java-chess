package chess.domain;

import chess.domain.chesspiece.EmptyPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Piece;

import java.util.Map;

public class ChessBoard {
    private final Map<Square, Piece> pieces;
    private Turn turn;

    public ChessBoard(Map<Square, Piece> pieces, Turn turn) {
        this.pieces = pieces;
        this.turn = turn;
    }

    public ChessBoard(Map<Square, Piece> pieces) {
        this(pieces, Turn.WHITE);
    }

    public boolean canMove(Square from, Square to) {
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

    private void validateEmptySquare(Piece target) {
        if (target == EmptyPiece.getInstance()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다");
        }
    }

    private void validateTurn(Piece target) {
        if (turn == Turn.WHITE && target.isBlack()) {
            throw new IllegalArgumentException("백색 기물의 차례입니다");
        }
        if (turn == Turn.BLACK && target.isWhite()) {
            throw new IllegalArgumentException("흑색 기물의 차례입니다");
        }
    }

    private void updateChessBoard(Square from, Square to, Piece target) {
        pieces.put(to, target);
        pieces.put(from, EmptyPiece.getInstance());
        if (target instanceof Pawn) {
            ((Pawn) target).move();
        }
    }

    private boolean hasObstacleAlongPath(final Square from, final Square to) {
        return from.calculatePath(to)
                .stream()
                .anyMatch(square -> pieces.get(square) != EmptyPiece.getInstance());
    }

    public Map<Square, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
