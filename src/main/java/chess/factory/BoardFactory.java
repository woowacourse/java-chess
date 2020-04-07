package chess.factory;

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
import chess.view.OutputView;

public class BoardFactory {
	private static final int BLACK_TEAM_EXECUTIVE_INDEX = 8;
	private static final int BLACK_TEAM_PAWN_INDEX = 7;
	private static final int BLACK_TO_INDEX = 6;
	private static final int BLACK_FROM_INDEX = 3;
	private static final int WHITE_TEAM_PAWN_INDEX = 2;
	private static final int WHITE_TEAM_EXECUTIVE_INDEX = 1;
	private static final int BOARD_FROM_INDEX = 1;
	private static final int BOARD_TO_INDEX = 8;
	private static final int ROOK_FIRST_INDEX = 1;
	private static final int KNIGHT_FIRST_INDEX = 2;
	private static final int BISHOP_FIRST_INDEX = 3;
	private static final int QUEEN_INDEX = 4;
	private static final int KING_INDEX = 5;
	private static final int BISHOP_SECOND_INDEX = 6;
	private static final int KNIGHT_SECOND_INDEX = 7;
	private static final int ROOK_SECOND_INDEX = 8;
	private static final int DEFAULT_INDEX = 0;
	private static final String POSITION_FORMAT = "%c%d";

	public static ChessBoard createBoard() {
		List<Row> board = new ArrayList<>();
		board.addAll(createWhiteTeam());
		board.addAll(createBlankTeam());
		board.addAll(createBlackTeam());
		return new ChessBoard(1, board, new Turn(true));
	}

	private static List<Row> createWhiteTeam() {
		return Arrays.asList(
			createExecutive(WHITE_TEAM_EXECUTIVE_INDEX, Team.WHITE),
			createPawns(WHITE_TEAM_PAWN_INDEX, Team.WHITE));
	}

	private static List<Row> createBlackTeam() {
		return Arrays.asList(
			createPawns(BLACK_TEAM_PAWN_INDEX, Team.BLACK),
			createExecutive(BLACK_TEAM_EXECUTIVE_INDEX, Team.BLACK));
	}

	private static List<Row> createBlankTeam() {
		List<Row> rows = new ArrayList<>();
		for (int index = BLACK_FROM_INDEX; index <= BLACK_TO_INDEX; index++) {
			rows.add(createBlanks(index));
		}
		return rows;
	}

	private static Row createExecutive(int index, Team team) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		chessPieces.add(new Rook(Position.of(index, ROOK_FIRST_INDEX), team));
		chessPieces.add(new Knight(Position.of(index, KNIGHT_FIRST_INDEX), team));
		chessPieces.add(new Bishop(Position.of(index, BISHOP_FIRST_INDEX), team));
		chessPieces.add(new Queen(Position.of(index, QUEEN_INDEX), team));
		chessPieces.add(new King(Position.of(index, KING_INDEX), team));
		chessPieces.add(new Bishop(Position.of(index, BISHOP_SECOND_INDEX), team));
		chessPieces.add(new Knight(Position.of(index, KNIGHT_SECOND_INDEX), team));
		chessPieces.add(new Rook(Position.of(index, ROOK_SECOND_INDEX), team));
		return new Row(chessPieces);
	}

	private static Row createPawns(int index, Team team) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		for (int y = BOARD_FROM_INDEX; y <= BOARD_TO_INDEX; y++) {
			chessPieces.add(new Pawn(Position.of(index, y), team));
		}
		return new Row(chessPieces);
	}

	private static Row createBlanks(int index) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		for (int y = BOARD_FROM_INDEX; y <= BOARD_TO_INDEX; y++) {
			chessPieces.add(new Blank(Position.of(index, y)));
		}
		return new Row(chessPieces);
	}

	public static ChessBoard createBoard(ChessDTO chessDTO) {
		List<Row> rows = new ArrayList<>();
		int column = BOARD_TO_INDEX;
		for (String row : chessDTO.getSplitRows()) {
			rows.add(createRow(row, column--));
		}
		ChessBoard chessBoard = new ChessBoard(chessDTO.getId(), rows, new Turn(chessDTO.isWhiteTurn()));
		return chessBoard;
	}

	private static Row createRow(String row, int column) {
		List<ChessPiece> chessPieces = new ArrayList<>();
		int index = DEFAULT_INDEX;
		for (int y = 'a'; y <= 'h'; y++) {
			char name = row.charAt(index++);
			String position = String.format(POSITION_FORMAT, (char)y, column);
			ChessPiece chessPiece = PieceConverter.convert(name, position);
			chessPieces.add(chessPiece);
		}
		return new Row(chessPieces);
	}

}
