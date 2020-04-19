package chess.domain.factory;

import static chess.domain.factory.BoardIndex.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Row;
import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;
import chess.domain.position.Position;
import chess.dto.ChessDTO;

public class BoardFactory {
	private static final String NOT_MATCH_ALL_PIECE_NUMBER_MESSAGE = "64개의 ChessDTO가 아닙니다.";
	private static final String NOT_MATCH_POSITION_MESSAGE = "찾을 수 없는 포지션입니다.";
	private static final String POSITION_FORMAT = "%c%d";

	public static ChessBoard createBoard() {
		List<Row> board = new ArrayList<>();
		board.addAll(createBlackTeam());
		board.addAll(createBlankTeam());
		board.addAll(createWhiteTeam());
		return new ChessBoard(board, new Turn(true));
	}

	private static List<Row> createBlackTeam() {
		return Arrays.asList(
			createExecutive(BLACK_TEAM_EXECUTIVE_INDEX.get(), Team.BLACK),
			createPawns(BLACK_TEAM_PAWN_INDEX.get(), Team.BLACK));
	}

	private static List<Row> createBlankTeam() {
		List<Row> rows = new ArrayList<>();
		for (int index = BLACK_TO_INDEX.get(); index >= BLACK_FROM_INDEX.get(); index--) {
			rows.add(createBlanks(index));
		}
		return rows;
	}

	private static List<Row> createWhiteTeam() {
		return Arrays.asList(
			createPawns(WHITE_TEAM_PAWN_INDEX.get(), Team.WHITE),
			createExecutive(WHITE_TEAM_EXECUTIVE_INDEX.get(), Team.WHITE));
	}

	private static Row createExecutive(int index, Team team) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		chessPieces.add(new Rook(Position.of(index, ROOK_FIRST_INDEX.get()), team));
		chessPieces.add(new Knight(Position.of(index, KNIGHT_FIRST_INDEX.get()), team));
		chessPieces.add(new Bishop(Position.of(index, BISHOP_FIRST_INDEX.get()), team));
		chessPieces.add(new Queen(Position.of(index, QUEEN_INDEX.get()), team));
		chessPieces.add(new King(Position.of(index, KING_INDEX.get()), team));
		chessPieces.add(new Bishop(Position.of(index, BISHOP_SECOND_INDEX.get()), team));
		chessPieces.add(new Knight(Position.of(index, KNIGHT_SECOND_INDEX.get()), team));
		chessPieces.add(new Rook(Position.of(index, ROOK_SECOND_INDEX.get()), team));
		return new Row(chessPieces);
	}

	private static Row createPawns(int index, Team team) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		for (int y = BOARD_FROM_INDEX.get(); y <= BOARD_TO_INDEX.get(); y++) {
			chessPieces.add(new Pawn(Position.of(index, y), team));
		}
		return new Row(chessPieces);
	}

	private static Row createBlanks(int index) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		for (int y = BOARD_FROM_INDEX.get(); y <= BOARD_TO_INDEX.get(); y++) {
			chessPieces.add(new Blank(Position.of(index, y)));
		}
		return new Row(chessPieces);
	}

	public static ChessBoard createBoard(List<ChessDTO> chessDTOS, Turn turn) {
		if (chessDTOS.size() != ALL_PIECE_NUMBER.get()) {
			throw new IllegalArgumentException(NOT_MATCH_ALL_PIECE_NUMBER_MESSAGE);
		}
		List<Row> rows = new ArrayList<>();
		for (int x = BOARD_FROM_INDEX.get(); x <= BOARD_TO_INDEX.get(); x++) {
			rows.add(createRow(chessDTOS, x));
		}
		return new ChessBoard(rows, turn);
	}

	private static Row createRow(List<ChessDTO> chessDTOS, int x) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		for (int y = ROW_FROM_INDEX.get(); y <= ROW_TO_INDEX.get(); y++) {
			ChessDTO chessDTO = findByPosition(chessDTOS, String.format(POSITION_FORMAT, y, x));
			String name = chessDTO.getName();
			String position = chessDTO.getPosition();
			chessPieces.add(PieceConverter.convert(name, position));
		}
		return new Row(chessPieces);
	}

	private static ChessDTO findByPosition(List<ChessDTO> chessDTOS, String position) {
		return chessDTOS.stream()
			.filter(chessDTO -> chessDTO.getPosition().equals(position))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(NOT_MATCH_POSITION_MESSAGE));
	}
}
