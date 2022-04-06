package chess.dto;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public final class BoardDto {

    final Map<String, PieceDto> positionsAndPieces = new HashMap<>();

    public BoardDto(final ChessGame chessGame) {
        final var board = chessGame.getBoard();
        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            positionsAndPieces.put(convertPosition(entry.getKey()), new PieceDto(entry.getValue()));
        }
    }

    private String convertPosition(final Position position) {
        return position.getFile().getName() + position.getRankNumber();
    }
}
