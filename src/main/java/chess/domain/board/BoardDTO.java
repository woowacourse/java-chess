package chess.domain.board;

import chess.domain.piece.GamePiece;
import chess.domain.piece.GamePieceDTO;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class BoardDTO {

    private final List<GamePieceDTO> pieces;
    private final StatusDTO status;

    private BoardDTO(List<GamePieceDTO> pieces, StatusDTO status) {
        this.pieces = pieces;
        this.status = status;
    }

    public static BoardDTO create(Board board) {
        Map<Position, GamePiece> positionToPiece = board.getBoard();

        List<GamePieceDTO> gamePieceDTOs = positionToPiece.entrySet()
                .stream()
                .map(entry -> GamePieceDTO.create(entry.getValue(), entry.getKey()))
                .collect(toList());

        StatusDTO status = StatusDTO.create(board.getStatus());

        return new BoardDTO(gamePieceDTOs, status);
    }

    public List<GamePieceDTO> getPieces() {
        return pieces;
    }

    public StatusDTO getStatus() {
        return status;
    }
}
