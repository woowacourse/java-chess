package chess.service;

import chess.dao.BoardDAO;
import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.PositionDTO;
import chess.dto.ResponseDTO;
import chess.exception.ChessException;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessService {

    private static final String GameNumber = "1";
    private static BoardDAO boardDAO;

    public void initChessGame() throws SQLException {
        boardDAO = new BoardDAO();
        boardDAO.initBoardTable();
        boardDAO.addBoard(Board.getGamingBoard(), Side.WHITE.name());
    }

    public ResponseDTO move(PositionDTO positionDTO) throws SQLException {
        Board board = new Board(boardDAO.findBoard(GameNumber));
        try {
            return moveExecute(positionDTO, board);
        } catch (ChessException e) {
            return new ResponseDTO("400", e.getMessage(), currentTurn().name());
        }
    }

    private ResponseDTO moveExecute(PositionDTO positionDTO, Board board) throws SQLException {
        board.move(Position.from(positionDTO.from()), Position.from(positionDTO.to()), currentTurn());
        boardDAO.updateBoard(board, currentTurn().changeTurn().name());
        if (board.isGameSet()) {
            Side side = board.winner();
            return new ResponseDTO("300", side.name(), "게임 종료(" + side.name() + " 승리)");
        }
        return new ResponseDTO("200", "Succeed", currentTurn().name());
    }

    public Map<String, String> getCurrentBoard() throws SQLException {
        Map<Position, Piece> board = boardDAO.findBoard(GameNumber);
        Map<String, String> boardName = new LinkedHashMap<>();

        for (Position position : board.keySet()) {
            String positionName = position.positionName();
            boardName.put(positionName, pieceToName(board.get(position)));
        }
        return boardName;
    }

    private String pieceToName(Piece piece) {
        if (piece.side() == Side.WHITE) {
            return "W" + piece.getInitial().toUpperCase();
        }
        if (piece.side() == Side.BLACK) {
            return "B" + piece.getInitial().toUpperCase();
        }
        return ".";
    }

    public String turnName() throws SQLException {
        return currentTurn().name();
    }

    private Side currentTurn() throws SQLException {
        return boardDAO.findTurn(GameNumber);
    }
}
