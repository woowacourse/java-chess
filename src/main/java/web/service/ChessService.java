package web.service;

import chess.domain.board.ChessBoard;
import chess.domain.command.Command;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.ChessBoardPosition;
import chess.domain.state.Running;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import web.dao.ChessGameDao;
import web.dao.ChessGameDaoImpl;
import web.dao.PieceDao;
import web.dao.PieceDaoImpl;
import web.dto.ChessGameResponse;
import web.dto.MoveRequest;
import web.dto.MoveResponse;

public class ChessService {

    private static final PieceDao PIECE_DAO = new PieceDaoImpl();
    private static final ChessGameDao CHESS_GAME_DAO = new ChessGameDaoImpl();

    public Long createChessGame(String gameName) throws SQLException {
        ChessGame chessGame = ChessGame.initChessGame();
        chessGame.setName(gameName);
        Long gameId = CHESS_GAME_DAO.createGame(chessGame);
        ChessBoard chessBoard = chessGame.chessBoard();
        initPieces(chessBoard.getPieces(), gameId);
        CHESS_GAME_DAO.updateTurn(gameId, chessGame.currentTurn().name());

        return gameId;
    }

    private void initPieces(Collection<Piece> pieces, Long gameId) throws SQLException {
        for (Piece piece : pieces) {
            String position = piece.getPosition().toString();
            PIECE_DAO.savePiece(position, piece, gameId);
        }
    }

    public ChessGame loadChessGame(String gameId) throws SQLException {
        ChessGameResponse chessGameResponse = CHESS_GAME_DAO.findByGameId(gameId);
        Map<Position, Piece> pieces = PIECE_DAO.findPieces(chessGameResponse.getId());
        return new ChessGame(
            new Running(new ChessBoard(pieces), Color.from(chessGameResponse.getTurn())));
    }

    public MoveResponse move(MoveRequest moveReqeust) throws SQLException {
        ChessGameResponse gameResponse = CHESS_GAME_DAO.findByGameId(
            String.valueOf(moveReqeust.getId()));
        ChessBoard chessBoard = new ChessBoard(PIECE_DAO.findPieces(moveReqeust.getId()));
        ChessGame chessGame = new ChessGame(
            new Running(chessBoard, Color.from(gameResponse.getTurn())));

        try {
            chessGame.execute(Command.move(moveReqeust.from(), moveReqeust.to()));
            checkPossibleToDeletePiece(moveReqeust.to(), chessBoard, moveReqeust.getId());
            updatePiece(moveReqeust, chessGame);
            CHESS_GAME_DAO.updateTurn(moveReqeust.getId(), chessGame.currentTurn().name());
            return checkFinished(chessGame, moveReqeust.getId());
        } catch (Exception e) {
            return new MoveResponse("400", e.getMessage(), chessGame.currentTurn().name());
        }
    }

    private void checkPossibleToDeletePiece(String to, ChessBoard chessBoard, Long id)
        throws SQLException {
        if (chessBoard.getBoard().get(ChessBoardPosition.from(to)) != null) {
            PIECE_DAO.deletePiece(to, id);
        }
    }

    private void updatePiece(MoveRequest moveReqeust, ChessGame chessGame) throws SQLException {
        ChessBoard chessBoard = chessGame.chessBoard();
        Map<Position, Piece> board = chessBoard.getBoard();

        PIECE_DAO.savePiece(moveReqeust.to(), board.get(ChessBoardPosition.from(moveReqeust.to())),
            moveReqeust.getId());
        PIECE_DAO.deletePiece(moveReqeust.from(), moveReqeust.getId());
    }

    private MoveResponse checkFinished(ChessGame chessGame, Long id) throws SQLException {
        if (chessGame.isGameEnd()) {
            CHESS_GAME_DAO.updateGameEnd(id);
            return new MoveResponse("300", "게임 종료", chessGame.winner().name());
        }
        return new MoveResponse("200", "성공", chessGame.currentTurn().name());
    }

    public Score score(String gameId) throws SQLException {
        Map<Position, Piece> pieces = PIECE_DAO.findPieces(Long.parseLong(gameId));
        return Score.from(new ChessBoard(pieces));
    }

    public String turn(String gameId) throws SQLException {
        return CHESS_GAME_DAO.findTurn(gameId);
    }
}
