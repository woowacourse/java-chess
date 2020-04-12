package chess.db;

import java.util.Arrays;
import java.util.function.Function;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.state.State;

/**
 *    디비의 정보와 흰 체스 말을 매핑해주는 enum입니다.
 *
 *    @author AnHyungJu
 */
public enum WhitePieceMapper {
	WHITE_KING("♔", state -> new King(state, "♔")),
	WHITE_QUEEN("♕", state -> new Queen(state, "♕")),
	WHITE_KNIGHT("♘", state -> new Knight(state, "♘")),
	WHITE_BISHOP("♗", state -> new Bishop(state, "♗")),
	WHITE_ROOK("♖", state -> new Rook(state, "♖")),
	WHITE_PAWN("♙", state -> new Pawn(state, "♙"));

	private String symbol;
	private Function<State, Piece> pieceMapper;

	WhitePieceMapper(String symbol,
		Function<State, Piece> pieceMapper) {
		this.symbol = symbol;
		this.pieceMapper = pieceMapper;
	}

	public static Piece mappingBy(String pieceSymbol, String pieceState) {
		return Arrays.stream(WhitePieceMapper.values())
			.filter(s -> s.symbol.equals(pieceSymbol))
			.findFirst()
			.map(mapper -> mapper.pieceMapper.apply(StateMapper.of(pieceState)))
			.orElse(null);
	}
}
