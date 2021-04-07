package chess.service;

import chess.dao.AbstractDAO;
import chess.dao.BoardDAO;
import chess.dao.PiecesDAO;
import chess.domain.board.Board;
import chess.domain.board.Pieces;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.dto.MovablePositionDTO;
import chess.dto.MovePieceDTO;
import chess.dto.WebBoardDTO;
import chess.dto.WebSimpleBoardDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessService {

    public int createBoard(WebSimpleBoardDTO webSimpleBoardDTO) throws SQLException {
        Board board = new Board(webSimpleBoardDTO.getWhitePlayer(),
            webSimpleBoardDTO.getBlackPlayer());
        board.init();
        webSimpleBoardDTO = new WebSimpleBoardDTO(board);
        BoardDAO boardDAO = BoardDAO.instance();
        Connection connection = boardDAO.connection();
        connection.setAutoCommit(false);
        try {
            int boardId = boardDAO.addBoard(webSimpleBoardDTO, connection);
            PiecesDAO.instance().addPieces(boardId, board.pieces(), connection);
            connection.commit();
            return boardId;
        } catch (Exception e) {
            connection.rollback();
            throw new SQLException(e.getMessage());
        } finally {
            boardDAO.closeConnection(connection);
        }
    }

    private Map<String, String> PositionToStringMap(Map<Position, Piece> pieces) {
        Map<String, String> boardInfo = new HashMap<>();
        for (Position position : pieces.keySet()) {
            boardInfo.put(position.positionToString(), getSymbol(pieces.get(position)));
        }
        return boardInfo;
    }

    private String getSymbol(Piece piece) {
        if (Objects.isNull(piece)) {
            return ".";
        }
        return piece.symbol();
    }

    public WebBoardDTO movedPiece(MovePieceDTO movePieceDTO) throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        Board board = boardDAO.findBoardByBoardId(movePieceDTO.getBoardId());
        board.movePiece(movePieceDTO.getSource(), movePieceDTO.getTarget());

        boardDAO.updateBoard(board, movePieceDTO);
        Map<String, String> pieces = PositionToStringMap(board.pieces());
        WebBoardDTO webBoardDTO = new WebBoardDTO(board, movePieceDTO.getBoardId());
        webBoardDTO.setPieces(pieces);

        return webBoardDTO;
    }

    public MovablePositionDTO movablePositions(MovablePositionDTO movablePositionDTO) throws SQLException {
        List<String> positions = new ArrayList<>();
        BoardDAO boardDAO = BoardDAO.instance();
        Board board = boardDAO.findBoardByBoardId(movablePositionDTO.getBoardId());
        for (Position position : board.movablePositions(movablePositionDTO.getSource())) {
            positions.add(position.positionToString());
        }
        movablePositionDTO.setMovablePositions(positions);
        return movablePositionDTO;
    }

    public WebBoardDTO joinBoard(WebSimpleBoardDTO webSimpleBoardDTO) throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        Board board = boardDAO.findBoardByBoardId(webSimpleBoardDTO.getBoardId());
        Map<String, String> pieces = PositionToStringMap(board.pieces());
        WebBoardDTO webBoardDTO = new WebBoardDTO(board, webSimpleBoardDTO.getBoardId());
        webBoardDTO.setPieces(pieces);
        return webBoardDTO;
    }

    public List<WebSimpleBoardDTO> searchBoard(String playerName) throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        return boardDAO.findBoardsByPlayerName(playerName);
    }
}
