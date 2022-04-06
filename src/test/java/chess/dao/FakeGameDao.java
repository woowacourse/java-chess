package chess.dao;


import chess.dto.GameDto;

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
    public void updateStatus(String status) {
        this.gameDto = new GameDto(this.gameDto.getTurn(), status);
    }

    @Override
    public GameDto find() {
        return gameDto;
    }
}
