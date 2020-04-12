package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardFactory {
	private ChessBoardFactory() {
	}

	public static ChessBoard create() {
		List<Piece> chessBoard = new LinkedList<>();
		for (Side side : Side.values()) {
			chessBoard.addAll(createNoble(side));
			chessBoard.addAll(createPawn(side));
		}
		return new ChessBoard(chessBoard);
	}

	private static List<Piece> createPawn(Side side) {
		return Arrays.stream(Column.values())
				.map(col -> new Pawn(side, Position.of(col, side.getInitPawnRow())))
				.collect(Collectors.toList());
	}

	private static List<Piece> createNoble(Side side) {
		Row row = side.getInitNobleRow();
		return Arrays.asList(
				new Rook(side, Position.of(Column.ONE, row)),
				new Knight(side, Position.of(Column.TWO, row)),
				new Bishop(side, Position.of(Column.THREE, row)),
				new Queen(side, Position.of(Column.FOUR, row)),
				new King(side, Position.of(Column.FIVE, row)),
				new Bishop(side, Position.of(Column.SIX, row)),
				new Knight(side, Position.of(Column.SEVEN, row)),
				new Rook(side, Position.of(Column.EIGHT, row))
		);
	}
}
