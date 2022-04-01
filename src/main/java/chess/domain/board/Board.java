package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Board {

	private static final String BLOCK_ERROR = "다른 기물에 막혀 해당 위치로 기물을 옮길 수 없습니다.";

	private final Map<Position, Piece> board;

	public Board(final Map<Position, Piece> board) {
		this.board = new HashMap<>(board);
	}

	public boolean isAlly(Position position, Team team) {
		return board.get(position).isAlly(team);
	}

	public boolean isCheck(final Position target) {
		return board.get(target).isKing();
	}

	public void move(Position source, Position target) {
		Piece piece = board.get(source);
		validateMove(source, target, piece);
		board.put(target, piece);
		board.put(source, new Blank());
	}

	private void validateMove(final Position source, final Position target, final Piece piece) {
		piece.validateMovement(source, target, board.get(target));
		validateBlocking(source, target, piece);
	}

	private void validateBlocking(final Position source, final Position target, final Piece piece) {
		Direction direction = piece.getDirection(source, target);
		Position checkPosition = source;
		while (checkPosition != target) {
			checkPosition = checkPosition.addDirection(direction);
			Piece currentPiece = board.get(checkPosition);
			checkBlocking(target, checkPosition, currentPiece);
		}
	}

	private void checkBlocking(final Position target, final Position checkPosition, final Piece currentPiece) {
		if (checkPosition != target && !currentPiece.isBlank()) {
			throw new IllegalArgumentException(BLOCK_ERROR);
		}
	}

	public List<Piece> getAllyPiecesByFile(final Team team, final File file) {
		return Arrays.stream(Rank.values())
				.map(rank -> Position.of(rank, file))
				.map(board::get)
				.filter(piece -> piece.isAlly(team))
				.collect(Collectors.toList());
	}

	public Map<Position, Piece> getBoard() {
		return new HashMap<>(board);
	}
}
