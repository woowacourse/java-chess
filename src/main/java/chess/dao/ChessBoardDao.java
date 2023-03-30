package chess.dao;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Piece;

public interface ChessBoardDao {

    void save(long chessGameId, Board board);
    void update(long chessGameId, Position piecePosition, Piece piece);
}
