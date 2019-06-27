package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.PieceFactory;
import chess.domain.pieces.PointFactory;
import chess.service.dto.ChessBoardDto;
import chess.service.dto.PieceDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ContinueGameInitializer {
    private static final int START_PIECE_ID = 1;
    private static final int END_PIECE_ID = 64;
    private static final int START_FIRST_CHAR = 0;
    private static final int END_FIRST_CHAR = 1;
    private static final int START_SECOND_CHAR = 1;
    private static final int END_SECOND_CHAR = 2;
    private static final int GAME_ID = 1;

    private ChessGameDao chessGameDao;
    private ChessPieceDao chessPieceDao;
    private ChessBoardDto chessBoardDto;

    public ContinueGameInitializer() {
        DataSource ds = DBManager.createDataSource();
        chessGameDao = new ChessGameDao(ds);
        chessPieceDao = new ChessPieceDao(ds);
        chessBoardDto = new ChessBoardDto();
    }

    public ChessBoardDto initialize() throws SQLException {
        Color currentTurn = chessGameDao.findTurn(GAME_ID).equals("WHITE") ? Color.WHITE : Color.BLACK;
        Map<String, String> initBoard = makeJSONPreviousBoard();
        Map<Point, Piece> gameBoard = makePreviousBoard();

        chessBoardDto.setCurrentOfTurn(currentTurn);
        chessBoardDto.setInitWebBoard(initBoard);
        chessBoardDto.setGameBoard(gameBoard);

        return chessBoardDto;
    }

    private Map<String, String> makeJSONPreviousBoard() throws SQLException {
        Map<String, String> initBoard = new HashMap<>();
        for (int i = START_PIECE_ID; i <= END_PIECE_ID; ++i) {
            PieceDto piece = chessPieceDao.findPieceById(String.valueOf(i));
            String color = piece.getColor().substring(START_FIRST_CHAR, END_FIRST_CHAR).toLowerCase();
            String type = piece.getType().equals("KNIGHT")
                    ? piece.getType().substring(START_SECOND_CHAR, END_SECOND_CHAR)
                    : piece.getType().substring(START_FIRST_CHAR, END_FIRST_CHAR);
            if (piece.getType().equals("BLANK")) {
                continue;
            }
            initBoard.put(piece.getPoint(), color + type);
        }
        return initBoard;
    }

    private Map<Point, Piece> makePreviousBoard() throws SQLException {
        Map<Point, Piece> gameBoard = new HashMap<>();
        for (int i = START_PIECE_ID; i <= END_PIECE_ID; ++i) {
            PieceDto piece = chessPieceDao.findPieceById(String.valueOf(i));
            String color = piece.getColor().substring(START_FIRST_CHAR, END_FIRST_CHAR).toLowerCase();
            String type = piece.getType().equals("KNIGHT")
                    ? piece.getType().substring(START_SECOND_CHAR, END_SECOND_CHAR)
                    : piece.getType().substring(START_FIRST_CHAR, END_FIRST_CHAR);
            gameBoard.put(PointFactory.of(piece.getPoint()), PieceFactory.of(color + type));
        }
        return gameBoard;
    }
}
