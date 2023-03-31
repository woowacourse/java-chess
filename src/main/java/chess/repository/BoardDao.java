package chess.repository;

import chess.domain.ChessGame;

public interface BoardDao {

    void saveChessGame(ChessGame chessGame);

    ChessGame selectChessGame();

    void deleteAll();
}
