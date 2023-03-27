package chess.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.dao.ChessGameDao;
import chess.dto.MoveDto;

class ChessServiceTest {

	private final ChessGameDao chessGameDao = new mockDao();
	private ChessService service;


	@Test
	@BeforeEach
	void beforeEach() {
		service = new ChessService(chessGameDao);
	}

	@Test
	@DisplayName("dao에서 가져온 움직임들로 체스를 정상적으로 초기화해야 한다.")
	void loadLastGameTest() {
		assertDoesNotThrow(() -> service.loadLastGame());
	}

	static class mockDao implements ChessGameDao {

		@Override
		public boolean isLastGameExists() {
			return false;
		}

		@Override
		public void saveMove(final MoveDto moveDto) {
		}

		@Override
		public List<MoveDto> loadMoves() {
			MoveDto move1 = new MoveDto(1, 1, 1, 3);
			MoveDto move2 = new MoveDto(1, 6, 1, 4);
			return List.of(move1, move2);
		}

		@Override
		public void deleteMoves() {

		}
	}
}
