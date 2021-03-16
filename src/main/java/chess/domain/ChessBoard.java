package chess.domain;

public class ChessBoard {
    private static final int SIZE = 8;
    Piece[][] chessBoard;

    public ChessBoard() {
        chessBoard = new Piece[SIZE][SIZE];
        initPiece();
    }

    private void initPiece() {

    }

    public Piece[][] getChessBoard() {
        return chessBoard;
    }
}
