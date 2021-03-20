package chess.domain.board;

import chess.domain.piece.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {
	public static final int BOARD_SIZE = 8;
	private static final double PAWN_PUNISHMENT_RATIO = 0.5;
	private static final int NUMBER_OF_KINGS = 2;
	private static final int MINIMUM_PUNISHABLE_PAWN_COUNT = 2;
	private static final String BLANK_MOVE_ERROR = "공백은 움직일 수 없습니다.";

	private final Map<Position, Piece> board = new LinkedHashMap<>();

	public ChessBoard() {
		initBlank();
	}

	public void initBoard() {
		initBlack();
		initWhite();
	}

	private void initBlank() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board.put(Position.of(i, j), new Blank(Color.NO_COLOR, Position.of(i, j)));
			}
		}
	}

	private void initBlack() {
		replace(Position.of("a8"), new Rook(Color.BLACK, Position.of("a8")));
		replace(Position.of("b8"), new Knight(Color.BLACK, Position.of("b8")));
		replace(Position.of("c8"), new Bishop(Color.BLACK, Position.of("c8")));
		replace(Position.of("d8"), new Queen(Color.BLACK, Position.of("d8")));
		replace(Position.of("e8"), new King(Color.BLACK, Position.of("e8")));
		replace(Position.of("f8"), new Bishop(Color.BLACK, Position.of("f8")));
		replace(Position.of("g8"), new Knight(Color.BLACK, Position.of("g8")));
		replace(Position.of("h8"), new Rook(Color.BLACK, Position.of("h8")));

		replace(Position.of("a7"), new Pawn(Color.BLACK, Position.of("a7")));
		replace(Position.of("b7"), new Pawn(Color.BLACK, Position.of("b7")));
		replace(Position.of("c7"), new Pawn(Color.BLACK, Position.of("c7")));
		replace(Position.of("d7"), new Pawn(Color.BLACK, Position.of("d7")));
		replace(Position.of("e7"), new Pawn(Color.BLACK, Position.of("e7")));
		replace(Position.of("f7"), new Pawn(Color.BLACK, Position.of("f7")));
		replace(Position.of("g7"), new Pawn(Color.BLACK, Position.of("g7")));
		replace(Position.of("h7"), new Pawn(Color.BLACK, Position.of("h7")));
	}

	private void initWhite() {
		replace(Position.of("a1"), new Rook(Color.WHITE, Position.of("a1")));
		replace(Position.of("b1"), new Knight(Color.WHITE, Position.of("b1")));
		replace(Position.of("c1"), new Bishop(Color.WHITE, Position.of("c1")));
		replace(Position.of("d1"), new Queen(Color.WHITE, Position.of("d1")));
		replace(Position.of("e1"), new King(Color.WHITE, Position.of("e1")));
		replace(Position.of("f1"), new Bishop(Color.WHITE, Position.of("f1")));
		replace(Position.of("g1"), new Knight(Color.WHITE, Position.of("g1")));
		replace(Position.of("h1"), new Rook(Color.WHITE, Position.of("h1")));

		replace(Position.of("a2"), new Pawn(Color.WHITE, Position.of("a2")));
		replace(Position.of("b2"), new Pawn(Color.WHITE, Position.of("b2")));
		replace(Position.of("c2"), new Pawn(Color.WHITE, Position.of("c2")));
		replace(Position.of("d2"), new Pawn(Color.WHITE, Position.of("d2")));
		replace(Position.of("e2"), new Pawn(Color.WHITE, Position.of("e2")));
		replace(Position.of("f2"), new Pawn(Color.WHITE, Position.of("f2")));
		replace(Position.of("g2"), new Pawn(Color.WHITE, Position.of("g2")));
		replace(Position.of("h2"), new Pawn(Color.WHITE, Position.of("h2")));
	}

	public void replace(Position position, Piece piece) {
		board.replace(position, piece);
	}

	public void move(String source, String target) {
		Position sourcePosition = Position.of(source);
		Position targetPosition = Position.of(target);
		Direction direction = Path.findDirection(this, sourcePosition, targetPosition);

		Piece sourcePiece = getPieceAt(sourcePosition);
		sourcePiece.move(this, direction, targetPosition);

		board.replace(targetPosition, sourcePiece);
		board.replace(sourcePosition, new Blank(Color.NO_COLOR, sourcePosition));
	}

	public List<Direction> getCandidateDirections(Position position) {
		return board.get(position).directions();
	}

	public Map<Position, Piece> getChessBoard() {
		return board;
	}

	public Piece getPieceAt(Position position) {
		return board.get(position);
	}

	public boolean isOngoing() {
		long kingCount = board.values()
				.stream()
				.filter(Piece::isKing)
				.count();
		return kingCount == NUMBER_OF_KINGS;
	}

	public double getScore(Color color) {
		double score = calculateScore(color);
		Map<Column, Long> pawnCountPerColumn = calculatePawnCount(color);
		double punishmentScore = calculatePunishmentScore(pawnCountPerColumn);
		return score - punishmentScore;
	}

	private double calculateScore(Color color) {
		return board.values()
				.stream()
				.filter(piece -> piece.isSameColor(color))
				.mapToDouble(Piece::score)
				.sum();
	}

	private Map<Column, Long> calculatePawnCount(Color color) {
		return board.entrySet()
				.stream()
				.filter(positionPieceEntry -> positionPieceEntry.getValue().isPawn())
				.filter(positionPieceEntry -> positionPieceEntry.getValue().isSameColor(color))
				.collect(Collectors.groupingBy(positionPieceEntry -> positionPieceEntry.getKey().getColumn(), Collectors.counting()));
	}

	private double calculatePunishmentScore(Map<Column, Long> pawnCountPerColumn) {
		return pawnCountPerColumn.values()
				.stream()
				.filter(count -> count >= MINIMUM_PUNISHABLE_PAWN_COUNT)
				.mapToDouble(count -> count * PAWN_PUNISHMENT_RATIO)
				.sum();
	}
}
