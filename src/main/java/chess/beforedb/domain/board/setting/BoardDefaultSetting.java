package chess.beforedb.domain.board.setting;

import static chess.beforedb.domain.piece.type.PieceWithColorType.B_BP;
import static chess.beforedb.domain.piece.type.PieceWithColorType.B_KG;
import static chess.beforedb.domain.piece.type.PieceWithColorType.B_NT;
import static chess.beforedb.domain.piece.type.PieceWithColorType.B_PN;
import static chess.beforedb.domain.piece.type.PieceWithColorType.B_QN;
import static chess.beforedb.domain.piece.type.PieceWithColorType.B_RK;
import static chess.beforedb.domain.piece.type.PieceWithColorType.W_BP;
import static chess.beforedb.domain.piece.type.PieceWithColorType.W_KG;
import static chess.beforedb.domain.piece.type.PieceWithColorType.W_NT;
import static chess.beforedb.domain.piece.type.PieceWithColorType.W_PN;
import static chess.beforedb.domain.piece.type.PieceWithColorType.W_QN;
import static chess.beforedb.domain.piece.type.PieceWithColorType.W_RK;

import java.util.Arrays;

public class BoardDefaultSetting extends BoardSetting {
    public BoardDefaultSetting() {
        super(Arrays.asList(
            B_RK, B_NT, B_BP, B_QN, B_KG, B_BP, B_NT, B_RK,
            B_PN, B_PN, B_PN, B_PN, B_PN, B_PN, B_PN, B_PN,
            null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null,
            W_PN, W_PN, W_PN, W_PN, W_PN, W_PN, W_PN, W_PN,
            W_RK, W_NT, W_BP, W_QN, W_KG, W_BP, W_NT, W_RK));
    }
}
