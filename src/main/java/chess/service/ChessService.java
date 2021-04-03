package chess.service;

import chess.dao.BoardDAO;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.dto.WebBoardDTO;
import chess.dto.WebPiecesDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessService {

    public Map<String, Integer> start(String whitePlayer, String blackPlayer) throws SQLException {
        Map<String, Integer> boardInfo = new HashMap<>();
        Board board = new Board(whitePlayer, blackPlayer);
        board.init();
        BoardDAO boardDAO = BoardDAO.instance();
        int boardId = boardDAO.addBoard(new WebBoardDTO(board), new WebPiecesDTO(board.pieces()));
        boardInfo.put("boardId", boardId);
        return boardInfo;
    }

    private Map<String, String> PositionToStringMap(Map<Position, Piece> pieces, Board board) {
        Map<String, String> boardInfo = new HashMap<>();
        for (Position position : pieces.keySet()) {
            boardInfo.put(position.positionToString(), getSymbol(pieces.get(position)));
        }
        boardInfo.put("whitePlayer", board.players().getWhitePlayer());
        boardInfo.put("blackPlayer", board.players().getBlackPlayer());
        boardInfo.put("isFinish", String.valueOf(board.isFinish()));
        boardInfo.put("turn", board.turn().color());
        return boardInfo;
    }

    private String getSymbol(Piece piece) {
        if (Objects.isNull(piece)) {
            return ".";
        }
        return piece.symbol();
    }

    public Map<String, String> movedPiece(int boardId ,String source, String target)
        throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        Board board = boardDAO.findBoardByBoardId(boardId);
        board.movePiece(source, target);
        WebBoardDTO webBoardDTO = new WebBoardDTO(board, boardId);
        WebPiecesDTO webPiecesDTO = new WebPiecesDTO(board.pieces(), source, target);
        boardDAO.updateBoard(webBoardDTO, webPiecesDTO);
        Map<Position, Piece> pieces = board.pieces();
        return PositionToStringMap(pieces, board);
    }

    public List<String> movablePositions(int boardId, String source) throws SQLException {
        List<String> positions = new ArrayList<>();
        BoardDAO boardDAO = BoardDAO.instance();
        Board board = boardDAO.findBoardByBoardId(boardId);
        for (Position position : board.movablePositions(source)) {
            positions.add(position.positionToString());
        }
        return positions;
    }

    public Map<String, String> joinBoard(int boardId) throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        Board board = boardDAO.findBoardByBoardId(boardId);
        Map<Position, Piece> pieces = board.pieces();
        return PositionToStringMap(pieces, board);
    }

    public List<Map<String, Object>> searchBoard(String playerName) throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        List<WebBoardDTO> webBoardDTOS = boardDAO.findBoardsByPlayerName(playerName);
        List<Map<String, Object>> boards = webBoardDTOSToBoards(webBoardDTOS);
        return boards;
    }

    private List<Map<String, Object>> webBoardDTOSToBoards(List<WebBoardDTO> webBoardDTOS) {
        List<Map<String, Object>> boards = new ArrayList<>();
        for (WebBoardDTO webBoardDTO : webBoardDTOS) {
            Map<String, Object> board = new HashMap<>();
            board.put("boardId", webBoardDTO.getBoardId());
            board.put("whitePlayer", webBoardDTO.getWhitePlayer());
            board.put("blackPlayer", webBoardDTO.getBlackPlayer());
            board.put("isFinish", webBoardDTO.getIsFinish());
            board.put("turnIsWhite", webBoardDTO.getTurnIsWhite());
            boards.add(board);
        }
        return boards;
    }
}
