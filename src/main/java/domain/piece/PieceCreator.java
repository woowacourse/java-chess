package domain.piece;

import java.util.Arrays;
import java.util.function.Function;

import domain.piece.position.Position;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public enum PieceCreator {
	KING_WHITE("k", (position) -> new King(position, Team.WHITE)),
	QUEEN_WHITE("q", (position) -> new Queen(position, Team.WHITE)),
	ROOK_WHITE("r", (position) -> new Rook(position, Team.WHITE)),
	BISHOP_WHITE("b", (position) -> new Bishop(position, Team.WHITE)),
	KNIGHT_WHITE("n", (position) -> new Knight(position, Team.WHITE)),
	PAWN_WHITE("p", (position) -> new Pawn(position, Team.WHITE)),
	KING_BLACK("K", (position) -> new King(position, Team.BLACK)),
	QUEEN_BLACK("Q", (position) -> new Queen(position, Team.BLACK)),
	ROOK_BLACK("R", (position) -> new Rook(position, Team.BLACK)),
	BISHOP_BLACK("B", (position) -> new Bishop(position, Team.BLACK)),
	KNIGHT_BLACK("N", (position) -> new Knight(position, Team.BLACK)),
	PAWN_BLACK("P", (position) -> new Pawn(position, Team.BLACK));

	private String symbol;
	private Function<Position, Piece> pieceCreator;

	PieceCreator(String symbol, Function<Position, Piece> pieceCreator) {
		this.symbol = symbol;
		this.pieceCreator = pieceCreator;
	}

	public static PieceCreator of(String symbol) {
		return Arrays.stream(PieceCreator.values())
			.filter(pc -> pc.symbol.equals(symbol))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 심볼입니다."));
	}

	public Function<Position, Piece> getPieceCreator() {
		return pieceCreator;
	}
}
