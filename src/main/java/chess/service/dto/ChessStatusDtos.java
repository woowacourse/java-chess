package chess.service.dto;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;

import chess.domain.chessGame.ChessStatus;

public class ChessStatusDtos {

	private final List<ChessStatusDto> chessStatusDtos;

	private ChessStatusDtos(final List<ChessStatusDto> chessStatusDtos) {
		this.chessStatusDtos = chessStatusDtos;
	}

	public static ChessStatusDtos of(final ChessStatus chessStatus) {
		Objects.requireNonNull(chessStatus, "체스 상태가 null입니다.");

		return chessStatus.getChessStatus().entrySet()
			.stream()
			.map(entry -> ChessStatusDto.of(entry.getKey(), entry.getValue()))
			.collect(collectingAndThen(toList(), ChessStatusDtos::new));
	}

	public List<ChessStatusDto> getChessStatusDtos() {
		return chessStatusDtos;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final ChessStatusDtos that = (ChessStatusDtos)object;
		return Objects.equals(chessStatusDtos, that.chessStatusDtos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chessStatusDtos);
	}

}
