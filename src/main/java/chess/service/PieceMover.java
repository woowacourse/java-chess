package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Type;
import chess.service.dto.MoveDto;
import chess.service.dto.MoveResultDto;
import chess.service.dto.PieceDto;

import javax.sql.DataSource;

public class PieceMover {

    private ChessGameDao chessGameDao;
    private ChessPieceDao chessPieceDao;
    private MoveResultDto moveResultDto;

    public PieceMover() {
        DataSource ds = DBManager.createDataSource();
        chessGameDao = new ChessGameDao(ds);
        chessPieceDao = new ChessPieceDao(ds);
        moveResultDto = new MoveResultDto();
    }

    public MoveResultDto movePiece(MoveDto moveDto) {
        try {
            ChessGame chessGame = moveDto.getChessGame();
            Point source = moveDto.getSource();
            Point target = moveDto.getTarget();
            Piece sourcePiece = chessGame.getPiece(source);
            chessGame.play(source, target);
            PieceDto sourcePieceDto = new PieceDto(source, sourcePiece.getColor(), sourcePiece.getType());
            PieceDto targetPieceDto = new PieceDto(target, Color.NONE, Type.BLANK);
            chessPieceDao.updatePiece(sourcePieceDto, targetPieceDto);   // target 위치에 해당 체스 말 넣기
            chessPieceDao.updatePiece(targetPieceDto, sourcePieceDto);   // source 위치에 빈칸을 넣기
            chessGameDao.updateTurn(chessGame.getColor().toString());    // 현재 턴 데이터베이스에 저장
            moveResultDto.setSuccess(true);
            moveResultDto.setKingDead(chessGame.isEnd());
        } catch (Exception e) {
            moveResultDto.setSuccess(false);
        }
        return moveResultDto;
    }
}
