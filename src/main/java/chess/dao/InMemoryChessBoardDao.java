package chess.dao;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Piece;

public class InMemoryChessBoardDao implements ChessBoardDao {

    private Board board;

    @Override
    public void save(long chessGameId, Board board) {
        this.board = Board.setting(board.getBoard());
    }

    @Override
    public void update(long chessGameId, Position piecePosition, Piece piece) {
        board.getBoard().put(piecePosition, piece);
    }
}
