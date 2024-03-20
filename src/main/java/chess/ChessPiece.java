package chess;

import java.util.List;
import java.util.function.BiPredicate;

import static chess.Direction.*;

public enum ChessPiece {
    BLACK_KING("K", List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();


                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = Math.abs(srcColumn.getValue() - trgColumn.getValue());
                if (rowDistance == 1 || columnDistance == 1) {
                    return true;
                }
                return false;
            }),
    WHITE_KING("k", List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();


                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = Math.abs(srcColumn.getValue() - trgColumn.getValue());
                if (rowDistance == 1 || columnDistance == 1) {
                    return true;
                }
                return false;
            }),
    BLACK_QUEEN("Q", List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                if (srcColumn == trgColumn || srcRow == trgRow) {
                    return true;
                }
                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = Math.abs(srcColumn.getValue() - trgColumn.getValue());
                if (rowDistance == columnDistance) {
                    return true;
                }
                return false;
            }),
    WHITE_QUEEN("q", List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                if (srcColumn == trgColumn || srcRow == trgRow) {
                    return true;
                }
                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = Math.abs(srcColumn.getValue() - trgColumn.getValue());
                if (rowDistance == columnDistance) {
                    return true;
                }
                return false;
            }),
    BLACK_ROOK("R", List.of(UP, DOWN, LEFT, RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                if (srcColumn == trgColumn || srcRow == trgRow) {
                    return true;
                }
                return false;
            }),
    WHITE_ROOK("r", List.of(UP, DOWN, LEFT, RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                if (srcColumn == trgColumn || srcRow == trgRow) {
                    return true;
                }
                return false;
            }),
    BLACK_BISHOP("B", List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = Math.abs(srcColumn.getValue() - trgColumn.getValue());
                if (rowDistance == columnDistance) {
                    return true;
                }
                return false;
            }),
    WHITE_BISHOP("b", List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = Math.abs(srcColumn.getValue() - trgColumn.getValue());
                if (rowDistance == columnDistance) {
                    return true;
                }
                return false;
            }),
    BLACK_KNIGHT("N", List.of(),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = Math.abs(srcColumn.getValue() - trgColumn.getValue());
                if (rowDistance == 1 && columnDistance == 2) {
                    return true;
                }
                if (rowDistance == 2 && columnDistance == 1) {
                    return true;
                }
                return false;
            }),
    WHITE_KNIGHT("n", List.of(),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = Math.abs(srcColumn.getValue() - trgColumn.getValue());
                if (rowDistance == 1 && columnDistance == 2) {
                    return true;
                }
                if (rowDistance == 2 && columnDistance == 1) {
                    return true;
                }
                return false;
            }),
    BLACK_PAWN("P", List.of(DOWN, DOWN_LEFT, DOWN_RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = srcColumn.getValue() - trgColumn.getValue();

                if(srcColumn.getValue() != 6 && srcRow == trgRow && columnDistance == 2) {
                    return false;
                }
                if (srcColumn.getValue() == 6 && srcRow == trgRow && columnDistance == 2) {//앞으로
                    return true;
                }
                if (rowDistance == 0 && columnDistance == 1) {//앞 1칸
                    return true;
                }
                if (rowDistance == 1 && columnDistance == 1) {//대각
                    return true;
                }
                return false;
            }),
    WHITE_PAWN("p", List.of(UP, UP_LEFT, UP_RIGHT),
            (src, trg) -> {
                Row srcRow = src.getRow();
                Column srcColumn = src.getColumn();
                Row trgRow = trg.getRow();
                Column trgColumn = trg.getColumn();

                int rowDistance = Math.abs(srcRow.getValue() - trgRow.getValue());
                int columnDistance = trgColumn.getValue() - srcColumn.getValue();

                if (srcColumn.getValue() != 1 && srcRow == trgRow && columnDistance == 2) {
                    return false;
                }
                if (srcColumn.getValue() == 1 && srcRow == trgRow && columnDistance == 2) {
                    return true;
                }
                if (rowDistance == 0 && columnDistance == 1) {
                    return true;
                }
                if (rowDistance == 1 && columnDistance == 1) {
                    return true;
                }
                return false;
            }),
    NONE(".", List.of(),
            (src, trg) -> {
                return false;
            });

    private final String name;
    private final List<Direction> movableDirection;
    private final BiPredicate<Position, Position> condition;

    ChessPiece(String name, List<Direction> movableDirection, BiPredicate<Position, Position> condition) {
        this.name = name;
        this.movableDirection = movableDirection;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public boolean isValidMovingRule(Position src,Position trg){
        return condition.test(src,trg);
    }


}
