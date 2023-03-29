package chess.dao;

import chess.domain.chessGame.ChessBoard;

public interface ChessGameDao  {

    void insert(ChessBoard chessBoard);

    ChessBoard select();

    void update(ChessBoard chessBoard);

    void delete(ChessBoard chessBoard);
}
