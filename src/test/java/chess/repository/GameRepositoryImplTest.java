package chess.repository;

import static chess.model.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.dao.FakeSquareDao;
import chess.dao.FakeStateDao;
import chess.dto.MoveDto;
import chess.model.board.Board;
import chess.model.piece.Pawn;
import chess.model.position.Position;
import chess.model.state.Ready;
import chess.model.state.State;
import chess.model.state.running.BlackTurn;
import chess.model.state.running.WhiteTurn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameRepositoryImplTest {

    @DisplayName("체스 게임 데이터를 전부 초기화 한다.")
    @Test()
    void initGameData() {
        GameRepository gameRepository = new GameRepositoryImpl(new FakeSquareDao(), new FakeStateDao());
        gameRepository.initGameData(new WhiteTurn(Board.init()));
        State state = gameRepository.getState();

        assertThat(state.isWhiteTurn()).isTrue();
    }

    @DisplayName("체스 게임 데이터 일부를 업데이트 한다.")
    @Test()
    void saveGameData() {
        MoveDto moveDto = new MoveDto("b2", "b4");
        GameRepository gameRepository = new GameRepositoryImpl(new FakeSquareDao(), new FakeStateDao());
        gameRepository.initGameData(new WhiteTurn(Board.init()));
        gameRepository.saveGameData(new BlackTurn(Board.init()), moveDto);
        State state = gameRepository.getState();

        assertThat(state.isBlackTurn()).isTrue();
    }

    @DisplayName("체스 게임 데이터를 전부 삭제 한다.")
    @Test()
    void deleteGameData() {
        GameRepository gameRepository = new GameRepositoryImpl(new FakeSquareDao(), new FakeStateDao());
        gameRepository.initGameData(new WhiteTurn(Board.init()));
        gameRepository.deleteGameData();

        assertThatThrownBy(gameRepository::getState)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
