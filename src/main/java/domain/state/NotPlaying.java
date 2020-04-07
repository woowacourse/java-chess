package domain.state;

import domain.state.exceptions.StateException;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;

public abstract class NotPlaying extends GameState {
	protected NotPlaying(final StateType stateType, final Pieces pieces) {
		super(stateType, pieces);
	}

	@Override
	protected State start() {
		Pieces startPieces = new Pieces(new StartPieces().getInstance());
		return new Started(startPieces);
	}

	@Override
	protected State end() {
		throw new StateException("NotPlaying 상태에서 end 명령어는 유효하지 않습니다.");
	}

	@Override
	protected State move(String from, String to) {
		throw new StateException("NotPlaying 상태에서 move 명령어는 유효하지 않습니다.");
	}

	@Override
	protected State report() {
		return new Reported(pieces);
	}
}
