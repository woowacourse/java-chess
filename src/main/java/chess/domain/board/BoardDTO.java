package chess.domain.board;

import chess.domain.piece.GamePiece;
import chess.domain.piece.GamePieceDTO;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class BoardDTO {

    private final List<GamePieceDTO> pieces;

    private BoardDTO(List<GamePieceDTO> pieces) {
        this.pieces = pieces;
    }

    public static BoardDTO create(Board board) {
        Map<Position, GamePiece> positionToPiece = board.getBoard();

        List<GamePieceDTO> gamePieceDTOs = positionToPiece.entrySet()
                .stream()
                .map(entry -> GamePieceDTO.create(entry.getValue(), entry.getKey()))
                .collect(toList());

        return new BoardDTO(gamePieceDTOs);
    }

    public List<GamePieceDTO> getPieces() {
        return pieces;
    }
}
