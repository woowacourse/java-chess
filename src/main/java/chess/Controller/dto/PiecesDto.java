package chess.Controller.dto;

import chess.domain.GameState;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PiecesDto {

    private final List<PieceDto> pieces;
    private final String gameStatus;

    private PiecesDto(final Map<Position, Piece> piecesStatus, final GameState gameStatus) {
        this.pieces = piecesStatus.entrySet()
                .stream()
                .map(entry -> PieceDto.fromEntity(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        this.gameStatus = gameStatus.toString();
    }

    public static PiecesDto fromEntity(final Board board) {
        return new PiecesDto(board.getPieces(), board.getGameState());
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
