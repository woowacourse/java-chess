package chess.domain.dto;

import chess.domain.BoardConverter;
import chess.domain.ChessGame;
import chess.domain.FinishFlag;

public class ChessGameDto {
	private String roomName;
	private String board;
	private String turn;
	private String finishFlag;

	private ChessGameDto(String roomName, String board, String turn, String finishFlag) {
		this.roomName = roomName;
		this.board = board;
		this.turn = turn;
		this.finishFlag = finishFlag;
	}

	public static ChessGameDto of(String roomName, ChessGame chessGame) {
		String board = BoardConverter.convertToString(chessGame.getBoard());
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		return new ChessGameDto(roomName, board, turn, finishFlag);
	}

	public String getRoomName() {
		return roomName;
	}

	public String getBoard() {
		return board;
	}

	public String getTurn() {
		return turn;
	}

	public String getFinishFlag() {
		return finishFlag;
	}
}
