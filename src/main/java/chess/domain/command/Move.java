package chess.domain.command;

import chess.domain.Position;

public class Move implements Command {

	private static final String RANKS = "abcdefgh";
	private static final String FILE = "12345678";
	private static final int COMMAND_LENGTH = 2;

	private final Position from;
	private final Position to;

	public Move(String from, String to) {
		validateCommand(from, to);
		this.from = new Position(convertFile(from), convertRank(from));
		this.to = new Position(convertFile(to), convertRank(to));
	}

	private void validateCommand(String from, String to) {
		if (!(from.length() == COMMAND_LENGTH) || !(to.length() == COMMAND_LENGTH)) {
			throw new IllegalArgumentException();
		}
		if (!RANKS.contains(from.substring(0, 1)) || !RANKS.contains(to.substring(0, 1))) {
			throw new IllegalArgumentException();
		}
		if (!FILE.contains(from.substring(1, 2)) || !FILE.contains(to.substring(1, 2))) {
			throw new IllegalArgumentException();
		}
	}

	private int convertRank(String from) {
		return from.charAt(0) - 'a' + 1;
	}

	private int convertFile(String from) {
		return from.charAt(1) - '0';
	}

	@Override
	public boolean isStart() {
		return false;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public boolean isMove() {
		return true;
	}

	@Override
	public Position getFromPosition() {
		return from;
	}

	@Override
	public Position getToPosition() {
		return to;
	}
}
