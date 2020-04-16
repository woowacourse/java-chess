package chess.service.dto;

import java.util.Objects;

import chess.domain.chessGame.ChessGame;

public class ChessGameDto {

	private final ChessBoardDto chessBoardDto;
	private final PieceColorDto pieceColorDto;
	private final ChessStatusDtos chessStatusDtos;
	private final boolean isEndState;
	private final boolean isKingCaught;

	private ChessGameDto(final ChessBoardDto chessBoardDto, final PieceColorDto pieceColorDto,
		final ChessStatusDtos chessStatusDtos, final boolean isEndState, final boolean isKingCaught) {
		this.chessBoardDto = chessBoardDto;
		this.pieceColorDto = pieceColorDto;
		this.chessStatusDtos = chessStatusDtos;
		this.isEndState = isEndState;
		this.isKingCaught = isKingCaught;
	}

	public static ChessGameDto of(final ChessGame chessGame) {
		Objects.requireNonNull(chessGame, "체스 게임이 null입니다.");

		final ChessBoardDto chessBoardDto = ChessBoardDto.of(chessGame.getChessBoard());
		final PieceColorDto pieceColorDto = PieceColorDto.of(chessGame.getCurrentPieceColor());
		final ChessStatusDtos chessStatusDtos = ChessStatusDtos.of(chessGame.getChessGameStatus());
		final boolean isEndStatus = chessGame.isEndState();
		final boolean isKingCaught = chessGame.isKingCaught();

		return new ChessGameDto(chessBoardDto, pieceColorDto, chessStatusDtos, isEndStatus, isKingCaught);
	}

	public ChessBoardDto getChessBoardDto() {
		return chessBoardDto;
	}

	public PieceColorDto getPieceColorDto() {
		return pieceColorDto;
	}

	public ChessStatusDtos getChessStatusDtos() {
		return chessStatusDtos;
	}

	public boolean isEndState() {
		return isEndState;
	}

	public boolean isKingCaught() {
		return isKingCaught;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final ChessGameDto that = (ChessGameDto)object;
		return isEndState == that.isEndState &&
			isKingCaught == that.isKingCaught &&
			Objects.equals(chessBoardDto, that.chessBoardDto) &&
			Objects.equals(pieceColorDto, that.pieceColorDto) &&
			Objects.equals(chessStatusDtos, that.chessStatusDtos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chessBoardDto, pieceColorDto, chessStatusDtos, isEndState, isKingCaught);
	}

}
