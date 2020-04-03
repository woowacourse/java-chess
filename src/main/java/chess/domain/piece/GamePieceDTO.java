package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.PositionDTO;

public class GamePieceDTO {

    private static final String COLOR_NAME_DELIMITER = "-";
    private static final String ORIGINAL_EMPTY_PIECE_NAME = "-.";
    private static final String MODIFIED_EMPTY_PIECE_NAME = "EMPTY";

    private final String name;
    private final PositionDTO position;

    private GamePieceDTO(String name, PositionDTO position) {
        this.name = name;
        this.position = position;
    }

    public static GamePieceDTO create(GamePiece gamePiece, Position position) {
        String name = String.join(COLOR_NAME_DELIMITER, gamePiece.playerColor.getName(), gamePiece.getName());
        if (name.equals(ORIGINAL_EMPTY_PIECE_NAME)) {
            name = MODIFIED_EMPTY_PIECE_NAME;
        }
        return new GamePieceDTO(name, PositionDTO.create(position));
    }

    public String getName() {
        return name;
    }

    public PositionDTO getPosition() {
        return position;
    }
}
