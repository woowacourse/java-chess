package service;

import java.util.List;

import dto.GameInfoDto;
import repository.ChessDao;

public class GameRoomService {

    private final ChessDao chessDao;
    private long gameId = 0L;

    public GameRoomService(ChessDao chessDao) {
        this.chessDao = chessDao;
    }

    public void createGameRoom(String gameName) {
        gameId = chessDao.addGame(gameName);
    }

    public List<String> readGameRooms() {
        return chessDao.findAllGame();
    }

    public GameInfoDto getGameInfo(String gameName) {
        this.gameId = chessDao.findGameIdByGameName(gameName);
        return new GameInfoDto(chessDao.findCurrentTurnByGameName(gameName), chessDao.findBoardByGameName(gameName));
    }

    public void saveGameInfo(GameInfoDto gameInfoDto) {
        chessDao.updateCurrentTurn(gameId, gameInfoDto.getCurrentTurn());
        chessDao.saveBoard(gameId, gameInfoDto.getBoardDtos());
    }
}
