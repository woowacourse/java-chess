package chess.repository;

public interface ChessRepository {
    void createChessGame(final String chessGameData);
    void saveChessGame(final String gameId, final String chessGameData);
    String loadChessGame(final String gameId);
}
