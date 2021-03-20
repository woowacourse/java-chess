package chess.domain;

import chess.domain.piece.*;

import java.util.Arrays;

public class Board {
    private static final int BOARD_SIZE = 8;

    private final Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = Pieces.findPiece(i, j);
            }
        }
    }

    public void movePiece(Point source, Point target, String currentColor) {
        Piece sourcePiece = selectPiece(source);
        validateSourcePieceNotEmpty(sourcePiece);
        validateSourcePieceColor(currentColor, sourcePiece);

        Piece targetPiece = selectPiece(target);
        validateTargetPieceColor(currentColor, targetPiece);

        moveStepByStep(source, target, sourcePiece, targetPiece);
    }

    private Piece selectPiece(Point point) {
        int x = point.getColumn();
        int y = point.getRow();
        return this.board[y][x];
    }

    private void moveStepByStep(Point source, Point target, Piece sourcePiece, Piece targetPiece) {
        Point currentPoint = source;
        Direction direction = sourcePiece.direction(targetPiece).orElse(null);
        while (!currentPoint.equals(target)) {
            Piece temp = pickPiece(currentPoint);
            currentPoint = temp.moveOneStep(target, direction);
            // TODO: depth 해결
            if (currentPoint.equals(target)) {
                replacePiece(sourcePiece, new Empty(".", null, source));
                sourcePiece.movePoint(target);
                replacePiece(targetPiece, sourcePiece);
                break;
            }
            validateNextPoint(currentPoint);
        }
    }

    public boolean hasBothKings() {
        return Arrays.stream(this.board)
                .flatMap(
                        row -> Arrays.stream(row)
                                .filter(piece -> piece instanceof King)
                )
                .count() == 2;
    }

    private void validateSourcePieceNotEmpty(Piece piece) {
        if (piece instanceof Empty) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }
    }

    private void validateSourcePieceColor(String currentColor, Piece sourcePiece) {
        if (sourcePiece.isNotSameColor(currentColor)) {
            throw new IllegalArgumentException("기물의 색이 일치하지 않아 움직일 수 없는 기물입니다.");
        }
    }

    private void validateTargetPieceColor(String currentColor, Piece targetPiece) {
        if (targetPiece.isSameColor(currentColor)) {
            throw new IllegalArgumentException("도착지에 아군이 존재합니다.");
        }
    }

    private void validateNextPoint(Point point) {
        if (!(pickPiece(point) instanceof Empty)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private Piece pickPiece(Point point) {
        return board[point.getRow()][point.getColumn()];
    }

    private void replacePiece(Piece currentPiece, Piece expectedPiece) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].equals(currentPiece)) {
                    board[i][j] = expectedPiece;
                }
            }
        }
    }

    public double addScore(String color) {
        return calculatePieceScore(color) - calculatePawnScore(color);
    }

    private double calculatePieceScore(String color) {
        double totalScore = 0;
        for (int i = 0; i < 8; i++) {
            totalScore += addRowPieceScore(color, totalScore, board[i]);
        }
        return totalScore;
    }

    private double addRowPieceScore(String color, double totalScore, Piece[] pieces) {
        for (int j = 0; j < 8; j++) {
            totalScore += addPieceScore(color, pieces[j]);
        }
        return totalScore;
    }

    private double addPieceScore(String color, Piece piece) {
        if (piece.isSameColor(color)) {
            return piece.score();
        }
        return 0;
    }

    private double calculatePawnScore(String color) {
        int pawnCountInColumn = 0;
        for (int i = 0; i < 8; i++) {
            pawnCountInColumn += countPawnsInColumn(color, i);
        }
        return pawnCountInColumn * 0.5;
    }

    private int countPawnsInColumn(String color, int i) {
        int pawnsInColumn = 0;
        for (int j = 0; j < 8; j++) {
            Piece piece = board[j][i];
            pawnsInColumn += countPawn(color, piece);
        }
        if (pawnsInColumn >= 2) {
            return pawnsInColumn;
        }
        return 0;
    }

    private int countPawn(String color, Piece piece) {
        if (piece instanceof Pawn && piece.isSameColor(color)) {
            return 1;
        }
        return 0;
    }

    public Piece[][] getBoard() {
        return board;
    }
}
