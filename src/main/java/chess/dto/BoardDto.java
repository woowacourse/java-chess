package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.pieces.Pieces;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardDto {
	private final Map<Position, PieceDto> board = new HashMap<>();

	public BoardDto(Pieces pieces) { // TODO: 2020/04/09 키 벨류면 a2이렇게가 키인데 이러면 클라가 파악을 못함
		for (Piece piece : pieces.getPieces()) {
			board.put(piece.getPosition(), new PieceDto(piece));
		}
	}

	public Map<Position, PieceDto> getBoard() {
		return board;
	}
}