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
import chess.domain.piece.PieceFactory;
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

	public Board(String boards) {
		pieces = new HashMap<>();
		if ("".equals(boards))
			return;
		String[] split = boards.split("\n");
		for (int i = 0; i < split.length; i++) {
			String s = split[i];
			String[] split1 = s.split("");
			for (int j = 0; j < split1.length; j++) {
				String s1 = split1[j];
				if (s1.equals("."))
					continue;
				pieces.put(Position.of(j + 1, 8 - i), PieceFactory.of(s1));
			}
		}
	}

	public void start() {
		pieces.clear();
		initChessBoard(8, BLACK, 7);
		initChessBoard(1, WHITE, 2);
	}

	private void initChessBoard(int othersRow, Team team, int pawnsRow) {
		initOthers(othersRow, team);
		initAllPawns(team, pawnsRow);
	}

	private void initOthers(int othersRow, Team team) {
		pieces.put(Position.of("a" + othersRow), new Rook(team));
		pieces.put(Position.of("b" + othersRow), new Knight(team));
		pieces.put(Position.of("c" + othersRow), new Bishop(team));
		pieces.put(Position.of("d" + othersRow), new Queen(team));
		pieces.put(Position.of("e" + othersRow), new King(team));
		pieces.put(Position.of("f" + othersRow), new Bishop(team));
		pieces.put(Position.of("g" + othersRow), new Knight(team));
		pieces.put(Position.of("h" + othersRow), new Rook(team));
	}

	private void initAllPawns(Team team, int pawnsRow) {
		for (int i = 0; i < MAXIMUM_POSITION_NUMBER; i++) {
			pieces.put(Position.of((char)('a' + i) + String.valueOf(pawnsRow)), new Pawn(team));
		}
	}

	// TODO: 2020/04/04 해당 기능 Result 클래스로 이동
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
	}

	private Piece requireNonEmpty(Piece piece) {
		if (piece.isBlank()) {
			throw new IllegalArgumentException("빈칸은 선택할 수 없습니다.");
		}
		return piece;
	}

	public Piece findPiece(Position position) {
		return pieces.getOrDefault(Objects.requireNonNull(position), EMPTY);
	}

	private void validateSourceMovingRoute(Position from, Position to, Piece source, Piece target) {
		BoardOccupyState occupyState = BoardOccupyState.of(source, target);
		occupyState.checkMovable(this, source, from, to);
	}

	private void updatePiecePosition(Position from, Position to, Piece source) {
		pieces.remove(from);
		pieces.put(to, source);
	}

	public boolean isExistAnyPieceAt(List<Position> traces) {
		return traces.stream()
			.anyMatch(trace -> findPiece(trace).isNotBlank());
	}

	public boolean isNotSameTeamFromPosition(Position position, Team team) {
		return !findPiece(position).isRightTeam(team);
	}

	public boolean containsNotSingleKingWith(Team team) {
		return !containsSingleKingWith(team);
	}

	public boolean containsSingleKingWith(Team team) {
		return containsSingleKing() && matchAllKings(team);
	}

	private boolean containsSingleKing() {
		long countOfKing = getPiecesStreamContainsOnlyKing().count();
		return countOfKing == COUNT_OF_KING_TO_FINISH_GAME;
	}

	private boolean matchAllKings(Team team) {
		return getPiecesStreamContainsOnlyKing().allMatch(piece -> piece.isRightTeam(team));
	}

	private Stream<Piece> getPiecesStreamContainsOnlyKing() {
		return pieces.values().stream()
			.filter(Piece::isKing);
	}

	public Map<Position, Piece> getPieces() {
		return Collections.unmodifiableMap(this.pieces);
	}

	public String getAsString() {
		StringBuilder builder = new StringBuilder();
		for (int rank = 8; rank >= 1; rank--) {
			for (int file = 1; file <= 8; file++) {
				Piece piece = findPiece(Position.of(file, rank));
				builder.append(piece.getSymbol());
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
