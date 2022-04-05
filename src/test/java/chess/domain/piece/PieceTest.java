package chess.domain.piece;

import static chess.domain.piece.vo.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceTest {

    private Piece piece;

    @BeforeEach
    void setUp() {
        piece = new Piece(WHITE, Position.from("a1")) {
            @Override
            public Piece move(List<Piece> otherPieces, Position targetPosition) {
                return null;
            }

            @Override
            public double getScore() {
                return 0;
            }
        };
    }

    @ParameterizedTest
    @DisplayName("기물이 BLACK 팀인지 반환")
    @CsvSource({"BLACK, true", "WHITE, false"})
    void isBlackTeam(TeamColor teamColor, boolean expected) {
        // given
        Piece piece = new Piece(teamColor, Position.from("a1")) {
            @Override
            public Piece move(List<Piece> otherPieces, Position targetPosition) {
                return null;
            }

            @Override
            public double getScore() {
                return 0;
            }
        };
        //when, then
        assertThat(piece.isBlackTeam()).isEqualTo(expected);
    }

    @Test
    @DisplayName("기물이 주어진 위치에 있는지 반환")
    void hasPosition() {
        assertThat(piece.hasPosition(Position.from("a1"))).isTrue();
    }

    @Test
    @DisplayName("기물과 같은 팀인지 반환")
    void isSameTeam() {
        //given
        Piece otherPiece = new Piece(WHITE, Position.from("a2")) {
            @Override
            public Piece move(List<Piece> otherPieces, Position targetPosition) {
                return null;
            }

            @Override
            public double getScore() {
                return 0;
            }
        };
        // when
        boolean actual = piece.isSameTeam(otherPiece);
        // then
        assertThat(actual).isTrue();
    }
}
