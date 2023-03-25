package chess.domain.chessGame;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Referee {

    private static final List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);
    private static final List<Integer> rows = List.of(1, 2, 3, 4, 5, 6, 7, 8);
    private static final int MULTI_PAWN_BOUNDARY = 2;
    private static final double MULTI_PAWN_SCORE = 0.5;

    private final Map<Position, Piece> chessBoard;

    public Referee(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public double calculateScore(Color color) {
        Map<Position, Piece> colorFilteredChessBoard = getColorFilteredChessBoard(color);
        return columns.stream()
                .map(column -> getPiecesInOneColumn(colorFilteredChessBoard, column))
                .mapToDouble(this::getColumnScore)
                .sum();
    }

    private Map<Position, Piece> getColorFilteredChessBoard(Color color) {
        Map<Position, Piece> colorFiltered = new HashMap<>();
        chessBoard.keySet().stream()
                .filter(position -> isColorOf(position, color))
                .forEach(position -> colorFiltered.put(position, chessBoard.get(position)));
        return colorFiltered;
    }

    private boolean isColorOf(Position position, Color color) {
        return chessBoard.get(position).getColor() == color;
    }

    private List<Piece> getPiecesInOneColumn(Map<Position, Piece> colorFilteredChessBoard, int column) {
        return rows.stream()
                .filter(row -> colorFilteredChessBoard.containsKey(Position.of(row, column)))
                .map(row -> colorFilteredChessBoard.get(Position.of(row, column)))
                .collect(Collectors.toList());
    }

    private double getColumnScore(List<Piece> piecesInOneColumn) {
        double columnScore  = piecesInOneColumn.stream()
                .mapToDouble(Piece::getScore)
                .sum();
        long pawnCount = getPawnCount(piecesInOneColumn);
        if (pawnCount >= MULTI_PAWN_BOUNDARY) {
            columnScore -= pawnCount * MULTI_PAWN_SCORE;
        }

        return columnScore;
    }

    private long getPawnCount(List<Piece> piecesInOneColumn) {
        return piecesInOneColumn.stream()
                .filter(piece -> piece.getPieceType() == PieceType.PAWN)
                .count();
    }
}
