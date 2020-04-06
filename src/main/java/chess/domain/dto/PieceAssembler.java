package chess.domain.dto;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;
import java.util.stream.Collectors;

public class PieceAssembler {
	private PieceAssembler() {
	}

	public static PieceDto createDto(Piece piece) {
		Position position = piece.getPosition();
		Column col = position.getCol();
		Row row = position.getRow();
		return new PieceDto(PieceNameConverter.toName(piece), col.getValue(), row.getSymbol());
	}

	public static List<PieceDto> createDtos(ChessBoard chessBoard) {
		List<Piece> pieces = chessBoard.getPieces();
		return pieces.stream()
				.map(PieceAssembler::createDto)
				.collect(Collectors.toList());
	}
}
