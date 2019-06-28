package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.PointFactory;
import chess.domain.pieces.Type;
import chess.service.dto.MoveResultDto;
import chess.service.dto.PieceDto;
import spark.Request;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PieceMover {

    private ChessGameDao chessGameDao;
    private ChessPieceDao chessPieceDao;

    public PieceMover() {
        DataSource ds = DBManager.createDataSource();
        chessGameDao = new ChessGameDao(ds);
        chessPieceDao = new ChessPieceDao(ds);
    }

    public MoveResultDto movePiece(Request request) throws SQLException {
        MoveResultDto moveResultDto = new MoveResultDto(false, false);
        ChessGame chessGame = request.session().attribute("chessGame");
        Point source = PointFactory.of(request.queryMap("source").value());
        Point target = PointFactory.of(request.queryMap("target").value());
        chessGame.play(source, target);
        updateDB(chessGame, source, target);
        moveResultDto.setSuccess(true);
        moveResultDto.setKingDead(chessGame.isEnd());
        return moveResultDto;
    }

    private void updateDB(ChessGame chessGame, Point source, Point target) throws SQLException {
        Piece sourcePiece = chessGame.getPiece(source);
        PieceDto sourcePieceDto = new PieceDto(source, sourcePiece.getColor(), sourcePiece.getType());
        PieceDto targetPieceDto = new PieceDto(target, Color.NONE, Type.BLANK);
        chessPieceDao.updatePiece(sourcePieceDto, targetPieceDto);   // target 위치에 해당 체스 말 넣기
        chessPieceDao.updatePiece(targetPieceDto, sourcePieceDto);   // source 위치에 빈칸을 넣기
        chessGameDao.updateTurn(chessGame.getColor().toString());    // 현재 턴 데이터베이스에 저장
    }
}
