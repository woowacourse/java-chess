package chess.web;

import chess.controller.dto.PieceDTO;
import chess.dao.BoardDao;
import chess.database.factory.BoardFactory;
import chess.database.factory.RoomFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.BoardInitialize;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessController {
    private GameState gameState;

    public ChessController(Map<Position, Piece> board) {
        this.gameState = new WhiteTurn(board);
    }

    public Map<String, Object> getInitialBoard() {
        Map<String, Object> model = new HashMap<>();
        Map<Position, Piece> board = gameState.getBoard();
        List<PieceDTO> pieces = getPieces("1");
        model.put("chessPiece", pieces);
        return model;
    }

    public List<PieceDTO> getInitialBoard(String roomId) {
        if (RoomFactory.existRoom(roomId)) {
            return getPieces(roomId);
        }
        return createPieces();
    }

    private List<PieceDTO> createPieces() {
        List<PieceDTO> pieces = new ArrayList<>();
        Map<Position, Piece> board = BoardInitialize.create();
        BoardFactory.saveAll(board);
        for (Position piecePosition : board.keySet()) {
            Piece piece = board.get(piecePosition);
            Position position = piece.getPosition();
            pieces.add(new PieceDTO(position.getPositionToString(), piece.getSymbol()));
        }
        return pieces;
    }

    private List<PieceDTO> getPieces(String roomId) {
        List<BoardDao> boards = BoardFactory.findAll(roomId);
        List<PieceDTO> pieces = new ArrayList<>();
        for (BoardDao boardDao : boards) {
            pieces.add(new PieceDTO(boardDao.getPosition(), boardDao.getSymbol()));
        }
        return pieces;
    }

    public Map<String, Object> move(String source, String destination) {
        Map<String, Object> model = new HashMap<>();
        try {
            gameState.move(source, destination);
            model.put("status", "무브 성공");
            return model;
        } catch (IllegalArgumentException exception) {
            model.put("status", exception.getMessage());
            return model;
        }
    }
}
