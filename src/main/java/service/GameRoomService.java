package service;

import java.util.List;

import dto.GameInfoDto;
import repository.connector.ProdConnector;
import repository.game.GameDao;
import repository.game.JdbcGameDao;
import repository.room.RoomDao;

public class GameRoomService {

    private final RoomDao roomDao;
    private final GameDao gameDao = new JdbcGameDao(new ProdConnector());

    public GameRoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void createGameRoom(String gameName) {
        roomDao.createRoom(gameName);
    }

    public List<String> readGameRooms() {
        return roomDao.findAllRooms();
    }

    public GameInfoDto getGameInfo(String gameName) {
        roomDao.findGameIdByGameName(gameName);
        return new GameInfoDto(gameDao.findCurrentTurnByGameName(gameName), gameDao.findBoardByGameName(gameName));
    }

    public void saveGameInfo(GameInfoDto gameInfoDto) {
        gameDao.deleteBoardById(1);
        gameDao.updateCurrentTurn(1, gameInfoDto.getCurrentTurn());
        gameDao.saveBoard(1, gameInfoDto.getBoardDtos());
    }
}
