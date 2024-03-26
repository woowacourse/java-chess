package chess.domain.board;

import chess.dto.PieceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("기물 생성")
class ChessBoardFactoryTest {

    List<PieceResponse> pieces;

    @BeforeEach
    void setUp() {
        pieces = new ChessBoardFactory().createBoard().createBoardStatus();
    }

    @DisplayName("검은 기물")
    @Nested
    class BlackTest {
        @DisplayName("검은 기물들의 생성에 성공한다")
        @Test
        void createBlackPieces() {
            //given & when & then
            assertThat(pieces)
                    .contains(
                            new PieceResponse(0, 7, "BLACK", "ROOK"),
                            new PieceResponse(1, 7, "BLACK", "KNIGHT"),
                            new PieceResponse(2, 7, "BLACK", "BISHOP"),
                            new PieceResponse(3, 7, "BLACK", "QUEEN"),
                            new PieceResponse(4, 7, "BLACK", "KING"),
                            new PieceResponse(5, 7, "BLACK", "BISHOP"),
                            new PieceResponse(6, 7, "BLACK", "KNIGHT"),
                            new PieceResponse(7, 7, "BLACK", "ROOK")
                    );
        }

        @DisplayName("검은 폰 생성에 성공한다")
        @Test
        void createBlackPawns() {
            //given & when & then
            assertThat(pieces)
                    .contains(
                            new PieceResponse(0, 6, "BLACK", "PAWN"),
                            new PieceResponse(1, 6, "BLACK", "PAWN"),
                            new PieceResponse(2, 6, "BLACK", "PAWN"),
                            new PieceResponse(3, 6, "BLACK", "PAWN"),
                            new PieceResponse(4, 6, "BLACK", "PAWN"),
                            new PieceResponse(5, 6, "BLACK", "PAWN"),
                            new PieceResponse(6, 6, "BLACK", "PAWN"),
                            new PieceResponse(7, 6, "BLACK", "PAWN")
                    );
        }
    }

    @DisplayName("흰 기물")
    @Nested
    class WhiteTest {
        @DisplayName("흰 기물들의 생성에 성공한다")
        @Test
        void createWhitePieces() {
            //given & when & then
            assertThat(pieces)
                    .contains(
                            new PieceResponse(0, 0, "WHITE", "ROOK"),
                            new PieceResponse(1, 0, "WHITE", "KNIGHT"),
                            new PieceResponse(2, 0, "WHITE", "BISHOP"),
                            new PieceResponse(3, 0, "WHITE", "QUEEN"),
                            new PieceResponse(4, 0, "WHITE", "KING"),
                            new PieceResponse(5, 0, "WHITE", "BISHOP"),
                            new PieceResponse(6, 0, "WHITE", "KNIGHT"),
                            new PieceResponse(7, 0, "WHITE", "ROOK")
                    );
        }

        @DisplayName("흰 폰 생성에 성공한다")
        @Test
        void createWhitePawns() {
            //given & when & then
            assertThat(pieces)
                    .contains(
                            new PieceResponse(0, 1, "WHITE", "PAWN"),
                            new PieceResponse(1, 1, "WHITE", "PAWN"),
                            new PieceResponse(2, 1, "WHITE", "PAWN"),
                            new PieceResponse(3, 1, "WHITE", "PAWN"),
                            new PieceResponse(4, 1, "WHITE", "PAWN"),
                            new PieceResponse(5, 1, "WHITE", "PAWN"),
                            new PieceResponse(6, 1, "WHITE", "PAWN"),
                            new PieceResponse(7, 1, "WHITE", "PAWN")
                    );
        }
    }
}
