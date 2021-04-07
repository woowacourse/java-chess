package chess.service;

import chess.dao.ResultDAO;
import chess.dao.UserDAO;
import chess.dto.ResultDTO;
import chess.dto.UserDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ResultService {
    private final ResultDAO resultDAO;
    private final UserDAO userDAO;

    public ResultService(final ResultDAO resultDAO, final UserDAO userDAO) {
        this.resultDAO = resultDAO;
        this.userDAO = userDAO;
    }

    public List<ResultDTO> allUserResult() {
        List<ResultDTO> results = new ArrayList<>();
        List<UserDTO> users = userDAO.findAll();
        for (UserDTO user : users) {
            int userId = user.getId();
            int winCount = resultDAO.winCountByUserId(userId);
            int loseCount = resultDAO.loseCountByUserId(userId);
            results.add(new ResultDTO(userDAO.findNicknameById(userId), winCount, loseCount));
        }
        Collections.sort(results);
        return results;
    }

    public void saveGameResult(final String roomId, final int winnerId, final int loserId) {
        resultDAO.saveGameResult(roomId, winnerId, loserId);
    }
}
