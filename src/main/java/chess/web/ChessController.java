package chess.web;

import chess.controller.dto.PieceDTO;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
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
        List<PieceDTO> pieces = new ArrayList<>();

        if (gameState.isRunning()) {

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
