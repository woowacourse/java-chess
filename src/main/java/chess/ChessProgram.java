package chess;

import chess.dao.BoardDao;
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

public class ChessProgram {

    private static final String GAME_NUMBER = "1";
    private static final String SUCCEED_CODE = "200";
    private static final String GAME_SET_CODE = "300";
    private static final String FAIL_CODE = "400";
    private static final String WHITE = "W";
    private static final String BLACK = "B";
    private static final String BLANK = ".";

    private static BoardDao boardDAO;

    public void initChessGame() {
        boardDAO = new BoardDao();
        boardDAO.initBoardTable();
        boardDAO.addBoard(Board.getGamingBoard(), Side.WHITE.name());
    }

    public ResponseDTO move(PositionDTO positionDTO) throws SQLException {
        Board board = new Board(boardDAO.findBoard(GAME_NUMBER));
        try {
            return moveExecute(positionDTO, board);
        } catch (ChessException e) {
            return new ResponseDTO(FAIL_CODE, e.getMessage(), currentTurn().name());
        }
    }

    private ResponseDTO moveExecute(PositionDTO positionDTO, Board board) {
        board.move(Position.from(positionDTO.from()), Position.from(positionDTO.to()), currentTurn());
        boardDAO.updateBoard(board, currentTurn().changeTurn().name());
        if (board.isGameSet()) {
            Side side = board.winner();
            return new ResponseDTO(GAME_SET_CODE, side.name(), "게임 종료(" + side.name() + " 승리)");
        }
        return new ResponseDTO(SUCCEED_CODE, "Succeed", currentTurn().name());
    }

    public Map<String, String> getCurrentBoard() {
        Map<Position, Piece> board = boardDAO.findBoard(GAME_NUMBER);
        Map<String, String> boardName = new LinkedHashMap<>();

        for (Position position : board.keySet()) {
            String positionName = position.positionName();
            boardName.put(positionName, pieceToName(board.get(position)));
        }
        return boardName;
    }

    private String pieceToName(Piece piece) {
        if (piece.side() == Side.WHITE) {
            return WHITE + piece.getInitial().toUpperCase();
        }
        if (piece.side() == Side.BLACK) {
            return BLACK + piece.getInitial().toUpperCase();
        }
        return BLANK;
    }

    public String turnName() {
        return currentTurn().name();
    }

    private Side currentTurn() {
        return boardDAO.findTurn(GAME_NUMBER);
    }
}
