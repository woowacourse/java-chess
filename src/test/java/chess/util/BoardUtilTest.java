package chess.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Position;
import chess.domain.pieces.King;
import chess.domain.pieces.Name;
import chess.domain.pieces.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardUtilTest {

    private final BoardUtil boardUtil = new BoardUtil();

    @Test
    @DisplayName("DB 저장을 위해 체스판을 문자열로 압축한다.")
    void compress_board() {
        // given
        Map<Position, Piece> board = new HashMap<>();
        String a1 = "a1";
        String a2 = "a2";
        String k = "k";
        String p = "p";

        board.put(Position.from(a1), new King(new Name(k)));
        board.put(Position.from(a2), new King(new Name(p)));

        // when
        List<String> compressedBoard = boardUtil.compressBoard(board);

        // then
        assertAll(
                () -> assertThat(compressedBoard.get(0)).contains(a1),
                () -> assertThat(compressedBoard.get(0)).contains(a2),
                () -> assertThat(compressedBoard.get(1)).contains(k),
                () -> assertThat(compressedBoard.get(1)).contains(p)
        );
    }

    @Test
    @DisplayName("DB에서 가져온 문자열로 압축된 체스판을 Map으로 압축을 풀어주고 반환한다.")
    void unCompressed_board() {
        // given
        List<String> compressedBoard = List.of("a1a2", "kp");

        // when
        Map<Position, Piece> chessBoard = boardUtil.unCompressBoard(compressedBoard);

        // then
        assertAll(
                () -> assertThat(chessBoard.get(Position.from("a1")).getName()).isEqualTo("k"),
                () -> assertThat(chessBoard.get(Position.from("a2")).getName()).isEqualTo("p")
        );
    }
}
