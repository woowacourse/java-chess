package chess.service;

import chess.domain.ChessBoard;
import chess.domain.ChessBoards;
import chess.domain.dao.ChessDao;
import chess.domain.dto.*;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ChessService {
    private final ChessDao chessDao;

    public ChessService() {
        this.chessDao = new ChessDao();
    }

    public ChessRoomDto startChess(ChessBoards chessBoards) throws SQLException {
        chessDao.deleteChessRoomByRoomNo(1);
        chessBoards.putChessBoard(1, ChessBoard.generate());
        ChessBoard chessBoard = chessBoards.findChessBoardByRoomNo(1);
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
        chessDao.addChessRoom(chessBoardDto.getChessBoard(), Color.WHITE.name(),
                chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
        return chessDao.findChessRoomByRoomNo(1);
    }

    public List<PositionDto> showRoutes(String sourceValue, ChessBoards chessBoards) {
        Position source = Position.of(sourceValue.charAt(0), sourceValue.charAt(1));
        ChessBoard chessBoard = chessBoards.findChessBoardByRoomNo(1);
        Piece sourcePiece = chessBoard.findByPosition(source);
        return chessBoard.routes(sourcePiece, source).stream()
                .map(position -> new PositionDto(String.valueOf(position.getX()) + position.getY()))
                .collect(Collectors.toList());
    }

    public ChessRoomDto movePiece(MoveStatusDto moveStatusDto, ChessBoards chessBoards) throws SQLException {
        Position source = Position.of(moveStatusDto.getSource().charAt(0), moveStatusDto.getSource().charAt(1));
        Position target = Position.of(moveStatusDto.getTarget().charAt(0), moveStatusDto.getTarget().charAt(1));
        ChessBoard chessBoard = chessBoards.findChessBoardByRoomNo(1);
        chessBoard.movePiece(source, target);

        MoveResultDto moveResultDto = new MoveResultDto(Color.from(moveStatusDto.getTurn()).reverse().name(), chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
        Piece nowPiece = chessBoard.findByPosition(target);
        chessDao.updateChessRoom(moveResultDto, new PieceDto(nowPiece.getName(), nowPiece.getColor().name()), new PositionDto(moveStatusDto.getSource()), new PositionDto(moveStatusDto.getTarget()));
        return chessDao.findChessRoomByRoomNo(1);
    }

    public boolean isAliveAllKings(ChessBoards chessBoards) {
        ChessBoard chessBoard = chessBoards.findChessBoardByRoomNo(1);
        return chessBoard.isAliveAllKings();
    }

    public ChessRoomDto loadChess(int roomNo, ChessBoards chessBoards) throws SQLException {
        ChessRoomDto chessRoomDto = chessDao.findChessRoomByRoomNo(roomNo);
        ChessBoard chessBoard = chessBoards.findChessBoardByRoomNo(roomNo);
        chessBoard.sync(chessRoomDto.getChessBoard());
        chessBoards.putChessBoard(roomNo, chessBoard);
        return chessRoomDto;
    }

    public void exitChess(int roomNo) throws SQLException {
        chessDao.deleteChessRoomByRoomNo(roomNo);
    }
}
