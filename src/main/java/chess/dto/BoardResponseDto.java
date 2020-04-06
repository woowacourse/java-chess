package chess.dto;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.GameManager;

public class BoardResponseDto {
	private List<PieceResponseDto> pieces;
	private String turn;

	public BoardResponseDto(GameManager gameManager) {
		this.turn = gameManager.getTurn();
		this.pieces = gameManager.getPieces()
				.entrySet()
				.stream()
				.map(cell -> new PieceResponseDto(cell.getKey(), cell.getValue()))
				.collect(Collectors.toList());
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public List<PieceResponseDto> getPieces() {
		return pieces;
	}

	public void setPieces(List<PieceResponseDto> pieces) {
		this.pieces = pieces;
	}
}
