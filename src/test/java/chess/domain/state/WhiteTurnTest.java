package chess.domain.state;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class WhiteTurnTest {
    @ParameterizedTest
    @CsvSource(value = {"a8:R", "c8:B", "d2:p", "d1:q", "e1:k", "a2:p", "a3:."}, delimiter = ':')
    @DisplayName("위치 값 문자를 입력하여 해당되는 말을 조회한다.")
    void findPiece(String position, String symbol) {
        // given
        GameState board = new WhiteTurn(BoardInitialize.create());

        //when
        Piece piece = board.getPiece(Position.from(position));
        String result = piece.getSymbol();

        // then
        assertThat(result).isEqualTo(symbol);
    }

    @ParameterizedTest
    @CsvSource(value = {"a2:a4:p", "b2:b4:p", "h2:h4:p"}, delimiter = ':')
    @DisplayName("출발 위치와 도착 위치를 입력하여 말을 움직인다.")
    void movePiece(String source, String destination, String symbol) {
        // given
        GameState board = new WhiteTurn(BoardInitialize.create());

        // when
        board.move(source, destination);

        // then
        Piece piece = board.getPiece(Position.from(destination));
        String result = piece.getSymbol();
        assertThat(result).isEqualTo(symbol);
    }

    @ParameterizedTest
    @CsvSource(value = {"a1:b1", "d1:e1", "d1:e1"}, delimiter = ':')
    @DisplayName("같은 팀 말 kill을 시도할 시, 예외가 발생한다.")
    void killSameTeam(String source, String destination) {
        GameState board = new WhiteTurn(BoardInitialize.create());

        Assertions.assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("목적지에 같은 팀 말이 있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"a7:a6", "b8:c8", "h7:h6"}, delimiter = ':')
    @DisplayName("다른 팀 말을 움직일 시, 예외가 발생한다.")
    void otherTeamPieceMove(String source, String destination) {
        GameState board = new WhiteTurn(BoardInitialize.create());

        Assertions.assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편 말을 옮길 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"a2:a4", "b2:b4", "h2:h4"}, delimiter = ':')
    @DisplayName("말을 움직일 시, WhiteTurn으로 변경된다.")
    void changeTeam(String source, String destination) {
        // given
        GameState board = new WhiteTurn(BoardInitialize.create());

        // when
        GameState whiteTurn = board.move(source, destination);
        Team result = whiteTurn.getTeam();

        // then
        assertThat(result).isEqualTo(Team.BLACK);
    }
}