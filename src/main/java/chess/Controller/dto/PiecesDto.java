package chess.Controller.dto;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class PiecesDto {

    private final List<PieceDto> pieces;
    private final String gameStatus;

    private PiecesDto(final Board board) {
        this(board.getPieces(), board.getGameState().toString().toLowerCase(Locale.ROOT));
    }

    private PiecesDto(final Map<Position, Piece> piecesStatus, final String gameStatus) {
        this.pieces = piecesStatus.entrySet()
                .stream()
                .map(entry -> PieceDto.fromEntity(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        this.gameStatus = gameStatus;
    }

    public static PiecesDto fromEntity(final Board board) {
        return new PiecesDto(board);
    }

    public static PiecesDto fromEntity(final Map<Position, Piece> pieces) {
        return new PiecesDto(pieces, "READY");
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
