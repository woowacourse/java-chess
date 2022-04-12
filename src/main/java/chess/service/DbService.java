package chess.service;

import chess.dao.DbBoardDao;
import chess.dao.DbGameDao;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.dto.GameData;
import java.util.Map;

public class DbService {
    private final DbGameDao dbGameDao;
    private final DbBoardDao dbBoardDao;

    private DbService(DbGameDao dbGameDao, DbBoardDao dbBoardDao) {
        this.dbGameDao = dbGameDao;
        this.dbBoardDao = dbBoardDao;
    }

    public static DbService create(DbGameDao dbGameDao, DbBoardDao dbBoardDao) {
        return new DbService(dbGameDao, dbBoardDao);
    }

    public Map<ChessBoardPosition, ChessPiece> getChessBoardInformation(int gameId) {
        return dbBoardDao.findAll(gameId);
    }

    public void saveDataToDb(GameData gameData, Map<ChessBoardPosition, ChessPiece> mapData) {
        dbGameDao.updateGameData(gameData.getGameId(), gameData.getTurn());
        dbBoardDao.updateAll(gameData.getGameId(), mapData);
    }

    public Team loadGameTurn(int gameId) {
        return Team.of(dbGameDao.getTurn(gameId));
    }

    public void saveInitData(GameData gameData, Map<ChessBoardPosition, ChessPiece> mapData) {
        dbGameDao.saveGame(gameData.getGameId(), gameData.getTurn());
        dbBoardDao.updateAll(gameData.getGameId(), mapData);
    }

    public boolean hasData(int gameId) {
        return !dbGameDao.getTurn(gameId).isEmpty();
    }
}
