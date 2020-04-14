package chess.model.service;

import chess.model.dto.GameResultDto;
import chess.model.dto.UserNameDto;
import chess.model.dto.UserNamesDto;
import chess.model.dto.UserResultDto;
import chess.model.repository.ChessGameDao;
import chess.model.repository.ChessResultDao;

public class ResultService {

    private static final ChessGameDao CHESS_GAME_DAO = ChessGameDao.getInstance();
    private static final ChessResultDao CHESS_RESULT_DAO = ChessResultDao.getInstance();

    private static final ResultService INSTANCE = new ResultService();

    private ResultService() {
    }

    public static ResultService getInstance() {
        return INSTANCE;
    }

    public UserNamesDto getUsers() {
        return new UserNamesDto(CHESS_GAME_DAO.getUsers());
    }

    public UserResultDto getResult(UserNameDto userNameDto) {
        GameResultDto gameResultDto = CHESS_RESULT_DAO.getWinOrDraw(userNameDto.getUserName())
            .orElseThrow(IllegalArgumentException::new);
        String winOrDraw = gameResultDto.getTotalCount() + "전 "
            + gameResultDto.getWinCount() + "승 "
            + gameResultDto.getDrawCount() + "무 "
            + gameResultDto.getLoseCount() + "패";
        return new UserResultDto(winOrDraw);

    }
}
