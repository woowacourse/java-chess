package chess.service;

import chess.dao.GameDao;
import chess.dto.TurnDto;

public class FakeGameDao implements GameDao {
    String turn = "WHITE";

    @Override
    public TurnDto getTurn() {
        return new TurnDto(turn);
    }

    @Override
    public void changeTurn() {
        this.turn = "BLACK";
    }
}
