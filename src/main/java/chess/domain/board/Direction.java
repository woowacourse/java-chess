package chess.domain.board;

import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public enum Direction {

	S(-1, 0),
	W(0, -1),
	N(1, 0),
	E(0, 1),
	SS(-2, 0),
	NN(2, 0),
	SW(-1, -1),
	SE(-1, 1),
	NW(1, -1),
	NE(1, 1),
	NNE(2, 1),
	NNW(2, -1),
	SSE(-2, 1),
	SSW(-2, -1),
	EEN(1, 2),
	EES(-1, 2),
	WWN(1, -2),
	WWS(-1, -2);

	private static final String NOT_SEARCH_DIRECTION_ERROR = "해당 방향이 없습니다.";

	private final int rankMovement;
	private final int fileMovement;

	Direction(int rankMovement, int fileMovement) {
		this.rankMovement = rankMovement;
		this.fileMovement = fileMovement;
	}

	public static List<Direction> getKnightDirection() {
		return List.of(SSE, SSW, NNE, NNW, EEN, EES, WWN, WWS);
	}

	public static List<Direction> getKingDirection() {
		return List.of(E, W, S, N, NE, NW, SE, SW);
	}

	public static List<Direction> getPawnDirection(Team team, boolean isAttack) {
		if (team.isBlack() && isAttack) {
			return List.of(SE, SW);
		}
		if (team.isBlack()) {
			return List.of(S);
		}
		if (isAttack) {
			return List.of(NE, NW);
		}
		return List.of(N);
	}

	public static Direction getDefaultPawnByTeam(final Team team) {
		if (team.isBlack()) {
			return SS;
		}
		return NN;
	}

	public static Direction find(final int differenceRank, final int differenceFile) {
		return Arrays.stream(values())
				.filter(value -> value.rankMovement == differenceRank && value.fileMovement == differenceFile)
				.findAny()
				.orElseThrow(() -> new NoSuchElementException(NOT_SEARCH_DIRECTION_ERROR));
	}

	public Optional<Rank> addRank(final Rank rank) {
		return rank.add(rankMovement);
	}

	public Optional<File> addFile(final File file) {
		return file.add(fileMovement);
	}
}
