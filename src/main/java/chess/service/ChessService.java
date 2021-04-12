package chess.service;

import chess.dao.ChessLogDao;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.dto.BoardDto;
import chess.dto.BoardStatusDto;
import chess.dto.MoveRequestDto;

import java.sql.SQLException;
import java.util.List;

public class ChessService {
    private static final String END_TRUE = "true";

    private final ChessLogDao chessLogDao;

    public ChessService() {
        this.chessLogDao = new ChessLogDao();
    }

    public BoardDto loadRoom(ChessGame chessGame, String roomNumber) throws SQLException {
        List<String> commands = chessLogDao.applyCommand(roomNumber);
        return start(chessGame, commands);
    }

    public BoardDto movePiece(ChessGame chessGame, MoveRequestDto moveRequestDto) throws SQLException {
        chessLogDao.addLog(moveRequestDto.getRoomId(), moveRequestDto.getTarget(), moveRequestDto.getDestination());
        return movePiece(chessGame, moveRequestDto.getTarget(), moveRequestDto.getDestination());
    }

    private BoardDto start(ChessGame chessGame, List<String> commands) {
        chessGame.settingBoard();

        for (String command : commands) {
            chessGame.move(command);
        }

        Board board = chessGame.getBoard();
        return new BoardDto(board, chessGame.turn());
    }

    private BoardDto movePiece(ChessGame chessGame, String target, String destination) {
        chessGame.move(target, destination);
        if (chessGame.isBeforeEnd()) {
            return new BoardDto(chessGame.getBoard(), chessGame.turn());
        }
        return new BoardDto(chessGame.getBoard(), chessGame.turn().name(), END_TRUE);
    }

    public List<String> movablePosition(ChessGame chessGame, String target) {
        return chessGame.findMovablePosition(target);
    }

    public BoardDto boardDto(ChessGame chessGame) {
        return new BoardDto(chessGame.getBoard(), chessGame.turn());
    }

    public BoardStatusDto boardStatusDto(ChessGame chessGame) {
        return new BoardStatusDto(chessGame.boardStatus());
    }

    public void deleteRoom(String roomNumber) throws SQLException {
        chessLogDao.deleteLog(roomNumber);
    }
}
