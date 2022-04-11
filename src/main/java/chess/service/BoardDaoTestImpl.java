package chess.service;

import chess.dao.BoardDao;
import chess.domain.piece.Team;

public class BoardDaoTestImpl implements BoardDao {

    public static final int BOARD_ID = 1;

    private Team turn;

    @Override
    public int createBoard(Team team) {
        turn = team;
        return BOARD_ID;
    }

    @Override
    public void updateTurn(Team turn, int id) {
        if (id == BOARD_ID) {
            this.turn = turn;
        }
    }

    @Override
    public Team findTurn(int id) {
        if (id != BOARD_ID) {
            throw new IllegalArgumentException("id 불일치");
        }
        return turn;
    }
}
