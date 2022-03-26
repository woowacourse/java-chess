package chess.chessgame;

import chess.piece.*;
import chess.utils.ChessboardGenerator;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chessboard {

    private final List<List<Piece>> board;

    private Chessboard(List<List<Piece>> board) {
        this.board = board;
    }

    public static Chessboard emptyChessboard() {
        return new Chessboard(new ArrayList<>());
    }

    public static Chessboard initializedChessboard() {
        return new Chessboard(ChessboardGenerator.generate());
    }

    public boolean move(Position position, Turn turn) {
        validate(position, turn);

        Piece target = findPiece(position.getToX(), position.getToY());
        boolean isKing = target.isSameType(Type.KING);

        movePiece(position);

        return isKing;
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }

    private Piece findPiece(int x, int y) {
        return board.get(x).get(y);
    }

    private void validate(Position position, Turn turn) {
        Piece source = findPiece(position.getFromX(), position.getFromY());
        Piece target = findPiece(position.getToX(), position.getToY());

        validateBlank(source);
        validateTurn(source, turn);
        validateMovable(position, source, target);
        validateMiddlePosition(position, source);
    }

    private void validateBlank(Piece piece) {
        if (piece.isSameType(Type.BLANK)) {
            throw new IllegalArgumentException("이동하려는 위치에 기물이 없습니다.");
        }
    }

    private void validateTurn(Piece source, Turn turn) {
        if (!source.isRightTurn(turn)) {
            throw new IllegalArgumentException("상대편의 기물은 움직일 수 없습니다.");
        }
    }

    private void validateMovable(Position position, Piece source, Piece target) {
        if (!target.isSameType(Type.BLANK) && isDiagonalPawn(position, source)) {
            return;
        }
        if (source.isMovable(position)) {
            return;
        }
        throw new IllegalArgumentException("움직일 수 없는 기물입니다.");
    }

    private boolean isDiagonalPawn(Position position, Piece source) {
        if (!source.isSameType(Type.PAWN)) {
            return false;
        }
        Pawn pawn = (Pawn) source;
        return pawn.isDiagonal(position);
    }

    private void validateMiddlePosition(Position position, Piece source) {
        List<Pair<Integer, Integer>> middlePositions = source.computeMiddlePosition(position);

        for (Pair<Integer, Integer> middlePosition : middlePositions) {
            if (findPiece(middlePosition.getLeft(), middlePosition.getRight()).isSameType(Type.BLANK)) {
                continue;
            }
            throw new IllegalArgumentException("가로막는 기물이 있습니다.");
        }
    }

    private void movePiece(Position position) {
        Piece source = findPiece(position.getFromX(), position.getFromY());

        board.get(position.getToX()).set(position.getToY(), source);
        board.get(position.getFromX()).set(position.getFromY(), new Blank());
    }

    public double computeScore(Color color) {
        double score = 0;

        for (List<Piece> list : board) {
            score += list.stream()
                    .filter(piece -> piece.isSameColor(color))
                    .mapToDouble(piece -> piece.getScore())
                    .sum();
        }

        for (int i = 0; i < 8; i++) {
            int duplicatedPawn = computePawnCount(i, color);
            if (computePawnCount(i, color) >= 2) {
                score -= 0.5 * duplicatedPawn;
            }
        }
        return score;
    }

    private int computePawnCount(int row, Color color) {
        return (int) board.stream()
                .map(l -> l.get(row))
                .filter(piece -> piece.isSameColor(color) && piece.isSameType(Type.PAWN))
                .count();
    }
}
