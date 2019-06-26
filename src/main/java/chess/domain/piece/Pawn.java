package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.*;

public class Pawn extends Piece {
	private static final int PAWN_SCORE = 1;
	private static final int MAX_DISTANCE_OF_INITIAL_PAWN = 2;
	private static final int MAX_DISTANCE_OF_DEFAULT_PAWN = 1;
	private static int WHITE_INITIAL_Y = 2;
	private static int BLACK_INITIAL_Y = 7;

	private final List<MovementInfo> movementInfos;
	private final List<MovementInfo> attackMovementInfos;

	private Pawn(Player player, List<MovementInfo> movementInfos, List<MovementInfo> attackMovementInfos, Position position, Score score) {
		super(player, Type.PAWN, position, score);
		this.movementInfos = movementInfos;
		this.attackMovementInfos = attackMovementInfos;
	}

	public static Pawn valueOf(Player player, Position position) {
		return (player.equals(Player.WHITE))? getWhite(position) : getBlack(position);
	}

	private static Pawn getWhite(Position position) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.TOP, MAX_DISTANCE_OF_INITIAL_PAWN)));
		List<MovementInfo> attackMovementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT_TOP, MAX_DISTANCE_OF_DEFAULT_PAWN),
				new MovementInfo(Direction.RIGHT_TOP, MAX_DISTANCE_OF_DEFAULT_PAWN)));

		return new Pawn(Player.WHITE, movementInfos, attackMovementInfos, position, new Score(PAWN_SCORE));
	}

	private static Pawn getBlack(Position position) {
		List<MovementInfo> movementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.BOTTOM, MAX_DISTANCE_OF_INITIAL_PAWN)));
		List<MovementInfo> attackMovementInfos = new ArrayList<>(Arrays.asList(
				new MovementInfo(Direction.LEFT_BOTTOM, MAX_DISTANCE_OF_DEFAULT_PAWN),
				new MovementInfo(Direction.RIGHT_BOTTOM, MAX_DISTANCE_OF_DEFAULT_PAWN)));

		return new Pawn(Player.BLACK, movementInfos, attackMovementInfos, position, new Score(PAWN_SCORE));
	}

	@Override
	public Path getMovablePath(Position end) {
		List<MovementInfo> movementInfos = new ArrayList<>();
		if (this.isMine(Player.BLACK)) {
			return getValidPath(end, generateMovementInfosForBlackPawn(movementInfos));
		}
		return getValidPath(end, generateMovementInfosForWhitePawn(movementInfos));
	}

	public List<MovementInfo> generateMovementInfosForWhitePawn(List<MovementInfo> movementInfos) {
		if (position.getCoordinateY() == WHITE_INITIAL_Y) {
			movementInfos.add(new MovementInfo(Direction.TOP, MAX_DISTANCE_OF_INITIAL_PAWN));
			return movementInfos;
		}
		movementInfos.add(new MovementInfo(Direction.TOP, MAX_DISTANCE_OF_DEFAULT_PAWN));
		return movementInfos;
	}

	public List<MovementInfo> generateMovementInfosForBlackPawn(List<MovementInfo> movementInfos) {
		if (position.getCoordinateY() == BLACK_INITIAL_Y) {
			movementInfos.add(new MovementInfo(Direction.BOTTOM, MAX_DISTANCE_OF_INITIAL_PAWN));
			return movementInfos;
		}
		movementInfos.add(new MovementInfo(Direction.BOTTOM, MAX_DISTANCE_OF_DEFAULT_PAWN));
		return  movementInfos;
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public Path getAttackablePath(Position end) {
		return getValidPath(end, attackMovementInfos);
	}
}
