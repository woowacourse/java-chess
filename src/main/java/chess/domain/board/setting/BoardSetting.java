package chess.domain.board.setting;

import chess.domain.piece.type.PieceWithColorType;
import java.util.ArrayList;
import java.util.List;

public abstract class BoardSetting {
    private static final int ALL_CELLS_SIZE = 64;

    private final List<PieceWithColorType> pieces = new ArrayList<>();

    protected BoardSetting(List<PieceWithColorType> piecesSetting) {
        validate(piecesSetting);
        this.pieces.addAll(piecesSetting);
    }

    private void validate(List<PieceWithColorType> piecesSetting) {
        if (piecesSetting.size() != ALL_CELLS_SIZE) {
            throw new IllegalArgumentException("보드의 모든 칸을 세팅해야 합니다.");
        }
    }

    public List<PieceWithColorType> getPiecesSetting() {
        return pieces;
    }
}
