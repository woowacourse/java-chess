package chess.dao;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import java.util.Map;

public class InMemoryChessBoardDao implements ChessBoardDao {

    @Override
    public void save(long chessGameId, Map<Position, Piece> board) {
        return;
    }

    @Override
    public void update(Position piecePosition, Piece piece) {
        return;
    }

    @Override
    public void select(Board board) {
        return;
    }
}
