package chess.service.dto;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Objects;

import chess.domain.chessBoard.ChessBoard;
import chess.web.PieceNameConverter;

public class ChessBoardDto {

	private final Map<String, String> chessBoard;

	private ChessBoardDto(final Map<String, String> chessBoard) {
		this.chessBoard = chessBoard;
	}

	public static ChessBoardDto of(final ChessBoard chessBoard) {
		Objects.requireNonNull(chessBoard, "체스 보드가 null입니다.");

		return chessBoard.getChessBoard().entrySet()
			.stream()
			.collect(collectingAndThen(
				toMap(
					entry -> entry.getKey().key(),
					entry -> PieceNameConverter.of(entry.getValue().getName()).getImageFileName()
				), ChessBoardDto::new));
	}

	public Map<String, String> getChessBoard() {
		return chessBoard;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final ChessBoardDto that = (ChessBoardDto)object;
		return Objects.equals(chessBoard, that.chessBoard);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chessBoard);
	}

}
