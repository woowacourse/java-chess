package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    void should_체스판을초기화해반환한다_when_create호출시() {
        //given

        //when
        ChessBoard chessBoard = ChessBoardFactory.create();

        //then
        assertThat(chessBoard).extracting("squares", InstanceOfAssertFactories.collection(Square.class))
                .hasSize(64);
    }

}