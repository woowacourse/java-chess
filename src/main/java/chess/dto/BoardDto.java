package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardDto {

    private final Long gameId;
    private final Map<String, PieceDto> board;

    public BoardDto(final Long gameId, final Map<String, PieceDto> board) {
        this.gameId = gameId;
        this.board = board;
    }

    public static BoardDto of(Long gameId, ChessBoard chessBoard) {
        return new BoardDto(gameId, createBoard(chessBoard.getBoard()));
    }

    private static Map<String, PieceDto> createBoard(Map<Position, Piece> board) {
        final Map<String, PieceDto> pieces = new HashMap<>();
        for (Position position : board.keySet()) {
            pieces.put(position.getName(), new PieceDto(board.get(position)));
        }
        return pieces;
    }

    public Long getGameId() {
        return gameId;
    }

    public Map<String, PieceDto> getBoard() {
        return board;
    }
}
