package chess.view;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class OutputRenderer {

	private static final int BOARD_SIZE = 8;
	private static final Map<PieceType, String> PIECE_TO_STRING = new EnumMap<>(PieceType.class);

	static {
		PIECE_TO_STRING.put(PieceType.EMPTY, ".");
		PIECE_TO_STRING.put(PieceType.KING, "K");
		PIECE_TO_STRING.put(PieceType.QUEEN, "Q");
		PIECE_TO_STRING.put(PieceType.ROOK, "R");
		PIECE_TO_STRING.put(PieceType.KNIGHT, "N");
		PIECE_TO_STRING.put(PieceType.BISHOP, "B");
		PIECE_TO_STRING.put(PieceType.PAWN, "P");
		PIECE_TO_STRING.put(PieceType.INITIAL_PAWN, "P");
	}

	public static BoardDto toBoardDto(final Map<Position, Piece> board) {
		List<Position> positions = new ArrayList<>(board.keySet());
		sortPositions(positions);

		List<List<String>> boardDto = stringifyPieces(board, positions);
		return new BoardDto(boardDto);
	}

	private static void sortPositions(final List<Position> positions) {
		positions.sort((p1, p2) -> {
			if (p1.getRow() == p2.getRow()) {
				return p1.getColumn() - p2.getColumn();
			}
			return p2.getRow() - p1.getRow();
		});
	}

	private static List<List<String>> stringifyPieces(final Map<Position, Piece> board, final List<Position> positions) {
		List<List<String>> boardDto = new ArrayList<>();
		for (int i = 0; i < BOARD_SIZE; i++) {
			List<String> line = stringifyLine(board, positions, i);
			boardDto.add(line);
		}
		return boardDto;
	}

	private static List<String> stringifyLine(
		final Map<Position, Piece> board,
		final List<Position> positions,
		final int column
	) {
		List<String> line = new ArrayList<>();
		for (int j = 0; j < BOARD_SIZE; j++) {
			Piece piece = board.get(positions.get(j + BOARD_SIZE * column));
			line.add(stringifySign(piece));
		}
		return line;
	}

	private static String stringifySign(final Piece piece) {
		String sign = PIECE_TO_STRING.get(piece.getType());
		if (piece.isGivenTeam(Team.WHITE)) {
			sign = sign.toLowerCase();
		}
		return sign;
	}
}
