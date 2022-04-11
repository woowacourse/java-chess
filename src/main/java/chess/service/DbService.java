package chess.service;

import chess.dao.DbBoardDao;
import chess.dao.DbGameDao;
import chess.domain.Team;
import chess.dto.ChessBoardDto;
import chess.dto.GameInformationDto;

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

    public ChessBoardDto getChessBoardInformation(int gameId) {
        return dbBoardDao.findAll(gameId);
    }

    public void deleteAllData(int gameId) {
        dbBoardDao.deleteAll(gameId);
        dbGameDao.deleteGameData(gameId);
    }

    public void saveDataToDb(int gameId, Team turn, ChessBoardDto chessBoardDto) {
        dbGameDao.updateGameData(gameId, GameInformationDto.of(gameId, turn));
        dbBoardDao.updateAll(gameId, chessBoardDto);
    }

    public GameInformationDto loadGameInformationDto(int gameId) {
        return dbGameDao.getGameData(gameId);
    }

    public void saveInitData(int gameId, Team turn, ChessBoardDto chessBoardDto) {
        dbGameDao.saveGame(GameInformationDto.of(gameId, turn));
        dbBoardDao.updateAll(gameId, chessBoardDto);
    }

    public boolean hasData(int gameId) {
        return !dbGameDao.getGameData(gameId).isEmpty();
    }
}
