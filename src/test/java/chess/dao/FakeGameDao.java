package chess.dao;


import chess.dto.GameDto;
import chess.dto.GameStatusDto;

public class FakeGameDao implements GameDao {

    private GameDto gameDto;

    @Override
    public void removeAll() {
        gameDto = null;
    }

    @Override
    public void save(GameDto gameDto) {
        this.gameDto = gameDto;
    }

    @Override
    public void update(GameDto gameDto) {
        this.gameDto = gameDto;
    }

    @Override
    public void updateTurn(String turn) {
        this.gameDto = new GameDto(turn, this.gameDto.getStatus());
    }

    @Override
    public void updateStatus(GameStatusDto statusDto) {
        this.gameDto = new GameDto(this.gameDto.getTurn(), statusDto.getName());
    }

    @Override
    public GameDto find() {
        return gameDto;
    }
}
