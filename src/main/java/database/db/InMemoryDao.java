package database.db;

import domain.ChessBoard;

public class InMemoryDao implements ChessBoardDao {

    private ChessBoard chessBoard;

    @Override
    public ChessBoard read() {
        if (chessBoard == null) {
            return new ChessBoard();
        }
        return chessBoard;
    }

    @Override
    public void update(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public void delete() {
        this.chessBoard = new ChessBoard();
    }
}
