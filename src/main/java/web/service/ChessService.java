package web.service;

import chess.domain.board.ChessBoard;
import chess.domain.command.Command;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.ChessBoardPosition;
import chess.domain.state.Ready;
import chess.domain.piece.Symbol;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import web.dao.PieceDao;
import web.dao.PieceDaoImpl;
import web.dto.MoveReqeust;
import web.dto.MoveResponse;

public class ChessService {

    private final PieceDao pieceDao;
    private ChessGame chessGame;

    public ChessService() {
        this.pieceDao = new PieceDaoImpl();
        this.chessGame = new ChessGame(new Ready(ChessBoard.createChessBoard()));
    }

    public void init() throws SQLException {
        if (pieceDao.load() == null) {
            pieceDao.removeAll();
            saveChessBoard();
        }
        List<Piece> pieces = pieceDao.load();
        String color = pieceDao.findTurn();
        chessGame = new ChessGame(new Ready(new ChessBoard(pieces), Color.from(color)));
        chessGame.start();
    }

    private void saveChessBoard() throws SQLException {
        ChessBoard chessBoard = ChessBoard.createChessBoard();
        initPieces(chessBoard.getPieces());
        pieceDao.initTurn();
    }

    private void initPieces(Collection<Piece> pieces) throws SQLException {
        for (Piece piece : pieces) {
            String position = piece.getPosition().toString();
            pieceDao.savePiece(position, piece);
        }
    }

    public Map<String, String> currentBoard() throws SQLException {
        Map<Position, Piece> board = pieceDao.findPieces();
        Map<String, String> result = new LinkedHashMap<>();

        for (Position position : board.keySet()) {
            String positionName = position.toString();
            String pieceName = Symbol.makePieceName(position, board);
            result.put(positionName, pieceName);
        }
        return result;
    }

    public MoveResponse move(MoveReqeust moveReqeust) throws SQLException {
        String from = moveReqeust.from();
        String to = moveReqeust.to();
        try {
            ChessBoard chessBoard = chessGame.chessBoard();
            checkPossibleToDeletePiece(to, chessBoard);
            chessGame.execute(Command.move(from, to));
            updatePiece(from, to);
            changeTurn();
            return checkFinished(chessGame);
        } catch (Exception e) {
            return new MoveResponse("400", e.getMessage(), chessGame.currentTurn().name());
        }
    }

    private void checkPossibleToDeletePiece(String to, ChessBoard chessBoard) throws SQLException {
        if (chessBoard.getBoard().get(ChessBoardPosition.from(to)) != null) {
            pieceDao.deletePiece(to);
        }
    }

    private void updatePiece(String from, String to) throws SQLException {
        ChessBoard chessBoard = chessGame.chessBoard();
        Map<Position, Piece> board = chessBoard.getBoard();

        pieceDao.savePiece(to, board.get(ChessBoardPosition.from(to)));
        pieceDao.deletePiece(from);
    }

    private void changeTurn() throws SQLException {
        pieceDao.updateTurn(chessGame.currentTurn());
    }

    private MoveResponse checkFinished(ChessGame chessGame) {
        if (chessGame.isGameEnd()) {
            return new MoveResponse("300", "게임 종료", chessGame.winner().name());
        }
        return new MoveResponse("200", "성공", chessGame.currentTurn().name());
    }

    public Score score() {
        return Score.from(chessGame.chessBoard());
    }

    public String turn() throws SQLException {
        return pieceDao.findTurn();
    }

    public void initChessBoard() throws SQLException {
        chessGame = new ChessGame(new Ready(ChessBoard.createChessBoard()));
        chessGame.start();
        pieceDao.removeAll();
        saveChessBoard();
    }
}
