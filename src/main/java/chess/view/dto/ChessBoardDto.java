package chess.view.dto;

import chess.domain.piece.Piece;
import chess.domain.player.Round;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class ChessBoardDto {
    private final Map<Position, Piece> chessBoard;

    public ChessBoardDto(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoardDto from(final Round round) {
        return new ChessBoardDto(round.getBoard());
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
