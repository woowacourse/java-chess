package chess.web.dto;

import chess.GameManager;
import chess.domain.board.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChessDto {

    private final Map<PositionDto, PieceDto> board;
    private final boolean isEnd;

    public ChessDto(Map<PositionDto, PieceDto> board, boolean isEnd) {
        this.board = board;
        this.isEnd = isEnd;
    }


    public static ChessDto from(GameManager gameManager) {
        Map<Position, Piece> board = gameManager.getBoard();
        Map<PositionDto, PieceDto> result = new HashMap<>();
        Set<Position> positions = board.keySet();
        for (Position position : positions) {
            Piece piece = board.get(position);
            result.put(PositionDto.from(position), PieceDto.from(piece));
        }
        return new ChessDto(result, gameManager.isFinished());
    }

    public Map<PositionDto, PieceDto> getBoard() {
        return board;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
