package chess.web.dto;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Objects;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.Position;
import chess.web.PieceNameConverter;

public class ChessBoardDto {

	private final Map<String, String> chessBoard;

	private ChessBoardDto(final Map<String, String> chessBoard) {
		this.chessBoard = chessBoard;
	}

	public static ChessBoardDto of(Map<Position, ChessPiece> chessBoard) {
		validate(chessBoard);
		return chessBoard.entrySet().stream()
			.collect(collectingAndThen(
				toMap(
					entry -> entry.getKey().key(),
					entry -> PieceNameConverter.of(entry.getValue().getName()).getImageUrl()
				), ChessBoardDto::new));
	}

	private static void validate(final Map<Position, ChessPiece> chessBoard) {
		if (Objects.isNull(chessBoard) || chessBoard.isEmpty()) {
			throw new IllegalArgumentException("체스 보드가 존재하지 않습니다.");
		}
	}

	public Map<String, String> getChessBoard() {
		return chessBoard;
	}

}
