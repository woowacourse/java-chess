package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    @ParameterizedTest
    @DisplayName("white 턴인지 확인")
    @MethodSource("createWhiteStatus")
    void isWhiteTurn(Status status, boolean expected) {
        assertThat(status.isWhiteTurn()).isEqualTo(expected);
    }

    static Stream<Arguments> createWhiteStatus() {
        return Stream.of(
                Arguments.of(new Status(0, StatusType.PROCESSING), true),
                Arguments.of(new Status(1, StatusType.PROCESSING), false)
        );
    }

    @ParameterizedTest
    @DisplayName("black 턴인지 확인")
    @MethodSource("createBlackStatus")
    void isBlackTurn(Status status, boolean expected) {
        assertThat(status.isBlackTurn()).isEqualTo(expected);
    }

    static Stream<Arguments> createBlackStatus() {
        return Stream.of(
                Arguments.of(new Status(0, StatusType.PROCESSING), false),
                Arguments.of(new Status(1, StatusType.PROCESSING), true)
        );
    }

    @ParameterizedTest
    @DisplayName("processing이 아닌지 확인")
    @MethodSource("createNotProcessingStatus")
    void isNotProcessing(Status status, boolean expected) {
        assertThat(status.isNotProcessing()).isEqualTo(expected);
    }

    static Stream<Arguments> createNotProcessingStatus() {
        return Stream.of(
                Arguments.of(new Status(0, StatusType.READY), true),
                Arguments.of(new Status(0, StatusType.PROCESSING), false),
                Arguments.of(new Status(0, StatusType.FINISHED), true)
        );
    }

    @ParameterizedTest
    @DisplayName("finish가 아닌지 확인")
    @MethodSource("createNotFinishedStatus")
    void isNotFinished(Status status, boolean expected) {
        assertThat(status.isNotFinished()).isEqualTo(expected);
    }

    static Stream<Arguments> createNotFinishedStatus() {
        return Stream.of(
                Arguments.of(new Status(0, StatusType.READY), true),
                Arguments.of(new Status(0, StatusType.PROCESSING), true),
                Arguments.of(new Status(0, StatusType.FINISHED), false)
        );
    }

    @Test
    void nextTurn() {
        Status status = new Status(0, StatusType.PROCESSING);
        int initTurn = status.getTurn();
        status = status.nextTurn();

        assertThat(status.getTurn()).isEqualTo(initTurn + 1);
    }

    @Test
    void finish() {
        Status status = new Status(0, StatusType.PROCESSING);
        status = status.finish();

        assertThat(status.getStatusType()).isEqualTo(StatusType.FINISHED);
    }
}