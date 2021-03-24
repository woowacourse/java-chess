package chess.domain.board.setting;

import chess.domain.piece.type.PieceWithColorType;
import java.util.List;

public class BoardCustomSetting extends BoardSetting {
    public BoardCustomSetting(List<PieceWithColorType> piecesSetting) {
        super(piecesSetting);
    }
}
