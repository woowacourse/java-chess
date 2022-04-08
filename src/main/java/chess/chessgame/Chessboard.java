package chess.chessgame;

import chess.piece.*;
import chess.utils.ChessboardGenerator;

import java.util.*;

public class Chessboard {

    private final Map<Position, Piece> board;

    public Chessboard(ChessboardGenerator chessboardGenerator) {
        board = chessboardGenerator.generate();
    }

    public void move(MovingPosition movingPosition, Turn turn) {
        validate(movingPosition, turn);
        movePiece(movingPosition);
    }

    public boolean isOver() {
        return (int) board.values()
                .stream()
                .filter(piece -> piece.isSameType(Type.KING))
                .count() == 1;
    }

    public double computeScore(Color color, double minusScoreOfSameYPawn) {
        double score = computeTotalScore(color);
        score += computeMinusScore(minusScoreOfSameYPawn);
        return score;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    private void validate(MovingPosition movingPosition, Turn turn) {
        Piece from = board.get(movingPosition.getFrom());
        Piece to = board.get(movingPosition.getTo());

        validateBlank(from);
        validateTurn(from, turn);
        validateColor(from, to);
        validateMovable(movingPosition, from, to);
        validateMiddlePosition(movingPosition, from);
    }

    private void validateBlank(Piece piece) {
        if (piece.isSameType(Type.BLANK)) {
            throw new IllegalArgumentException("이동하려는 위치에 기물이 없습니다.");
        }
    }

    private void validateTurn(Piece from, Turn turn) {
        if (!turn.isRightTurn(from.getColor())) {
            throw new IllegalArgumentException("상대편의 기물은 움직일 수 없습니다.");
        }
    }

    private void validateColor(Piece from, Piece to) {
        if (from.isSameColor(to.getColor())) {
            throw new IllegalArgumentException("상대편의 기물으로만 이동할 수 있습니다.");
        }
    }

    private void validateMovable(MovingPosition movingPosition, Piece from, Piece to) {
        if (!to.isSameType(Type.BLANK) && isDiagonalPawn(movingPosition, from)) {
            return;
        }
        if (from.isMovable(movingPosition)) {
            return;
        }
        throw new IllegalArgumentException("움직일 수 없는 기물입니다.");
    }

    private boolean isDiagonalPawn(MovingPosition movingPosition, Piece from) {
        if (!from.isSameType(Type.PAWN)) {
            return false;
        }
        Pawn pawn = (Pawn) from;
        return pawn.isDiagonal(movingPosition);
    }

    private void validateMiddlePosition(MovingPosition movingPosition, Piece from) {
        from.computeMiddlePosition(movingPosition)
                .forEach(this::validateMiddleBlank);
    }

    private void validateMiddleBlank(Position position) {
        if (!board.get(position).isSameType(Type.BLANK)) {
            throw new IllegalArgumentException("가로막는 기물이 있습니다.");
        }
    }

    private void movePiece(MovingPosition movingPosition) {
        Position from = movingPosition.getFrom();
        Position to = movingPosition.getTo();

        board.put(to, board.get(from));
        board.put(from, new Blank());
    }

    private double computeTotalScore(Color color) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double computeMinusScore(double minusScoreOfSameYPawn) {
        double minusScore = 0;
        for (int i = 0; i < board.size(); i++) {
            minusScore -= computeMinusScoreOfY(i, Color.BLACK, minusScoreOfSameYPawn);
            minusScore -= computeMinusScoreOfY(i, Color.WHITE, minusScoreOfSameYPawn);
        }
        return minusScore;
    }

    private double computeMinusScoreOfY(int y, Color color, double minusScoreOfSameYPawn) {
        int pawnCount = computePawnCount(y, color);
        if (pawnCount >= 2) {
            return pawnCount * minusScoreOfSameYPawn;
        }
        return 0;
    }

    private int computePawnCount(int y, Color color) {
        return (int) board.keySet()
                .stream()
                .filter(position -> position.isSameY(y))
                .map(board::get)
                .filter(piece -> piece.isSameColor(color) && piece.isSameType(Type.PAWN))
                .count();
    }

}
