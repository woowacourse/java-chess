package dao;

public interface PieceDao {
    PieceDto findByGameId(int gameId);
    int addPiece(String position, int kindId, int gameId);
    int deletePieceByPosition(String position);
}
