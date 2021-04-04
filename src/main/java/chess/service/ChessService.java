package chess.service;

import chess.domain.ChessBoard;
import chess.domain.dao.ChessDao;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.ChessRoomDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PositionDto;
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

    public ChessRoomDto startChess() throws SQLException {
        chessDao.deleteChessRoomByRoomNo(1);
        chessBoard = ChessBoard.generate();
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
        ChessRoomDto chessRoomDto = new ChessRoomDto(
                chessBoardDto.getChessBoard(), Color.WHITE.name(),
                chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
        chessDao.addChessRoom(chessRoomDto);
        return chessDao.findChessRoomByRoomNo(1);
    }

    public List<PositionDto> showRoutes(Map<String, String> map) {
        String sourceValue = map.get("source");
        Position source = Position.of(sourceValue.charAt(0), sourceValue.charAt(1));
        Piece sourcePiece = chessBoard.findByPosition(source);
        return chessBoard.routes(sourcePiece, source).stream()
                .map(position -> new PositionDto(String.valueOf(position.getX())+position.getY()))
                .collect(Collectors.toList());
    }

    public void movePiece(Map<String, String> map, Map<String, Object> model) throws SQLException {
        Position source = Position.of(map.get("source").charAt(0), map.get("source").charAt(1));
        Position target = Position.of(map.get("target").charAt(0), map.get("target").charAt(1));
        chessBoard.movePiece(source, target);
        ChessRoomDto chessRoomDto = new ChessRoomDto(Color.from(map.get("turn")).reverse().name(), chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
        Piece nowPiece = chessBoard.findByPosition(target);
        chessDao.updateChessRoom(chessRoomDto, new PieceDto(nowPiece.getName(), nowPiece.getColor().name()), new PositionDto(map.get("source")), new PositionDto(map.get("target")));
        chessRoomDto = chessDao.findChessRoomByRoomNo(1);
        model.put("chessRoomInfo", chessRoomDto);
        model.put("isAliveAllKings", chessBoard.isAliveAllKings());
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
