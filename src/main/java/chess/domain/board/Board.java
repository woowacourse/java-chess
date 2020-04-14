package chess.domain.board;

import static chess.domain.piece.Empty.*;
import static chess.domain.piece.Team.*;
import static chess.domain.position.Position.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
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
	private static final Pattern PIECES_PATTERN = Pattern.compile("([\\.pPbBrRkKnNqQ]{8}\\n){8}");
	private static final String ILLEGAL_BOARD_REGEX_EXCEPTION_MESSAGE = "문자열의 형태가 체스판의 형식이 아닙니다.";
	private static final String ROW_SEPARATOR = "\n";
	private static final String COLUMN_SEPARATOR = "";

	private final Map<Position, Piece> pieces;

	public Board() {
		this.pieces = new HashMap<>();
	}

	public Board(Map<Position, Piece> pieces) {
		this.pieces = Objects.requireNonNull(pieces);
	}

	public Board(String boards) {
		validateBoardRegex(Objects.requireNonNull(boards));
		pieces = new HashMap<>();
		parse(boards);
	}

	private void parse(String boards) {
		String[] boardRow = boards.split(ROW_SEPARATOR);
		for (int row = MINIMUM_POSITION_NUMBER; row <= MAXIMUM_POSITION_NUMBER; row++) {
			parseRow(boardRow[row - 1], row);
		}
	}

	private void parseRow(String boardRow, int row) {
		String[] rowElements = boardRow.split(COLUMN_SEPARATOR);
		for (int col = 1; col <= MAXIMUM_POSITION_NUMBER; col++) {
			parseOneElement(Position.of(col, MAXIMUM_POSITION_NUMBER + 1 - row), rowElements[col - 1]);
		}
	}

	private void parseOneElement(Position of, String rowElement) {
		if (".".equals(rowElement)) {
			return;
		}
		pieces.put(of, PieceFactory.of(rowElement));
	}

	private void validateBoardRegex(String boards) {
		if (!PIECES_PATTERN.matcher(boards).matches()) {
			throw new IllegalArgumentException(String.format(ILLEGAL_BOARD_REGEX_EXCEPTION_MESSAGE + "%s", boards));
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

	public String findSymbol(Position position) {
		return findPiece(position).getSymbol();
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
		for (int row = MAXIMUM_POSITION_NUMBER; row >= MINIMUM_POSITION_NUMBER; row--) {
			parseRowAsString(builder, row);
		}
		return builder.toString();
	}

	private void parseRowAsString(StringBuilder builder, int row) {
		for (int col = MINIMUM_POSITION_NUMBER; col <= MAXIMUM_POSITION_NUMBER; col++) {
			Piece piece = findPiece(Position.of(col, row));
			builder.append(piece.getSymbol());
		}
		builder.append(ROW_SEPARATOR);
	}
}
