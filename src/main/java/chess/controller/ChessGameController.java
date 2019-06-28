package chess.controller;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessGame;
import chess.domain.MoveResult;
import chess.domain.Point;
import chess.domain.pieces.*;
import chess.dto.ChessBoardDto;
import chess.dto.PieceDto;
import com.google.gson.Gson;
import spark.Route;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGameController {

    private static ChessGame chessGame;
    private static boolean isNewGame;

    public static Route newGame = (request, response) -> {
        response.redirect("/chessgame.html");
        isNewGame = true;
        return null;
    };

    public static Route continueGame = (request, response) -> {
        response.redirect("/chessgame.html");
        isNewGame = false;
        return null;
    };

    public static Route initialize = (request, response) -> {
        response.type("application/json");
        try {
            return new Gson().toJson(isNewGame ? initializeNewGame() : initializeContinue());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(e.getMessage());
        }
    };

    private static Map<String, String> initializeNewGame() {
        chessGame = new ChessGame();
        ChessGameDao chessGameDao = new ChessGameDao(DBManager.createDataSource());
        ChessPieceDao chessPieceDao = new ChessPieceDao(DBManager.createDataSource());
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessGame.getBoard());

        try {
            chessGameDao.deleteGame();
            for (int i = 1; i <= 64; ++i) {
                chessPieceDao.deletePieceById(String.valueOf(i));
            }
            chessGameDao.addGame();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 데이터베이스에 초기화 배열 저장
        chessBoardDto.getPoints().forEach((key, value) -> {
            PieceDto pieceDto = new PieceDto(key);
            pieceDto.setColor(value.getColor().toString());
            pieceDto.setType(value.getType().toString());
            try {
                chessPieceDao.addPiece(pieceDto);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // 프론트에 보드 정보 주기
        Map<String, String> initBoard = new HashMap<>();
        chessBoardDto.getPoints().entrySet().stream()
                .filter(point -> !point.getValue().equalsType(Type.BLANK))
                .forEach(point -> initBoard.put(point.getKey().toString(), point.getValue().toString()));
        return initBoard;
    }

    private static Map<String, String> initializeContinue() throws SQLException {
        ChessGameDao chessGameDao = new ChessGameDao(DBManager.createDataSource());
        ChessPieceDao chessPieceDao = new ChessPieceDao(DBManager.createDataSource());
        Map<String, String> initBoard = new HashMap<>();
        Map<Point, Piece> gameBoard = new HashMap<>();
        Color currentTurn = "WHITE".equals(chessGameDao.findTurn()) ? Color.WHITE : Color.BLACK;

        for (int i = 1; i <= 64; ++i) {
            PieceDto piece = chessPieceDao.findPieceById(String.valueOf(i));
            String color = piece.getColor().substring(0, 1).toLowerCase();
            String type = "KNIGHT".equals(piece.getType())
                    ? piece.getType().substring(1, 2)
                    : piece.getType().substring(0, 1);
            gameBoard.put(PointFactory.of(piece.getPoint()), PieceFactory.of(color + type));
            if ("BLANK".equals(piece.getType())) {
                continue;
            }
            initBoard.put(piece.getPoint(), color + type);
        }
        chessGame = new ChessGame(currentTurn, gameBoard);

        return initBoard;
    }

    public static Route move = (request, response) -> {
        response.type("application/json");
        MoveResult moveResult = new MoveResult();
        try {
            Point source = PointFactory.of(request.queryMap("source").value());
            Point target = PointFactory.of(request.queryMap("target").value());
            moveResult.setSuccess(movePiece(source, target));
            moveResult.setKingDead(chessGame.isEnd());
        } catch (Exception e) {
            moveResult.setSuccess(false);
            response.status(500);
        }
        return new Gson().toJson(moveResult);
    };

    public static boolean movePiece(Point source, Point target) {
        try {
            Piece sourcePiece = chessGame.getPiece(source);
            chessGame.play(source, target);
            PieceDto sourcePieceDto = new PieceDto(source, sourcePiece);
            PieceDto targetPieceDto = new PieceDto(target, Color.NONE, Type.BLANK);
            ChessPieceDao chessPieceDao = new ChessPieceDao(DBManager.createDataSource());
            chessPieceDao.updatePiece(sourcePieceDto, targetPieceDto);  // target 위치에 해당 체스 말 넣기
            chessPieceDao.updatePiece(targetPieceDto, sourcePieceDto);  // source 위치에 빈칸을 넣기
            // 현재 턴 데이터베이스에 저장
            ChessGameDao chessGameDao = new ChessGameDao(DBManager.createDataSource());
            chessGameDao.updateTurn(chessGame.getColor().toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
