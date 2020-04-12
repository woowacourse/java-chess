package chess.domain.db;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.db.StateMapper;
import chess.domain.piece.state.Moved;
import chess.domain.piece.state.State;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
public class StateMapperTest {
	@DisplayName("Moved 상태를 가져왔을 때 같은 상태를 가진 인스턴스를 반환하는지 확인")
	@Test
	void ofTest() {
		String pieceState = "Moved";

		State state = StateMapper.of(pieceState);

		assertThat(state).isInstanceOf(Moved.class);
	}
}
