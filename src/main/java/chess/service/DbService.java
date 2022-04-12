package chess.service;

import chess.dao.DbBoardDao;
import chess.dao.DbGameDao;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.dto.BoardData;
import chess.dto.GameData;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<ChessBoardPosition, ChessPiece> getChessBoardData(int gameId) {
        return toMapData(dbBoardDao.findAll(gameId));
    }

    public void saveDataToDb(int gameId, Team turn, Map<ChessBoardPosition, ChessPiece> mapData) {
        dbGameDao.updateGameData(toGameData(gameId, turn));
        dbBoardDao.updateAll(gameId, toBoardDatas(mapData));
    }

    public Team loadGameTurn(int gameId) {
        return Team.of(dbGameDao.getTurn(gameId));
    }

    public void saveInitData(int gameId, Team turn, Map<ChessBoardPosition, ChessPiece> mapData) {
        dbGameDao.saveGame(toGameData(gameId, turn));
        dbBoardDao.updateAll(gameId, toBoardDatas(mapData));
    }

    public boolean hasData(int gameId) {
        return !dbGameDao.getTurn(gameId).isEmpty();
    }

    private GameData toGameData(int gameId, Team turn) {
        return GameData.of(gameId, Team.of(turn));
    }

    private List<BoardData> toBoardDatas(Map<ChessBoardPosition, ChessPiece> mapData) {
        return mapData.entrySet()
                .stream()
                .map(it -> BoardData.of(ChessPieceConverter.of(it.getValue()),
                        it.getKey().getColumn(),
                        it.getKey().getRow()))
                .collect(Collectors.toList());
    }

    private Map<ChessBoardPosition, ChessPiece> toMapData(List<BoardData> boardDatas) {
        return boardDatas.stream()
                .collect(Collectors.toMap(
                        it -> ChessBoardPosition.of(it.getColumn(), it.getRow()),
                        it -> ChessPieceConverter.of(it.getChessPieceType())));
    }
}
