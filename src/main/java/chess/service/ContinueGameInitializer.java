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
        Color currentTurn = chessGameDao.findTurn().equals("WHITE") ? Color.WHITE : Color.BLACK;
        Map<String, String> initBoard = makeJSONPreviousBoard();
        Map<Point, Piece> gameBoard = makePreviousBoard();

        chessBoardDto.setCurrentOfTurn(currentTurn);
        chessBoardDto.setInitWebBoard(initBoard);
        chessBoardDto.setGameBoard(gameBoard);

        return chessBoardDto;
    }

    private Map<String, String> makeJSONPreviousBoard() throws SQLException {
        Map<String, String> initBoard = new HashMap<>();
        for (int i = 1; i <= 64; ++i) {
            PieceDto piece = chessPieceDao.findPieceById(String.valueOf(i));
            String color = piece.getColor().substring(0, 1).toLowerCase();
            String type = piece.getType().equals("KNIGHT")
                    ? piece.getType().substring(1, 2)
                    : piece.getType().substring(0, 1);
            if (piece.getType().equals("BLANK")) {
                continue;
            }
            initBoard.put(piece.getPoint(), color + type);
        }
        return initBoard;
    }

    private Map<Point, Piece> makePreviousBoard() throws SQLException {
        Map<Point, Piece> gameBoard = new HashMap<>();
        for (int i = 1; i <= 64; ++i) {
            PieceDto piece = chessPieceDao.findPieceById(String.valueOf(i));
            String color = piece.getColor().substring(0, 1).toLowerCase();
            String type = piece.getType().equals("KNIGHT")
                    ? piece.getType().substring(1, 2)
                    : piece.getType().substring(0, 1);
            gameBoard.put(PointFactory.of(piece.getPoint()), PieceFactory.of(color + type));
        }
        return gameBoard;
    }
}
