package chess.service;

import chess.dao.GameDao;
import chess.dao.GameDaoImpl;
import chess.domain.Aliance;
import chess.domain.Board;
import chess.domain.Result;
import chess.domain.ResultCalculator;
import chess.dto.GameDto;

import java.util.List;

public class GameService {
    private static GameDao gameDao = GameDaoImpl.getInstance();

    private GameService() {}

    public static List<GameDto> getNotEndGames() {
        return gameDao.findNotEndGames();
    }

    public static int addGame() {
        return gameDao.addGame();
    }

    public static Board createBoard(int gameId) {
        GameDto gameDto = gameDao.findById(gameId);
        return new Board(gameDto.getTurn());
    }

    public static GameDto checkIsEndGame(Board board, int gameId) {
        GameDto gameDto = gameDao.findById(gameId);

        if (gameDto.isEnd()) {
            ResultCalculator resultCalculator = new ResultCalculator(board);
            Result result = new Result(resultCalculator.calculateResult());
            throw new RuntimeException(String.format("[최종 스코어] 백 : 흑 = %.1f : %.1f 게임이 종료되었습니다.",
                    result.getWhiteResult(), result.getBlackResult()));
        }
        return gameDto;
    }

    public static void updateGame(Board board, int gameId) {
        Aliance nextTurn = board.switchTurn();
        boolean isEnd = !board.isKingAlive(nextTurn);
        GameDto gameDto = new GameDto(gameId, isEnd, nextTurn.getTeamId());
        gameDao.updateGame(gameDto);
    }
}
