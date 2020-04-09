package chess.domain.piece;

import static chess.domain.service.ChessGameService.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

import chess.domain.Side;
import chess.domain.position.Position;

public enum PieceFactory {
	ROOK("r", (side, position) -> Optional.of(new Rook(side, position))),
	KNIGHT("n", (side, position) -> Optional.of(new Knight(side, position))),
	BISHOP("b", (side, position) -> Optional.of(new Bishop(side, position))),
	QUEEN("q", (side, position) -> Optional.of(new Queen(side, position))),
	KING("k", (side, position) -> Optional.of(new King(side, position))),
	PAWN("p", (side, position) -> Optional.of(new Pawn(side, position))),
	BLANK(BLANK_MARK, (side, position) -> Optional.empty());

	private String mark;
	private BiFunction<Side, Position, Optional<Piece>> constructor;

	PieceFactory(String mark,
			BiFunction<Side, Position, Optional<Piece>> constructor) {
		this.mark = mark;
		this.constructor = constructor;
	}

	public static Optional<Piece> of(String name, Side side, Position position) {
		return Arrays.stream(values())
				.filter(pieceFactory -> pieceFactory.mark.equals(name))
				.map(pieceFactory -> pieceFactory.create(side, position))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("해당 기물이 없습니다."));
	}

	public Optional<Piece> create(Side side, Position position) {
		return constructor.apply(side, position);
	}
}
