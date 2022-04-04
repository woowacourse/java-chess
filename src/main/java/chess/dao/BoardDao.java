package chess.dao;

import chess.domain.piece.Team;

public interface BoardDao {
    void createBoard(Team team);

    void updateTurn(Team turn, int id);
}
