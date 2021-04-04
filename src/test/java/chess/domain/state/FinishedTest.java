package chess.domain.state;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FinishedTest {
    private Finished finished;

    @BeforeEach
    void setup() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
        chessBoard.put(Position.valueOf("a4"), new Queen(TeamColor.BLACK));
        finished = new Finished(chessBoard);
    }

    @Test
    @DisplayName("움직임 불가")
    void move() {
        assertThatThrownBy(() -> finished.move(Position.valueOf("a4"), Position.valueOf("a5")))
                .isInstanceOf(UnsupportedOperationException.class);
    }


    @Test
    @DisplayName("종료 불가")
    void terminate() {
        assertThatThrownBy(() -> finished.terminate())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("실행중인지 확인")
    void isRunning() {
        assertFalse(finished.isRunning());
    }

    @Test
    @DisplayName("결과 테스트 진행")
    void result() {
    }

    // todo: result 테스트코드 작성하기
}