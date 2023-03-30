package chess.repository;

import chess.domain.chessGame.ChessBoard;

import java.util.List;

public interface ChessGameDao  {

    List<String> getUsers();

    boolean find(String user);

    void insert(String user, ChessBoard chessBoard);

    ChessBoard select(String user);

    void update(String user, ChessBoard chessBoard);

    void delete(String user, ChessBoard chessBoard);
}
