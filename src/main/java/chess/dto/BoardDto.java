package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.CustomBoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
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

    public static BoardDto of(final Integer gameId, final Board board) {
        return new BoardDto(gameId, createBoard(board.getBoard()));
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
        if (!board.get(position).isSame(Type.EMPTY)) {
            strings.put(position.getName(), new PieceDto(board.get(position)));
        }
    }

    public Board toBoard() {
        final Map<Position, Piece> pieces = new HashMap<>();
        for (String position : board.keySet()) {
            pieces.put(Position.from(position), board.get(position).toPiece());
        }

        return new Board(new CustomBoardFactory(pieces));
    }

    public Integer getGameId() {
        return gameId;
    }

    public Map<String, PieceDto> getBoard() {
        return board;
    }
}
