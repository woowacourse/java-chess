package chess.db;

import java.util.Arrays;
import java.util.function.Supplier;

import chess.domain.piece.state.Captured;
import chess.domain.piece.state.Initial;
import chess.domain.piece.state.Moved;
import chess.domain.piece.state.State;

/**
 *    디비의 상태 정보를 맵핑해주는 enum입니다.
 *
 *    @author AnHyungJu
 */
public enum StateMapper {
	INITIAL("Initial", Initial::new),
	MOVED("Moved", Moved::new),
	CAPTURED("Captured", Captured::new);

	private String state;
	private Supplier<State> stateMapper;

	StateMapper(String state, Supplier<State> stateMapper) {
		this.state = state;
		this.stateMapper = stateMapper;
	}

	public static State of(String pieceState) {
		return Arrays.stream(StateMapper.values())
			.filter(s -> s.state.equals(pieceState))
			.findFirst()
			.map(s -> s.stateMapper.get())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태입니다."));
	}
}
