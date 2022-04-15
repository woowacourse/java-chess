package chess.repository;

import chess.dto.MoveDto;
import chess.model.board.Board;
import chess.model.state.State;
import chess.model.state.running.WhiteTurn;

public class FakeGameRepository implements GameRepository {

    @Override
    public void initGameData(State state) {
    }

    @Override
    public void saveGameData(State nextState, MoveDto moveDto) {
    }

    @Override
    public void deleteGameData() {
    }

    @Override
    public Board getBoard() {
        return Board.init();
    }

    @Override
    public State getState() {
        return new WhiteTurn(Board.init());
    }
}
