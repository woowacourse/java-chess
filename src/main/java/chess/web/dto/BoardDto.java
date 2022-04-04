package chess.web.dto;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;

public class BoardDto {
    private Map<String, PieceDto> board;

    public BoardDto(Map<String, PieceDto> board) {
        this.board = board;
    }

    private static Map<String, PieceDto> convertNewBoard(Map<Position, Piece> board) {
        Map<String, PieceDto> newBoard = new HashMap<>();
        for (Position position : board.keySet()) {
            String positionStr = position.getFile().name() + position.getRank().getValue();
            newBoard.put(positionStr, PieceDto.of(positionStr, board.get(position)));
        }
        return newBoard;
    }

    public Map<String, PieceDto> getBoard() {
        return board;
    }

    public void setBoard(Map<Position, Piece> board) {
        this.board = convertNewBoard(board);
    }

    public PieceDto get(String position) {
        return board.get(position);
    }
}
