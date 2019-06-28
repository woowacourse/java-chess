package chess.service;

import chess.dao.RoundDao;
import chess.dao.RoundDaoImpl;
import chess.domain.Game;
import chess.dto.BoardDto;
import chess.dto.RoundDto;

import java.sql.SQLException;
import java.util.List;

public class GameService {
    private GameService() {
    }

    public static GameService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static GameService INSTANCE = new GameService();
    }

    public BoardDto moveGame(Game game, int from, int to) {
        Game playedGame = game.play(from, to);
        if (!game.equals(playedGame)) {
            RoundDao roundDao = RoundDaoImpl.getInstance();
            RoundDto roundDto = new RoundDto();
            roundDto.setRound(game.getRound());
            roundDto.setFrom(from);
            roundDto.setTo(to);
            try {
                roundDao.addRound(roundDto);
            } catch (SQLException e) {
                throw new IllegalArgumentException("데이터 베이스 오류");
            }
        }
        return new BoardDto(playedGame.getBoard());
    }

    public BoardDto start() {
        Game game = new Game();
        Game loadedGame = game;
        RoundDao roundDao = RoundDaoImpl.getInstance();
        List<RoundDto> roundDtos = null;
        try {
            roundDtos = roundDao.selectRound();
        } catch (SQLException e) {
            throw new IllegalArgumentException("데이터 베이스 오류");
        }
        for (RoundDto roundDto : roundDtos) {
            loadedGame = loadedGame.reload(roundDto.getFrom(), roundDto.getTo());
        }
        return new BoardDto(loadedGame.getBoard());
    }
}
