package chess.dao;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Piece;

public interface ChessBoardDao {

    void save(long chessGameId, Board board);
    void update(Position piecePosition, Piece piece);
}
