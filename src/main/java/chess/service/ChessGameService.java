package chess.service;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
import chess.domain.Board;
import chess.domain.piece.Team;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import chess.view.BoardDto;
import chess.view.PieceDto;
import java.util.Map;

public class ChessGameService {

    private final BoardDao boardDao;
    private final TurnDao turnDao;


    public ChessGameService(BoardDao boardDao, TurnDao turnDao) {
        this.boardDao = boardDao;
        this.turnDao = turnDao;
    }

    public State getState() {
        return StateFactory.of(turnDao.getTurnTeam(), boardDao.getBoard());
    }

    public Map<String, PieceDto> getBoardData() {
        return BoardDto.of(boardDao.getBoard()).getBoardData();
    }

    public void save(Board board, Team team) {
        boardDao.saveBoard(BoardDto.of(board));
        turnDao.saveTurn(team.getTeamName());
    }

    public void removeData() {
        boardDao.removeBoard();
        turnDao.saveTurn("none");
    }
}
