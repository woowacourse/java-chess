package chess.web;

import chess.controller.dto.PieceDTO;
import chess.dao.BoardDao;
import chess.database.factory.BoardFactory;
import chess.database.factory.RoomFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;

import java.util.*;

public class ChessController {
    private GameState gameState;

    public ChessController(Map<Position, Piece> board) {
        this.gameState = new WhiteTurn(board);
    }

    public Map<String, Object> getInitialBoard() {
        Map<String, Object> model = new HashMap<>();
        Map<Position, Piece> board = gameState.getBoard();
        List<PieceDTO> pieces = new ArrayList<>();

        if (Objects.requireNonNull(RoomFactory.findByPosition("1")).length() == 0) {
            List<BoardDao> boards = BoardFactory.findAll("1");
            for (BoardDao boardDao : boards) {
                pieces.add(new PieceDTO(boardDao.getPosition(), boardDao.getSymbol()));
            }
            model.put("chessPiece", pieces);
            return model;
        }

        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            if (!piece.isBlank()) {
                pieces.add(new PieceDTO(position.getPositionToString(), piece.getSymbol()));
            }
        }
        model.put("chessPiece", pieces);
        return model;
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
