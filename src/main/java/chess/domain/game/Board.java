package chess.domain.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import chess.domain.piece.Color;
import chess.domain.piece.Path;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;
import chess.domain.piece.Symbol;
import chess.domain.piece.exception.NotMovableException;

public class Board {
	public static final Board EMPTY = new Board();
	private static final int BLACK_PIECES_Y = 7;
	private static final int BLACK_PAWNS_Y = 6;
	private static final int WHITE_PIECES_Y = 0;
	private static final int WHITE_PAWNS_Y = 1;
	private static final int INITIAL_KING_COUNT = 2;

	private Map<Position, Piece> board;

	private Board() {
		this.board = new TreeMap<>();
	}

	public static Board create() {
		Board board = new Board();

		List<Symbol> pieceSequence = Arrays.asList(
				Symbol.ROOK, Symbol.KNIGHT, Symbol.BISHOP, Symbol.QUEEN,
				Symbol.KING, Symbol.BISHOP, Symbol.KNIGHT, Symbol.ROOK
		);

		for (Position position : Position.values()) {
			board.setBlank(position);
		}

		for (int x = Position.BEGIN_X; x < Position.END_X; x++) {
			board.setPiece(Position.of(x, BLACK_PIECES_Y),
					PieceFactory.create(pieceSequence.get(x), Position.of(x, BLACK_PIECES_Y), Color.BLACK));
			board.setPiece(Position.of(x, BLACK_PAWNS_Y),
					PieceFactory.create(Symbol.PAWN, Position.of(x, BLACK_PAWNS_Y), Color.BLACK));
			board.setPiece(Position.of(x, WHITE_PAWNS_Y),
					PieceFactory.create(Symbol.PAWN, Position.of(x, WHITE_PAWNS_Y), Color.WHITE));
			board.setPiece(Position.of(x, WHITE_PIECES_Y),
					PieceFactory.create(pieceSequence.get(x), Position.of(x, WHITE_PIECES_Y), Color.WHITE));
		}

		return board;
	}

	private void setBlank(Position position) {
		board.put(position, PieceFactory.createBlank(position));
	}

	private void setPiece(Position position, Piece piece) {
		board.put(position, piece);
		piece.onMoveEvent(event -> {
			validateMove(event.getSource(), event.getTarget(), event.getPath());
			updatePosition(event.getSourcePosition(), event.getTargetPosition());
		});
	}

	public Piece findPiece(Position position) {
		return board.get(position);
	}

	public List<Piece> findPiecesByColor(Color color) {
		return board.values()
				.stream()
				.filter(piece -> piece.isSameColor(color))
				.collect(Collectors.toList());
	}

	public boolean isKingDead() {
		return board.values()
				.stream()
				.filter(Piece::isKing)
				.count() < INITIAL_KING_COUNT;
	}

	public boolean hasKing(Color color) {
		return board.values()
				.stream()
				.anyMatch(piece -> piece.isKing() && piece.isSameColor(color));
	}

	private void validateMove(Piece source, Piece target, Path path) {
		validateColor(source, target);
		validatePath(path);
	}

	private void validateColor(Piece source, Piece target) {
		if (source.isSameColor(target)) {
			throw new NotMovableException("목표 위치에 같은 색상의 말이 존재하여 이동할 수 없습니다.");
		}
	}

	private void validatePath(Path path) {
		path.forEachRemaining(this::validateBlank);
	}

	private void validateBlank(Position position) {
		Piece piece = board.get(position);
		if (!piece.isBlank()) {
			throw new NotMovableException("해당 경로에 장애물이 존재하여 이동할 수 없습니다.");
		}
	}

	private void updatePosition(Position source, Position target) {
		Piece piece = board.get(source);
		board.put(target, piece);
		setBlank(source);
	}

	public Map<Position, Piece> getBoard() {
		return Collections.unmodifiableMap(board);
	}
}
