package chess.dto;

import java.util.List;

import chess.domain.piece.PieceMapper;
import chess.domain.piece.Pieces;

public class PiecesResponseDTO {
	List<PieceResponseDTO> pieces;

	public PiecesResponseDTO(Pieces originPieces) {
		this.pieces = PieceMapper.getInstance().createPiecesResponseDTO(originPieces);
	}

	public List<PieceResponseDTO> getPieces() {
		return pieces;
	}
}
