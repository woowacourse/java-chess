package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Type;
import chess.service.dto.MoveInfoDto;
import chess.service.dto.MoveResultDto;
import chess.service.dto.PieceDto;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PieceMoveService {

    public MoveResultDto movePiece(MoveInfoDto moveInfoDto) throws SQLException {
        MoveResultDto moveResultDto = new MoveResultDto(false, false);
        ChessGame chessGame = moveInfoDto.getChessGame();
        Point source = moveInfoDto.getSource();
        Point target = moveInfoDto.getTarget();
        Piece sourcePiece = chessGame.getPiece(source);
        chessGame.play(source, target);
        updateDB(moveInfoDto, sourcePiece);
        moveResultDto.setSuccess(true);
        moveResultDto.setKingDead(chessGame.isEnd());
        return moveResultDto;
    }

    private void updateDB(MoveInfoDto moveInfoDto, Piece sourcePiece) throws SQLException {
        DataSource ds = DBManager.createDataSource();
        ChessGameDao chessGameDao = new ChessGameDao(ds);
        ChessPieceDao chessPieceDao = new ChessPieceDao(ds);
        PieceDto sourcePieceDto = new PieceDto(moveInfoDto.getSource(), sourcePiece.getColor(), sourcePiece.getType());
        PieceDto targetPieceDto = new PieceDto(moveInfoDto.getTarget(), Color.NONE, Type.BLANK);
        chessPieceDao.updatePiece(sourcePieceDto, targetPieceDto);   // target 위치에 해당 체스 말 넣기
        chessPieceDao.updatePiece(targetPieceDto, sourcePieceDto);   // source 위치에 빈칸을 넣기
        chessGameDao.updateTurn(moveInfoDto.getChessGame().getColor().toString());    // 현재 턴 데이터베이스에 저장
    }
}
