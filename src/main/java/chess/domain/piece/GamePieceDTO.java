package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.PositionDTO;

public class GamePieceDTO {

    private final String name;
    private final PositionDTO position;

    public GamePieceDTO(GamePiece gamePiece, Position position) {
        this.name = gamePiece.getName();
        this.position = new PositionDTO(position);
    }

    public String getName() {
        return name;
    }

    public PositionDTO getPosition() {
        return position;
    }
}
