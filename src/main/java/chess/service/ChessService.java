package chess.service;

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
        return addDatabaseBoard(webSimpleBoardDTO, board);
    }

    private int addDatabaseBoard(WebSimpleBoardDTO webSimpleBoardDTO, Board board) throws SQLException {
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

    public WebBoardDTO movedPiece(MovePieceDTO movePieceDTO) throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        Pieces pieces = PiecesDAO.instance().joinPieces(movePieceDTO.getBoardId());
        Board board = boardDAO.findBoardByBoardId(movePieceDTO.getBoardId(), pieces);
        board.movePiece(movePieceDTO.getSource(), movePieceDTO.getTarget());

        updateDatabaseBoard(movePieceDTO, board);

        Map<String, String> piecesData = changedPositionToStringMap(board.pieces());
        WebBoardDTO webBoardDTO = new WebBoardDTO(board, movePieceDTO.getBoardId());
        webBoardDTO.setPieces(piecesData);
        return webBoardDTO;
    }

    private void updateDatabaseBoard(MovePieceDTO movePieceDTO, Board board) throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        Connection connection = boardDAO.connection();
        try {
            BoardDAO.instance().updateBoard(board, movePieceDTO, connection);
            PiecesDAO.instance().updatePiece(board, movePieceDTO, connection);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            boardDAO.closeConnection(connection);
        }
    }

    public MovablePositionDTO movablePositions(MovablePositionDTO movablePositionDTO)
        throws SQLException {
        List<String> positions = new ArrayList<>();
        Pieces pieces = PiecesDAO.instance().joinPieces(movablePositionDTO.getBoardId());
        Board board = BoardDAO.instance().findBoardByBoardId(movablePositionDTO.getBoardId(), pieces);
        for (Position position : board.movablePositions(movablePositionDTO.getSource())) {
            positions.add(position.changedPositionToString());
        }
        movablePositionDTO.setMovablePositions(positions);
        return movablePositionDTO;
    }

    public WebBoardDTO joinBoard(WebSimpleBoardDTO webSimpleBoardDTO) throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        Pieces pieces = PiecesDAO.instance().joinPieces(webSimpleBoardDTO.getBoardId());
        Board board = boardDAO.findBoardByBoardId(webSimpleBoardDTO.getBoardId(), pieces);
        Map<String, String> piecesData = changedPositionToStringMap(board.pieces());
        WebBoardDTO webBoardDTO = new WebBoardDTO(board, webSimpleBoardDTO.getBoardId());
        webBoardDTO.setPieces(piecesData);
        return webBoardDTO;
    }

    public List<WebSimpleBoardDTO> searchBoard(String playerName) throws SQLException {
        BoardDAO boardDAO = BoardDAO.instance();
        return boardDAO.findBoardsByPlayerName(playerName);
    }

    private Map<String, String> changedPositionToStringMap(Map<Position, Piece> pieces) {
        Map<String, String> piecesData = new HashMap<>();
        for (Position position : pieces.keySet()) {
            piecesData.put(position.changedPositionToString(), getSymbol(pieces.get(position)));
        }
        return piecesData;
    }

    private String getSymbol(Piece piece) {
        if (Objects.isNull(piece)) {
            return ".";
        }
        return piece.symbol();
    }
}
