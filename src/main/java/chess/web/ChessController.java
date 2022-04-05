package chess.web;

import chess.dto.PieceDTO;
import chess.service.ChessService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessController {
    private final ChessService chessService = new ChessService();

    public Map<String, Object> getBoard(String roomId) {
        HashMap<String, Object> model = new HashMap<>();
        Map<String, String> board = chessService.getInitialBoard(roomId);
        List<PieceDTO> pieces = board.entrySet()
                .stream()
                .map(i -> new PieceDTO(i.getKey(), i.getValue()))
                .collect(Collectors.toUnmodifiableList());
        model.put("chessPiece", pieces);
        return model;
    }

    public String move(String source, String destination, String roomId) {
        return chessService.move(source, destination, roomId);
    }

    public Map<String, Object> getStatus() {
        return chessService.getStatus();
    }

    public Map<String, Object> resetBoard(String roomId) {
        HashMap<String, Object> model = new HashMap<>();
        Map<String, String> board = chessService.resetBoard(roomId);
        List<PieceDTO> pieces = board.entrySet()
                .stream()
                .map(i -> new PieceDTO(i.getKey(), i.getValue()))
                .collect(Collectors.toUnmodifiableList());
        model.put("chessPiece", pieces);
        return model;
    }
}
