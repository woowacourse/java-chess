package chess.db;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.state.Captured;
import chess.domain.piece.state.Initial;
import chess.domain.piece.state.Moved;
import chess.domain.piece.state.State;

/**
 *    디비의 정보와 체스 말을 매핑해주는 enum입니다.
 *
 *    @author AnHyungJu
 */
public enum PieceMapper {
	BLACK_KING("♚", state -> new King(state, "♚")),
	BLACK_QUEEN("♛", state -> new Queen(state, "♛")),
	BLACK_KNIGHT("♞", state -> new Knight(state, "♞")),
	BLACK_BISHOP("♝", state -> new Bishop(state, "♝")),
	BLACK_ROOK("♜", state -> new Rook(state, "♜")),
	BLACK_PAWN("♟", state -> new Pawn(state, "♟")),

	WHITE_KING("♔", state -> new King(state, "♔")),
	WHITE_QUEEN("♕", state -> new Queen(state, "♕")),
	WHITE_KNIGHT("♘", state -> new Knight(state, "♘")),
	WHITE_BISHOP("♗", state -> new Bishop(state, "♗")),
	WHITE_ROOK("♖", state -> new Rook(state, "♖")),
	WHITE_PAWN("♙", state -> new Pawn(state, "♙"));

	private String symbol;
	private Function<State, Piece> pieceMapper;

	PieceMapper(String symbol, Function<State, Piece> pieceMapper) {
		this.symbol = symbol;
		this.pieceMapper = pieceMapper;
	}

	public static Piece mappingBy(String pieceSymbol, String pieceState) {
		return Arrays.stream(PieceMapper.values())
			.filter(s -> s.symbol.equals(pieceSymbol))
			.findFirst()
			.map(mapper -> mapper.pieceMapper.apply(StateMapper.of(pieceState)))
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Symbol입니다."));
	}

	private enum StateMapper {
		INITIAL("Initial", Initial::new),
		MOVED("Moved", Moved::new),
		CAPTURED("Captured", Captured::new);

		private String state;
		private Supplier<State> stateMapper;

		StateMapper(String state, Supplier<State> stateMapper) {
			this.state = state;
			this.stateMapper = stateMapper;
		}

		private static State of(String pieceState) {
			return Arrays.stream(StateMapper.values())
				.filter(s -> s.state.equals(pieceState))
				.findFirst()
				.map(s -> s.stateMapper.get())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태입니다."));
		}
	}
}
