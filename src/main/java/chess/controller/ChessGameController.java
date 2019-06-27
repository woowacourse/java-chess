package chess.controller;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.PointFactory;
import chess.domain.pieces.Type;
import chess.service.ContinueGameInitializer;
import chess.service.NewGameInitializer;
import chess.service.PieceMover;
import chess.service.dto.ChessBoardDto;
import chess.service.dto.MoveDto;
import chess.service.dto.MoveResultDto;
import chess.service.dto.PieceDto;
import com.google.gson.Gson;
import spark.Route;

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
            ChessBoardDto chessBoardDto;
            if (isNewGame) {
                chessGame = new ChessGame();
                NewGameInitializer newGameInitializer = new NewGameInitializer();
                chessBoardDto = newGameInitializer.initialize(chessGame);
                return new Gson().toJson(chessBoardDto.getInitWebBoard());
            }

            ContinueGameInitializer continueGameInitializer = new ContinueGameInitializer();
            chessBoardDto = continueGameInitializer.initialize();
            chessGame = new ChessGame(chessBoardDto.getCurrentOfTurn(), chessBoardDto.getGameBoard());
            return new Gson().toJson(chessBoardDto.getInitWebBoard());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(e.getMessage());
        }
    };

    public static Route move = (request, response) -> {
        response.type("application/json");
        MoveResultDto moveResultDto = new MoveResultDto();
        try {
            MoveDto moveDto = new MoveDto();
            moveDto.setSource(PointFactory.of(request.queryMap("source").value()));
            moveDto.setTarget(PointFactory.of(request.queryMap("target").value()));
            moveDto.setChessGame(chessGame);

            PieceMover pieceMover = new PieceMover();
            moveResultDto = pieceMover.movePiece(moveDto);
        } catch (Exception e) {
            moveResultDto.setSuccess(false);
            response.status(500);
        }
        return new Gson().toJson(moveResultDto);
    };

    public static boolean movePiece(Point source, Point target) {
        try {
            Piece sourcePiece = chessGame.getPiece(source);
            chessGame.play(source, target);
            PieceDto sourcePieceDto = new PieceDto(source, sourcePiece.getColor(), sourcePiece.getType());
            PieceDto targetPieceDto = new PieceDto(target, Color.NONE, Type.BLANK);
            ChessPieceDao chessPieceDao = new ChessPieceDao(DBManager.createDataSource());
            chessPieceDao.updatePiece(sourcePieceDto, targetPieceDto);                    // target 위치에 해당 체스 말 넣기
            chessPieceDao.updatePiece(targetPieceDto, sourcePieceDto);                    // source 위치에 빈칸을 넣기
            ChessGameDao chessGameDao = new ChessGameDao(DBManager.createDataSource());   // 현재 턴 데이터베이스에 저장
            chessGameDao.updateTurn(chessGame.getColor().toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
