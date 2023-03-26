package chess.dao;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import java.util.Map;

public class InMemoryChessBoardDao implements ChessBoardDao {

    private Board board;

    @Override
    public void save(long chessGameId, Map<Position, Piece> board) {
        this.board = Board.setting(board, new InMemoryChessBoardDao());
    }

    @Override
    public void update(Position piecePosition, Piece piece) {
        board.getBoard().put(piecePosition, piece);
    }
}
