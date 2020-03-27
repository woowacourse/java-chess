package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardFactory {
	private ChessBoardFactory() {
	}

	public static ChessBoard create() {
		List<Piece> chessBoard = new ArrayList<>();
		chessBoard.addAll(createNoble(Side.BLACK));
		chessBoard.addAll(createPawn(Side.BLACK));
		chessBoard.addAll(createNoble(Side.WHITE));
		chessBoard.addAll(createPawn(Side.WHITE));
		return new ChessBoard(chessBoard);
	}

	public static List<Piece> createPawn(Side side) {
		return Arrays.stream(Column.values())
				.map(col -> new Pawn(side, new Position(col, side.getInitPawnRow())))
				.collect(Collectors.toList());
	}

	public static List<Piece> createNoble(Side side) {
		Row row = side.getInitNobleRow();
		return Arrays.asList(
				new Rook(side, new Position("a", row)),
				new Knight(side, new Position("b", row)),
				new Bishop(side, new Position("c", row)),
				new Queen(side, new Position("d", row)),
				new King(side, new Position("e", row)),
				new Bishop(side, new Position("f", row)),
				new Knight(side, new Position("g", row)),
				new Rook(side, new Position("h", row))
		);
	}
}
