package chess.service;

import chess.domain.ChessBoard;
import chess.domain.dao.ChessDao;
import chess.domain.dto.*;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private ChessBoard chessBoard;
    private final ChessDao chessDao;

    public ChessService() {
        chessBoard = ChessBoard.generate();
        this.chessDao = new ChessDao();
    }

//    public ChessRoomDto startChess() throws SQLException {
//        chessDao.deleteChessRoomByRoomNo(1);
//        chessBoard = ChessBoard.generate();
//        ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard(), chessBoard.isAliveAllKings());
//        ChessRoomDto chessRoomDto = new ChessRoomDto(
//                chessBoardDto.getChessBoard(), Color.WHITE.name(),
//                chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
//        chessDao.addChessRoom(chessRoomDto);
//        return chessDao.findChessRoomByRoomNo(1);
//    }

    public ChessRoomDto startChess() throws SQLException {
        chessDao.deleteChessRoomByRoomNo(1);
        chessBoard = ChessBoard.generate();
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
        chessDao.addChessRoom(chessBoardDto.getChessBoard(), Color.WHITE.name(),
                chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
        return chessDao.findChessRoomByRoomNo(1);
    }

    public List<PositionDto> showRoutes(String sourceValue) {
        Position source = Position.of(sourceValue.charAt(0), sourceValue.charAt(1));
        Piece sourcePiece = chessBoard.findByPosition(source);
        return chessBoard.routes(sourcePiece, source).stream()
                .map(position -> new PositionDto(String.valueOf(position.getX())+position.getY()))
                .collect(Collectors.toList());
    }

//    public void movePiece(MoveStatusDto moveStatusDto, Map<String, Object> model) throws SQLException {
//        Position source = Position.of(moveStatusDto.getSource().charAt(0), moveStatusDto.getSource().charAt(1));
//        Position target = Position.of(moveStatusDto.getTarget().charAt(0), moveStatusDto.getTarget().charAt(1));
//        chessBoard.movePiece(source, target);
//        MoveResultDto moveResultDto = new MoveResultDto(Color.from(moveStatusDto.getTurn()).reverse().name(), chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
//        Piece nowPiece = chessBoard.findByPosition(target);
//        chessDao.updateChessRoom(moveResultDto, new PieceDto(nowPiece.getName(), nowPiece.getColor().name()), new PositionDto(moveStatusDto.getSource()), new PositionDto(moveStatusDto.getTarget()));
//        ChessRoomDto chessRoomDto = chessDao.findChessRoomByRoomNo(1);
//
//        model.put("chessRoomInfo", chessRoomDto);
//        model.put("isAliveAllKings", chessBoard.isAliveAllKings());
//    }

    public ChessRoomDto movePiece(MoveStatusDto moveStatusDto, Map<String, Object> model) throws SQLException {
        Position source = Position.of(moveStatusDto.getSource().charAt(0), moveStatusDto.getSource().charAt(1));
        Position target = Position.of(moveStatusDto.getTarget().charAt(0), moveStatusDto.getTarget().charAt(1));
        chessBoard.movePiece(source, target);

        MoveResultDto moveResultDto = new MoveResultDto(Color.from(moveStatusDto.getTurn()).reverse().name(), chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
        Piece nowPiece = chessBoard.findByPosition(target);
        chessDao.updateChessRoom(moveResultDto, new PieceDto(nowPiece.getName(), nowPiece.getColor().name()), new PositionDto(moveStatusDto.getSource()), new PositionDto(moveStatusDto.getTarget()));
        return chessDao.findChessRoomByRoomNo(1);
    }

    public boolean isAliveAllKings() {
        return chessBoard.isAliveAllKings();
    }

    public ChessRoomDto loadChess(int roomNo) throws SQLException {
        ChessRoomDto chessRoomDto = chessDao.findChessRoomByRoomNo(roomNo);
        chessBoard.sync(chessRoomDto.getChessBoard());
        return chessRoomDto;
    }

    public int exitChess(int roomNo) throws SQLException {
        return chessDao.deleteChessRoomByRoomNo(roomNo);
    }
}
