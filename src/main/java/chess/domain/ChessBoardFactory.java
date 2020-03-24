package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;

public class ChessBoardFactory {
	private ChessBoardFactory() {
	}

	public static ChessBoard create() {
		List<Piece> chessBoard = new ArrayList<>();
		chessBoard.addAll(createNoble(Side.BLACK, 1));
		chessBoard.addAll(createPawn(Side.BLACK, 2));
		chessBoard.addAll(createPawn(Side.WHITE, 7));
		chessBoard.addAll(createNoble(Side.WHITE, 8));
		return new ChessBoard(chessBoard);
	}

	public static List<Piece> createPawn(Side side, int row) {
		return IntStream.rangeClosed(1, 8)
				.mapToObj(i -> new Pawn(side, new Position(i, row)))
				.collect(Collectors.toList());
	}

	public static List<Piece> createNoble(Side side, int row) {
		List<Piece> nobleLine = new ArrayList<>();
		nobleLine.add(new Rook(side, new Position('a', row)));
		nobleLine.add(new Knight(side, new Position('b', row)));
		nobleLine.add(new Bishop(side, new Position('c', row)));
		nobleLine.add(new Queen(side, new Position('d', row)));
		nobleLine.add(new King(side, new Position('e', row)));
		nobleLine.add(new Bishop(side, new Position('f', row)));
		nobleLine.add(new Knight(side, new Position('g', row)));
		nobleLine.add(new Rook(side, new Position('h', row)));
		return nobleLine;
	}
}
