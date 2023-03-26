package database.db;

import domain.ChessBoard;

public class InMemoryDao implements ChessBoardDao {

    private ChessBoard chessBoard;

    @Override
    public ChessBoard read() {
        return chessBoard;
    }

    @Override
    public void update(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public void save(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }
}
