package chess.service;

import java.sql.SQLException;

import chess.dao.ChessGameDao;
import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.game.exception.InvalidTurnException;
import chess.domain.game.state.Playing;
import chess.domain.piece.Position;
import chess.domain.piece.exception.NotMovableException;
import chess.dto.BoardDto;
import chess.dto.ChessGameDto;
import chess.dto.ResponseDto;
import chess.dto.StatusDto;
import chess.dto.TurnDto;

public class ChessService {
    private static final ChessGameDao chessGameDao = new ChessGameDao();

    public ResponseDto createChessRoom() throws SQLException {
        ChessGame chessGame = chessGameDao.save();
        if (chessGame == null) {
            return new ResponseDto(ResponseDto.FAIL, "새로운 방을 만드는데 실패했습니다.");
        }
        chessGame.start();
        chessGameDao.update(chessGame);
        return new ResponseDto(ResponseDto.SUCCESS, chessGame.getId());
    }

    public ResponseDto restartGame(int chessRoomId) throws SQLException {
        ChessGame chessGame = chessGameDao.findById(chessRoomId);
        if (chessGame == null) {
            return null;
        }
        ChessGame newChessGame = new ChessGame(chessGame.getId(), new Playing(Board.create(), Turn.WHITE));
        chessGameDao.update(newChessGame);
        return new ResponseDto(ResponseDto.SUCCESS, chessGame.getId());
    }

    public ResponseDto movePiece(int pieceId, Position sourcePosition, Position targetPosition) throws SQLException {
        ChessGame chessGame = chessGameDao.findById(pieceId);
        try {
            chessGame.move(sourcePosition, targetPosition);
            chessGameDao.update(chessGame);
        } catch (NotMovableException | IllegalArgumentException e) {
            return new ResponseDto(ResponseDto.FAIL, "이동할 수 없는 위치입니다.");
        } catch (InvalidTurnException e) {
            return new ResponseDto(ResponseDto.FAIL, chessGame.turn().getColor() + "의 턴입니다.");
        }
        return responseChessGame(chessGame);
    }

    public ResponseDto getChessGameById(int chessGameId) throws SQLException {
        ChessGame chessGame = chessGameDao.findById(chessGameId);
        return responseChessGame(chessGame);
    }

    public ResponseDto getGameList() throws SQLException {
        return new ResponseDto(ResponseDto.SUCCESS, chessGameDao.selectAll());
    }

    private static ResponseDto responseChessGame(ChessGame chessGame) {
        return new ResponseDto(ResponseDto.SUCCESS,
                new ChessGameDto(new BoardDto(chessGame.board()), new TurnDto(chessGame.turn()),
                    new StatusDto(chessGame.status().getWhiteScore(), chessGame.status().getBlackScore(),
                        chessGame.status().getWinner()), chessGame.isFinished()));
    }
}
