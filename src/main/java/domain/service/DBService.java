package domain.service;

import dao.GameDao;
import dao.PieceDao;
import domain.game.TeamColor;
import dto.PieceDto;
import java.util.List;

public class DBService {
    private final GameDao gameDao = GameDao.getInstance();
    private final PieceDao pieceDao = PieceDao.getInstance();

    public TeamColor findSavedTurn(int gameId) {
        return gameDao.findTurn(gameId);
    }

    public List<PieceDto> findSavedPieces(int gameId) {
        return pieceDao.findAllPieces(gameId);
    }
}
