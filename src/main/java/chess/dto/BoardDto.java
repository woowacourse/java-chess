package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardDto {

    private final Integer gameId;
    private final Map<String, PieceDto> board;

    public BoardDto(final Integer gameId, final Map<String, PieceDto> board) {
        this.gameId = gameId;
        this.board = board;
    }

    public static BoardDto of(final Integer gameId, final Map<Position, Piece> board) {
        return new BoardDto(gameId, createBoard(board));
    }

    private static Map<String, PieceDto> createBoard(final Map<Position, Piece> board) {
        final Map<String, PieceDto> strings = new HashMap<>();
        for (Position position : board.keySet()) {
            putPiece(strings, board, position);
        }
        return strings;
    }

    private static void putPiece(final Map<String, PieceDto> strings, final Map<Position, Piece> board,
                                 final Position position) {
        if (!board.get(position).isEmpty()) {
            strings.put(position.getName(), new PieceDto(board.get(position)));
        }
    }

    public Integer getGameId() {
        return gameId;
    }

    public Map<String, PieceDto> getBoard() {
        return board;
    }
}
