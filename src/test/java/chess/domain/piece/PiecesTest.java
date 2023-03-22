package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PiecesTest {

    @DisplayName("초기 ChessBoard 에서는 한 file 에 4개의 체스말이 들어있다.")
    @Test
    void 초기_체스판_한_열_확인() {
        ChessBoard chessBoard = new ChessBoard();
        Set<Pieces> piecesCollectedByFile = chessBoard.getPiecesCollectedByFile();

        for (Pieces pieces : piecesCollectedByFile) {
            assertThat(pieces.size()).isEqualTo(4);
        }
    }

}