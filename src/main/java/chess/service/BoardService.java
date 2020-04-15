package chess.service;

import chess.domain.board.Board;
import chess.domain.dao.BoardDAO;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.result.GameResult;
import chess.exception.TakeTurnException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardService {
    private BoardDAO boardDAO;

    public BoardService() {
        boardDAO = new BoardDAO();
    }

    public Map<String, Object> receiveEmptyBoard() {
        Board board = BoardFactory.createBoard();
        return createBoardModel(board);
    }

    public Map<String, Object> receiveInitializedBoard() throws SQLException {
        Board board = BoardFactory.createBoard();
        board.initialize();

        boardDAO.deletePieces();
        for (Position position : board.getBoard().keySet()) {
            boardDAO.placePiece(board, position);
        }


        Map<String, Object> model = createBoardModel(board);
        model.put("turn", "WHITE 가 먼저 시작합니다.");
        return model;
    }

    public Map<String, Object> receiveLoadedBoard(TurnService turnService) throws SQLException {
        Board board = new Board(boardDAO.findAllPieces());
        Map<String, Object> model = createBoardModel(board);
        model.put("turn", turnService.getCurrentTurn() + "차례 입니다.");
        return model;
    }

    public Map<String, Object> receiveMovedBoard(final String fromPiece, final String toPiece, final TurnService turnService, final FinishService finishService) throws SQLException {
        Board board = new Board(boardDAO.findAllPieces());
        Piece piece = board.findBy(Position.of(fromPiece));
        if (!piece.isSameTeam(turnService.getCurrentTurn())) {
            throw new TakeTurnException("체스 게임 순서를 지켜주세요.");
        }
        board.move(fromPiece, toPiece);
        finishService.updateFinish(board.isFinished());

        boardDAO.deletePieces();
        for (Position position : board.getBoard().keySet()) {
            boardDAO.placePiece(board, position);
        }

        Map<String, Object> model = createBoardModel(board);
        turnService.updateTurn();
        model.put("turn", turnService.getCurrentTurn() + "차례입니다.");
        return model;
    }

    private Map<String, Object> createBoardModel(final Board board) {
        Map<String, Object> model = new HashMap<>();
        Map<Position, Piece> rawBoard = board.getBoard();
        for (Position position : rawBoard.keySet()) {
            model.put(position.toString(), rawBoard.get(position).getName());
        }
        return model;
    }

    public boolean isFinished() throws SQLException {
        Board board = new Board(boardDAO.findAllPieces());
        return board.isFinished();
    }

    public Map<String, Object> receiveScoreStatus() throws SQLException {
        GameResult gameResult = new GameResult();
        Board board = new Board(boardDAO.findAllPieces());

        double blackScore = gameResult.calculateScore(board, Team.BLACK);
        double whiteScore = gameResult.calculateScore(board, Team.WHITE);

        Map<String, Object> model = createBoardModel(board);
        model.put("black", blackScore);
        model.put("white", whiteScore);
        return model;
    }
}
