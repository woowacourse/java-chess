package chess.service;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.ChessBoardInitLogic;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.dto.BoardData;
import chess.dto.GameData;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StorageService {
    private final GameDao gameDao;
    private final BoardDao boardDao;

    private StorageService(GameDao gameDao, BoardDao boardDao) {
        this.gameDao = gameDao;
        this.boardDao = boardDao;
    }

    public static StorageService create(GameDao gameDao, BoardDao boardDao) {
        return new StorageService(gameDao, boardDao);
    }

    public Map<ChessBoardPosition, ChessPiece> loadChessBoardData(int gameId) {
        return toMapData(boardDao.findAll(gameId));
    }

    public void saveDataToDb(int gameId, Team turn, Map<ChessBoardPosition, ChessPiece> mapData) {
        gameDao.updateGameData(toGameData(gameId, turn));
        boardDao.updateAll(gameId, toBoardDatas(mapData));
    }

    public Team loadGameTurn(int gameId) {
        return Team.of(gameDao.getTurn(gameId));
    }

    public void saveInitData(int gameId, Team turn, Map<ChessBoardPosition, ChessPiece> mapData) {
        gameDao.saveGame(toGameData(gameId, turn));
        boardDao.updateAll(gameId, toBoardDatas(mapData));
    }

    public void saveDataIfStorageEmpty(int gameId) {
        if (!hasData(gameId)) {
            saveInitData(gameId, Team.WHITE, ChessBoardInitLogic.initialize());
        }
    }

    private boolean hasData(int gameId) {
        return !gameDao.getTurn(gameId).isEmpty();
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
