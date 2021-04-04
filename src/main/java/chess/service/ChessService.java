package chess.service;

import chess.dao.BoardDAO;
import chess.dto.PositionDTO;
import chess.dto.ResponseDTO;
import chess.domain.board.Board;
import chess.domain.Side;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
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
        String from = positionDTO.from();
        String to = positionDTO.to();
        Board board = new Board(boardDAO.findBoard(GameNumber));
        try {
            board.move(Position.from(from), Position.from(to), currentTurn());
            boardDAO.updateBoard(board, currentTurn().changeTurn().name());
            if (board.isGameSet()) {
                Side side = board.winner();
                return new ResponseDTO("300", side.name(), "게임 종료(" + side.name() + " 승리)");
            }
            return new ResponseDTO("200", "Succeed", currentTurn().name());
        } catch (ChessException e) {
            return new ResponseDTO("400", e.getMessage(), currentTurn().name());
        }
    }

    public Map<String, String> getCurrentBoard() throws SQLException {
        Map<Position, Piece> board = boardDAO.findBoard(GameNumber);
        Map<String, String> boardDTO = new LinkedHashMap<>();

        for (Position position : board.keySet()) {
            String positionDTO = position.stringPosition();
            String pieceDTO;
            Piece piece = board.get(position);
            if (piece.side() == Side.BLACK) {
                pieceDTO = "B" + piece.getInitial();
            } else if (piece.side() == Side.WHITE) {
                pieceDTO = "W" + piece.getInitial().toUpperCase();
            } else {
                pieceDTO = ".";
            }
            boardDTO.put(positionDTO, pieceDTO);
        }
        return boardDTO;
    }

    public String turnName() throws SQLException {
        return currentTurn().name();
    }

    private Side currentTurn() throws SQLException {
        return boardDAO.findTurn(GameNumber);
    }
}
