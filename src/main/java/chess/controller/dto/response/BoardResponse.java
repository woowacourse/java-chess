package chess.controller.dto.response;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardResponse {

    private final List<PieceResponse> pieces;

    public BoardResponse(Map<Position, Piece> board) {
        this.pieces = createPieceResponses(board);
    }

    private static List<PieceResponse> createPieceResponses(Map<Position, Piece> board) {
        return board.entrySet()
                .stream()
                .map(entry -> new PieceResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
