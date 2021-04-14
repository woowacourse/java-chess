package chess.service;

import chess.dao.ChessLogDao;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.dto.BoardDto;
import chess.dto.BoardStatusDto;
import chess.dto.MovablePositionDto;
import chess.dto.MoveRequestDto;

import java.sql.SQLException;
import java.util.List;

public class ChessService {
    private static final String END_TRUE = "true";

    private final ChessLogDao chessLogDao;

    public ChessService() {
        this.chessLogDao = new ChessLogDao();
    }

    public BoardDto loadRoom(String roomNumber) {
        return start(loadChessGame(roomNumber));
    }

    private ChessGame loadChessGame(String roomNumber) {
        List<String> commands = chessLogDao.applyCommand(roomNumber);
        ChessGame chessGame = new ChessGame();
        chessGame.settingBoard();

        for (String command : commands) {
            chessGame.move(command);
        }

        return chessGame;
    }

    public BoardDto move(MoveRequestDto moveRequestDto) throws SQLException {
        ChessGame chessGame = loadChessGame(moveRequestDto.getRoomId());

        try {
            BoardDto boardDto = movePiece(chessGame, moveRequestDto);
            chessLogDao.addLog(moveRequestDto.getRoomId(), moveRequestDto.getTarget(), moveRequestDto.getDestination());
            return boardDto;
        }
        catch (Exception e) {
            return start(chessGame);
        }
    }

    private BoardDto start(ChessGame chessGame) {
        Board board = chessGame.getBoard();
        return new BoardDto(board, chessGame.turn());
    }

    private BoardDto movePiece(ChessGame chessGame, MoveRequestDto moveRequestDto) {
        chessGame.move(moveRequestDto.getTarget(), moveRequestDto.getDestination());
        if (chessGame.isBeforeEnd()) {
            return new BoardDto(chessGame.getBoard(), chessGame.turn());
        }
        return new BoardDto(chessGame.getBoard(), chessGame.turn().name(), END_TRUE);
    }

    public List<String> movablePosition(MovablePositionDto movablePositionDto) {
        return loadChessGame(movablePositionDto.getRoomId()).findMovablePosition(movablePositionDto.getTarget());
    }

    public BoardStatusDto boardStatusDto(String roomId) {
        return new BoardStatusDto(loadChessGame(roomId).boardStatus());
    }

    public void deleteRoom(String roomNumber) {
        chessLogDao.deleteLog(roomNumber);
    }
}
