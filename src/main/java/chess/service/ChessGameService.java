package chess.service;

import chess.dao.ChessBoardDao;
import chess.dao.ChessTurnDao;
import chess.domain.ChessGame;
import chess.domain.board.BoardInitializer;
import chess.domain.board.GameOverException;
import chess.domain.piece.PieceColor;
import chess.dto.ChessBoardDto;
import chess.dto.ChessGameDto;

import java.sql.SQLException;

public class ChessGameService {
    private static ChessGameService instance;

    private ChessGameService() {
    }

    public static ChessGameService getInstance() {
        if (instance == null) {
            instance = new ChessGameService();
        }
        return instance;
    }

    public ChessGameDto getGame(int gameId) throws SQLException {
        ChessBoardDto chessBoardDTO = ChessBoardDao.getInstance().selectChessBoard(gameId);
        PieceColor turn = ChessTurnDao.getInstance().selectChessTurn(gameId);

        ChessGameDto chessGameDTO = new ChessGameDto();
        chessGameDTO.setBoard(chessBoardDTO);
        chessGameDTO.setTurn(turn);

        return chessGameDTO;
    }

    public int getId(String idText) throws SQLException {
        int id;

        if (idText == null) {
            ChessTurnDao.getInstance().insertChessTurn(PieceColor.WHITE);
            id = ChessTurnDao.getInstance().selectMaxGameId();
            ChessBoardDao.getInstance().insertChessBoard(id, new ChessBoardDto(BoardInitializer.initialize()));
        } else {
            id = Integer.parseInt(idText);
        }

        return id;
    }

    public ChessGameDto move(int id, String from, String to) throws SQLException {
        ChessGameDto chessGameDTO = getGame(id);

        ChessGame chessGame = new ChessGame(chessGameDTO.getTurn(), chessGameDTO.getBoard().getBoard());

        try {
            chessGame.move(from, to);
            ChessBoardDto chessBoardDTO = new ChessBoardDto(chessGame.getBoard());
            chessGameDTO.setBoard(chessBoardDTO);
            chessGameDTO.setTurn(chessGame.getTurn());

            ChessTurnDao.getInstance().updateChessTurn(id, chessGameDTO.getTurn());
            ChessBoardDao.getInstance().deleteChessBoard(id);
            ChessBoardDao.getInstance().insertChessBoard(id, chessBoardDTO);
        } catch (GameOverException e) {
            ChessTurnDao.getInstance().deleteChessTurn(id);
            throw new GameOverException(chessGameDTO.getTurn().toString());
        }
        return chessGameDTO;
    }

    public ChessGameDto getStatus(int id) throws SQLException {
        ChessGameDto chessGameDTO = getGame(id);
        ChessGame chessGame = new ChessGame(chessGameDTO.getTurn(), chessGameDTO.getBoard().getBoard());
        chessGameDTO.setStatus(chessGame.status());
        return chessGameDTO;
    }
}
