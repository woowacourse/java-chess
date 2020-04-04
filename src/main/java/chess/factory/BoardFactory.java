package chess.factory;

import chess.dao.BoardDAO;
import chess.domain.board.Board;
import chess.domain.board.Row;
import chess.domain.chesspiece.*;
import chess.domain.game.GameStatus;
import chess.domain.game.Team;
import chess.domain.move.Coordinate;
import chess.domain.move.Position;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.domain.board.BoardInfo.BOARD_MAX_INDEX;
import static chess.domain.board.BoardInfo.BOARD_MIN_INDEX;
import static chess.domain.game.Team.BLACK;
import static chess.domain.game.Team.WHITE;

public class BoardFactory {
    private static final int BLANK_ROW_SIZE_BASE = 0;
    private static final int BLANK_ROW_SIZE = 4;

    private BoardFactory() {
    }

    public static Board createBoard() {
        List<Row> board = new ArrayList<>();
        GameStatus gameStatus = new GameStatus();

        addWhiteTeamRow(board);
        addBlankRows(board);
        addBlackTeamRow(board);
        return new Board(board, gameStatus);
    }

    public static Board createBlankBoard(GameStatus gameStatus) {
        List<Row> board = new ArrayList<>();
        for (int i = BOARD_MIN_INDEX; i <= BOARD_MAX_INDEX; i++) {
            addBlankRow(board);
        }
        return new Board(board, gameStatus);
    }

    public static Board createContinueBoard(BoardDAO boardDAO) throws SQLException {
        return boardDAO.loadBoard();
    }

    private static void addWhiteTeamRow(List<Row> board) {
        addMainPieces(board, WHITE);
        addPawn(board, WHITE);
    }

    private static void addBlankRows(List<Row> board) {
        for (int i = BLANK_ROW_SIZE_BASE; i < BLANK_ROW_SIZE; i++) {
            addBlankRow(board);
        }
    }

    private static void addBlackTeamRow(List<Row> board) {
        addPawn(board, BLACK);
        addMainPieces(board, BLACK);
    }

    private static void addMainPieces(List<Row> board, Team team) {
        List<ChessPiece> chessPieces = new ArrayList<>();

        chessPieces.add(new Rook(team));
        chessPieces.add(new Knight(team));
        chessPieces.add(new Bishop(team));
        chessPieces.add(new Queen(team));
        chessPieces.add(new King(team));
        chessPieces.add(new Bishop(team));
        chessPieces.add(new Knight(team));
        chessPieces.add(new Rook(team));
        board.add(Row.of(chessPieces));
    }

    private static void addPawn(List<Row> board, Team team) {
        List<ChessPiece> chessPieces = new ArrayList<>();

        for (int i = BOARD_MIN_INDEX; i <= BOARD_MAX_INDEX; i++) {
            chessPieces.add(new Pawn(team));
        }
        board.add(Row.of(chessPieces));
    }

    private static void addBlankRow(List<Row> board) {
        List<ChessPiece> chessPieces = new ArrayList<>();

        for (int i = BOARD_MIN_INDEX; i <= BOARD_MAX_INDEX; i++) {
            chessPieces.add(new Blank());
        }
        board.add(Row.of(chessPieces));
    }

    public static void createBoardIntoDB(BoardDAO boardDAO) throws SQLException {
        boardDAO.addToBoard(new Rook(BLACK), Position.of(Coordinate.of(8), Coordinate.of(1)));
        boardDAO.addToBoard(new Knight(BLACK), Position.of(Coordinate.of(8), Coordinate.of(2)));
        boardDAO.addToBoard(new Bishop(BLACK), Position.of(Coordinate.of(8), Coordinate.of(3)));
        boardDAO.addToBoard(new Queen(BLACK), Position.of(Coordinate.of(8), Coordinate.of(4)));
        boardDAO.addToBoard(new King(BLACK), Position.of(Coordinate.of(8), Coordinate.of(5)));
        boardDAO.addToBoard(new Bishop(BLACK), Position.of(Coordinate.of(8), Coordinate.of(6)));
        boardDAO.addToBoard(new Knight(BLACK), Position.of(Coordinate.of(8), Coordinate.of(7)));
        boardDAO.addToBoard(new Rook(BLACK), Position.of(Coordinate.of(8), Coordinate.of(8)));

        boardDAO.addToBoard(new Pawn(BLACK), Position.of(Coordinate.of(7), Coordinate.of(1)));
        boardDAO.addToBoard(new Pawn(BLACK), Position.of(Coordinate.of(7), Coordinate.of(2)));
        boardDAO.addToBoard(new Pawn(BLACK), Position.of(Coordinate.of(7), Coordinate.of(3)));
        boardDAO.addToBoard(new Pawn(BLACK), Position.of(Coordinate.of(7), Coordinate.of(4)));
        boardDAO.addToBoard(new Pawn(BLACK), Position.of(Coordinate.of(7), Coordinate.of(5)));
        boardDAO.addToBoard(new Pawn(BLACK), Position.of(Coordinate.of(7), Coordinate.of(6)));
        boardDAO.addToBoard(new Pawn(BLACK), Position.of(Coordinate.of(7), Coordinate.of(7)));
        boardDAO.addToBoard(new Pawn(BLACK), Position.of(Coordinate.of(7), Coordinate.of(8)));

        boardDAO.addToBoard(new Pawn(WHITE), Position.of(Coordinate.of(2), Coordinate.of(1)));
        boardDAO.addToBoard(new Pawn(WHITE), Position.of(Coordinate.of(2), Coordinate.of(2)));
        boardDAO.addToBoard(new Pawn(WHITE), Position.of(Coordinate.of(2), Coordinate.of(3)));
        boardDAO.addToBoard(new Pawn(WHITE), Position.of(Coordinate.of(2), Coordinate.of(4)));
        boardDAO.addToBoard(new Pawn(WHITE), Position.of(Coordinate.of(2), Coordinate.of(5)));
        boardDAO.addToBoard(new Pawn(WHITE), Position.of(Coordinate.of(2), Coordinate.of(6)));
        boardDAO.addToBoard(new Pawn(WHITE), Position.of(Coordinate.of(2), Coordinate.of(7)));
        boardDAO.addToBoard(new Pawn(WHITE), Position.of(Coordinate.of(2), Coordinate.of(8)));

        boardDAO.addToBoard(new Rook(WHITE), Position.of(Coordinate.of(1), Coordinate.of(1)));
        boardDAO.addToBoard(new Knight(WHITE), Position.of(Coordinate.of(1), Coordinate.of(2)));
        boardDAO.addToBoard(new Bishop(WHITE), Position.of(Coordinate.of(1), Coordinate.of(3)));
        boardDAO.addToBoard(new Queen(WHITE), Position.of(Coordinate.of(1), Coordinate.of(4)));
        boardDAO.addToBoard(new King(WHITE), Position.of(Coordinate.of(1), Coordinate.of(5)));
        boardDAO.addToBoard(new Bishop(WHITE), Position.of(Coordinate.of(1), Coordinate.of(6)));
        boardDAO.addToBoard(new Knight(WHITE), Position.of(Coordinate.of(1), Coordinate.of(7)));
        boardDAO.addToBoard(new Rook(WHITE), Position.of(Coordinate.of(1), Coordinate.of(8)));
    }
}
