package chess.beforedb.domain.board.setting;

import chess.beforedb.domain.piece.type.PieceWithColorType;
import java.util.List;

public class BoardCustomSetting extends BoardSetting {
    public BoardCustomSetting(List<PieceWithColorType> piecesSetting) {
        super(piecesSetting);
    }
}
