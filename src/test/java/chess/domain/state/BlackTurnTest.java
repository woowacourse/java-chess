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

class BlackTurnTest {
    @ParameterizedTest
    @CsvSource(value = {"a1:r", "b1:n", "c8:B", "d1:q", "e1:k", "a2:p", "a3:."}, delimiter = ':')
    @DisplayName("위치 값 문자를 입력하여 해당되는 말을 조회한다.")
    void findPiece(String position, String symbol) {
        // given
        GameState board = new BlackTurn(BoardInitialize.create());

        //when
        Piece piece = board.getPiece(Position.from(position));

        // then
        String result = piece.getSymbol();
        assertThat(result).isEqualTo(symbol);
    }

    @ParameterizedTest
    @CsvSource(value = {"g7:g6:P", "b7:b5:P", "h7:h5:P"}, delimiter = ':')
    @DisplayName("출발 위치와 도착 위치를 입력하여 말을 움직인다.")
    void movePiece(String source, String destination, String symbol) {
        // given
        GameState board = new BlackTurn(BoardInitialize.create());

        // when
        board.move(source, destination);

        // then
        Piece piece = board.getPiece(Position.from(destination));
        String result = piece.getSymbol();
        assertThat(result).isEqualTo(symbol);
    }

    @ParameterizedTest
    @CsvSource(value = {"a8:b8", "d8:e8", "d8:e8"}, delimiter = ':')
    @DisplayName("같은 팀 말 kill을 시도할 시, 예외가 발생한다.")
    void killSameTeam(String source, String destination) {
        GameState board = new BlackTurn(BoardInitialize.create());

        Assertions.assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("목적지에 같은 팀 말이 있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"a2:a3", "b1:c1", "h2:h3"}, delimiter = ':')
    @DisplayName("다른 팀 말을 움직일 시, 예외가 발생한다.")
    void otherTeamPieceMove(String source, String destination) {
        GameState board = new BlackTurn(BoardInitialize.create());

        Assertions.assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편 말을 옮길 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"a7:a6", "b7:b5", "h7:h6"}, delimiter = ':')
    @DisplayName("말을 움직일 시, WhiteTurn으로 변경된다.")
    void changeTeam(String source, String destination) {
        // given
        GameState board = new BlackTurn(BoardInitialize.create());

        // when
        GameState whiteTurn = board.move(source, destination);

        // then
        Team result = whiteTurn.getTeam();
        assertThat(result).isEqualTo(Team.WHITE);
    }
}