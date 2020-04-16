package chess.model.service;

import chess.model.dto.GameResultDto;
import chess.model.dto.UserNameDto;
import chess.model.dto.UserNamesDto;
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

    public GameResultDto getResult(UserNameDto userNameDto) {
        return CHESS_RESULT_DAO.getWinOrDraw(userNameDto.getUserName())
            .orElseThrow(IllegalArgumentException::new);
    }
}
