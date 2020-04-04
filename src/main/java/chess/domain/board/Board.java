package chess.domain.board;

import static chess.domain.piece.Empty.*;
import static chess.domain.piece.Team.*;
import static chess.domain.position.Position.*;
import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Board {
	private static final int COUNT_OF_KING_TO_FINISH_GAME = 1;
	private final Map<Position, Piece> pieces;

	public Board() {
		this.pieces = new HashMap<>();
	}

	public Board(Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public void start() {
		pieces.clear();
		initAllBoardEmpty();
		initChessBoard(8, BLACK, 7);
		initChessBoard(1, WHITE, 2);
	}

	private void initAllBoardEmpty() {
		Position.values().forEach(position -> pieces.put(position, EMPTY));
	}

	private void initChessBoard(int othersRank, Team teamColor, int pawnRank) {
		pieces.put(Position.of("a" + othersRank), new Rook(teamColor));
		pieces.put(Position.of("b" + othersRank), new Knight(teamColor));
		pieces.put(Position.of("c" + othersRank), new Bishop(teamColor));
		pieces.put(Position.of("d" + othersRank), new Queen(teamColor));
		pieces.put(Position.of("e" + othersRank), new King(teamColor));
		pieces.put(Position.of("f" + othersRank), new Bishop(teamColor));
		pieces.put(Position.of("g" + othersRank), new Knight(teamColor));
		pieces.put(Position.of("h" + othersRank), new Rook(teamColor));

		for (int i = 0; i < MAXIMUM_POSITION_NUMBER; i++) {
			pieces.put(Position.of((char)('a' + i) + String.valueOf(pawnRank)), new Pawn(teamColor));
		}
	}

	public Map<Team, Double> status() {
		HashMap<Team, Double> collect = pieces.values().stream()
			.filter(Piece::isNotBlank)
			.collect(groupingBy(Piece::getTeam, HashMap::new, summingDouble(Piece::getScore)));

		for (int file = 1; file <= 8; file++) {
			int finalFile = file;
			Map<Team, Long> cnt = IntStream.rangeClosed(1, 8)
				.mapToObj(rank -> findPiece(Position.of(finalFile, rank)))
				.filter(Piece::isPawn)
				.collect(groupingBy(Piece::getTeam, counting()));

			cnt.keySet().stream()
				.filter(team -> cnt.get(team) == 1)
				.forEach(team -> collect.put(team, collect.get(team) + 0.5));
		}
		return collect;
	}

	public void move(Position from, Position to) {
		Piece source = requireNonEmpty(findPiece(from));
		Piece target = findPiece(to);
		validateSourceMovingRoute(from, to, source, target);
		updatePiecePosition(from, to, source);
		source.updateHasMoved();
	}

	public Piece findPiece(Position position) {
		return pieces.getOrDefault(Objects.requireNonNull(position), EMPTY);
	}

	private Piece requireNonEmpty(Piece piece) {
		if (piece.isBlank()) {
			throw new IllegalArgumentException("빈칸은 선택할 수 없습니다.");
		}
		return piece;
	}

	private void validateSourceMovingRoute(Position from, Position to, Piece source, Piece target) {
		BoardOccupyState occupyState = BoardOccupyState.of(source, target);
		occupyState.checkMovable(this, source, from, to);
	}

	private void updatePiecePosition(Position from, Position to, Piece source) {
		pieces.put(from, EMPTY);
		pieces.put(to, source);
	}

	public boolean isExistAnyPieceAt(List<Position> traces) {
		return traces.stream()
			.anyMatch(trace -> findPiece(trace).isNotBlank());
	}

	public Map<Position, Piece> getPieces() {
		return Collections.unmodifiableMap(this.pieces);
	}

	public boolean isNotSameTeamFromPosition(Position position, Team team) {
		return !findPiece(position).isRightTeam(team);
	}

	public boolean containsSingleKingWith(Team team) {
		return containsSingleKing() && matchAllKings(team);
	}

	private boolean containsSingleKing() {
		long countOfKing = getPieceStream().count();
		return countOfKing == COUNT_OF_KING_TO_FINISH_GAME;
	}

	private boolean matchAllKings(Team team) {
		return getPieceStream().allMatch(piece -> piece.isRightTeam(team));
	}

	private Stream<Piece> getPieceStream() {
		return pieces.values().stream()
			.filter(Piece::isKing);
	}

	public boolean containsNotSingleKingWith(Team team) {
		return !containsSingleKingWith(team);
	}
}
