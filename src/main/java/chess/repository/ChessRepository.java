package chess.repository;

import chess.dto.RoomsDTO;

public interface ChessRepository {
    void createChessGame(final String chessGameData);
    void saveChessGame(final String gameId, final String chessGameData);
    String loadChessGame(final String gameId);
    RoomsDTO createRoom(final String name, final String pw);
}
