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

    ChessBoardDao chessBoardDao = ChessBoardDao.getInstance();
    ChessTurnDao chessTurnDao = ChessTurnDao.getInstance();

    private ChessGameService() {
    }

    public static ChessGameService getInstance() {
        if (instance == null) {
            instance = new ChessGameService();
        }
        return instance;
    }

    public ChessGameDto getGame(int gameId) throws SQLException {
        ChessBoardDto chessBoardDTO = chessBoardDao.selectChessBoard(gameId);
        PieceColor turn = chessTurnDao.selectChessTurn(gameId);

        ChessGameDto chessGameDTO = new ChessGameDto();
        chessGameDTO.setBoard(chessBoardDTO);
        chessGameDTO.setTurn(turn);

        return chessGameDTO;
    }

    public int getId(String idText) throws SQLException {
        int id;

        if (idText == null) {
            chessTurnDao.insertChessTurn(PieceColor.WHITE);
            id = chessTurnDao.selectMaxGameId();
            chessBoardDao.insertChessBoard(id, new ChessBoardDto(BoardInitializer.initialize()));
        } else {
            id = Integer.parseInt(idText);
        }

        return id;
    }

    public ChessGameDto move(int id, String from, String to) throws SQLException {
        ChessGameDto chessGameDto = getGame(id);

        ChessGame chessGame = new ChessGame(chessGameDto.getTurn(), chessGameDto.getBoard().getBoard());

        try {
            chessGame.move(from, to);
            ChessBoardDto chessBoardDTO = new ChessBoardDto(chessGame.getBoard());
            chessGameDto.setBoard(chessBoardDTO);
            chessGameDto.setTurn(chessGame.getTurn());

            chessTurnDao.updateChessTurn(id, chessGameDto.getTurn());
            chessBoardDao.deleteChessBoard(id);
            chessBoardDao.insertChessBoard(id, chessBoardDTO);
        } catch (GameOverException e) {
            chessTurnDao.deleteChessTurn(id);
            throw new GameOverException(chessGameDto.getTurn().toString());
        }
        return chessGameDto;
    }

    public ChessGameDto getStatus(int id) throws SQLException {
        ChessGameDto chessGameDTO = getGame(id);
        ChessGame chessGame = new ChessGame(chessGameDTO.getTurn(), chessGameDTO.getBoard().getBoard());
        chessGameDTO.setStatus(chessGame.status());
        return chessGameDTO;
    }
}
