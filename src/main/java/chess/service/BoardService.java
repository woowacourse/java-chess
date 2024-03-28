package chess.service;

import chess.dao.PieceDAO;
import chess.dao.TurnDAO;
import chess.db.ConnectionManager;
import chess.dto.PieceDTO;
import chess.dto.TurnDTO;
import chess.model.board.Board;
import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.Type;
import chess.model.position.Position;

import java.util.*;

public class BoardService {
    private final PieceDAO pieceDAO;
    private final TurnDAO turnDAO;

    public BoardService(ConnectionManager connectionManager) {
        this.pieceDAO = new PieceDAO(connectionManager);
        this.turnDAO = new TurnDAO(connectionManager);
    }

    public boolean isBoardExist() {
        TurnDTO turnDTO = turnDAO.findOne();
        return Objects.nonNull(turnDTO);
    }

    public Board getBoard() {
        TurnDTO turnDTO = turnDAO.findOne();
        List<PieceDTO> pieceDTOs = pieceDAO.findAll();
        return convertToBoard(turnDTO, pieceDTOs);
    }

    private Board convertToBoard(TurnDTO turnDTO, List<PieceDTO> pieceDTOs) {
        if (pieceDTOs.isEmpty()) {
            throw new IllegalStateException("조회할 수 있는 보드가 없습니다.");
        }
        Color color = Color.from(turnDTO.currentColor());
        Map<Position, Piece> piecePosition = convertToPiecePosition(pieceDTOs);
        return new Board(piecePosition, color);
    }

    private Map<Position, Piece> convertToPiecePosition(List<PieceDTO> pieceDTOs) {
        Map<Position, Piece> piecePosition = new HashMap<>();
        for (PieceDTO pieceDTO : pieceDTOs) {
            Position position = Position.of(pieceDTO.file(), pieceDTO.rank());
            Piece piece = Type.getPiece(pieceDTO.type());
            piecePosition.put(position, piece);
        }
        return piecePosition;
    }

    public void saveBoard(Board board) {
        deleteExistingBoard();
        if (board.getWinnerColor() != Color.NONE) {
            return;
        }
        saveTurn(board.getCurrentColor());
        savePieces(board.getSquares());
    }

    private void deleteExistingBoard() {
        turnDAO.deleteALl();
        pieceDAO.deleteAll();
    }

    private void saveTurn(Color color) {
        TurnDTO turnDTO = new TurnDTO(color.name());
        turnDAO.save(turnDTO);
    }

    private void savePieces(Map<Position, Piece> piecePosition) {
        List<PieceDTO> pieceDTOs = new ArrayList<>();
        for (Position position : piecePosition.keySet()) {
            Piece piece = piecePosition.get(position);
            PieceDTO pieceDTO = convertToPieceDTO(position, piece);
            pieceDTOs.add(pieceDTO);
        }
        pieceDAO.saveAll(pieceDTOs);
    }

    private static PieceDTO convertToPieceDTO(Position position, Piece piece) {
        int file = position.getFile();
        int rank = position.getRank();
        Type type = Type.from(piece);
        return new PieceDTO(file, rank, type.name());
    }
}
