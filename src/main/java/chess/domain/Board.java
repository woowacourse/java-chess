package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import java.util.Arrays;

public class Board {
    private static final int BOARD_SIZE = 8;

    private final Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            initializeRow(i);
        }
    }

    private void initializeRow(int i) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            board[i][j] = Pieces.findPiece(i, j);
        }
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void movePiece(Point source, Point target, String currentColor) {
        Piece sourcePiece = selectPiece(source);
        validateSourcePiece(sourcePiece);
        System.out.println(sourcePiece.isSameColor(currentColor));
        System.out.println(currentColor);

        if(sourcePiece.isNotSameColor(currentColor)){
            System.out.println(currentColor);

            throw new IllegalArgumentException("기물 색이 일치하지 않습니다.");
        }

        Piece targetPiece = selectPiece(target);

        if(targetPiece.isSameColor(currentColor)) {
            throw new IllegalArgumentException("도착지에 아군이 존재합니다.");
        }

        Point nowSource = source;
        while(!nowSource.equals(target)) {
            if(sourcePiece.isMovableRoute(targetPiece)) {
                replacePieceWithEmpty(sourcePiece);
                nowSource = sourcePiece.moveOneStep(target);
                sourcePiece.movePoint(target);
                board[target.getY()][target.getX()] = sourcePiece;
            }
        }
    }

    private void replacePieceWithEmpty(Piece sourcePiece) {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j< 8; j++) {
                if(board[i][j].equals(sourcePiece)){
                    board[i][j] = new Empty(".", null, Point.valueOf(i, j));
                }
            }
        }
    }

    private Piece selectPiece(Point point) {
        int x = point.getX();
        int y = point.getY();
        return this.board[y][x];
    }

    private void validateSourcePiece(Piece piece) {
        if (piece instanceof Empty) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }

        // TODO: 색 검사
//        if (this.board[x][y])
    }

    public boolean hasBothKings() {
        return Arrays.stream(this.board)
                .flatMap(
                        row -> Arrays.stream(row)
                                .filter(piece -> piece instanceof King)
                )
                .count() == 2;
    }
}
