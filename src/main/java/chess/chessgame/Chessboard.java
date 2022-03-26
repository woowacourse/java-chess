package chess.chessgame;

import chess.piece.*;
import chess.utils.ChessboardGenerator;

import java.util.Collections;
import java.util.List;

public class Chessboard {

    private final List<List<Piece>> board;

    public Chessboard(ChessboardGenerator chessboardGenerator) {
        board = chessboardGenerator.generate();
    }

    public boolean move(MovingPosition movingPosition, Turn turn) {
        validate(movingPosition, turn);

        Piece target = findPiece(movingPosition.getToX(), movingPosition.getToY());
        boolean isKing = target.isSameType(Type.KING);

        movePiece(movingPosition);

        return isKing;
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }

    private Piece findPiece(int x, int y) {
        return board.get(x).get(y);
    }

    private void validate(MovingPosition movingPosition, Turn turn) {
        Piece source = findPiece(movingPosition.getFromX(), movingPosition.getFromY());
        Piece target = findPiece(movingPosition.getToX(), movingPosition.getToY());

        validateBlank(source);
        validateTurn(source, turn);
        validateColor(source, target);
        validateMovable(movingPosition, source, target);
        validateMiddlePosition(movingPosition, source);
    }

    private void validateBlank(Piece piece) {
        if (piece.isSameType(Type.BLANK)) {
            throw new IllegalArgumentException("이동하려는 위치에 기물이 없습니다.");
        }
    }

    private void validateTurn(Piece source, Turn turn) {
        if (!turn.isRightTurn(source.getColor())) {
            throw new IllegalArgumentException("상대편의 기물은 움직일 수 없습니다.");
        }
    }

    private void validateColor(Piece source, Piece target) {
        if (source.isSameColor(target.getColor())) {
            throw new IllegalArgumentException("상대편의 기물으로만 이동할 수 있습니다.");
        }
    }

    private void validateMovable(MovingPosition movingPosition, Piece source, Piece target) {
        if (!target.isSameType(Type.BLANK) && isDiagonalPawn(movingPosition, source)) {
            return;
        }
        if (source.isMovable(movingPosition)) {
            return;
        }
        throw new IllegalArgumentException("움직일 수 없는 기물입니다.");
    }

    private boolean isDiagonalPawn(MovingPosition movingPosition, Piece source) {
        if (!source.isSameType(Type.PAWN)) {
            return false;
        }
        Pawn pawn = (Pawn) source;
        return pawn.isDiagonal(movingPosition);
    }

    private void validateMiddlePosition(MovingPosition movingPosition, Piece source) {
        source.computeMiddlePosition(movingPosition).forEach(
                middlePosition -> validateMiddleBlank(middlePosition)
        );
    }

    private void validateMiddleBlank(Position position) {
        if (!findPiece(position.getX(), position.getY()).isSameType(Type.BLANK)) {
            throw new IllegalArgumentException("가로막는 기물이 있습니다.");
        }
    }

    private void movePiece(MovingPosition movingPosition) {
        Piece source = findPiece(movingPosition.getFromX(), movingPosition.getFromY());

        board.get(movingPosition.getToX()).set(movingPosition.getToY(), source);
        board.get(movingPosition.getFromX()).set(movingPosition.getFromY(), new Blank());
    }

    public double computeScore(Color color, double minusScoreOfSameColumnPawn) {
        double score = computeTotalScore(color);

        for (int i = 0; i < board.size(); i++) {
            int duplicatedPawn = computePawnCount(i, color);
            score -= computeMinusScore(duplicatedPawn, minusScoreOfSameColumnPawn);
        }

        return score;
    }

    private double computeTotalScore(Color color) {
        double score = 0;
        for (List<Piece> list : board) {
            score += list.stream()
                    .filter(piece -> piece.isSameColor(color))
                    .mapToDouble(Piece::getScore)
                    .sum();
        }
        return score;
    }

    private int computePawnCount(int col, Color color) {
        return (int) board.stream()
                .map(pieces -> pieces.get(col))
                .filter(piece -> piece.isSameColor(color) && piece.isSameType(Type.PAWN))
                .count();
    }

    private double computeMinusScore(int duplicatedPawn, double minusScoreOfSameColumnPawn) {
        if (duplicatedPawn <= 1) {
            return 0;
        }
        return duplicatedPawn * minusScoreOfSameColumnPawn;
    }

}
