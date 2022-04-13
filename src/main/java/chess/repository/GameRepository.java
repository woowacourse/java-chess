package chess.repository;

import chess.dto.MoveDto;
import chess.model.board.Board;
import chess.model.state.State;

public interface GameRepository {

    void initGameData(State state);

    void saveGameData(State nextState, MoveDto moveDto);

    void deleteGameData();

    Board getBoard();

    State getState();
}
