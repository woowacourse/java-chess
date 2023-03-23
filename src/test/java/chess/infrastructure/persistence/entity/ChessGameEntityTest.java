package chess.infrastructure.persistence.entity;

import chess.domain.board.ChessBoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.GameState;
import chess.domain.piece.Color;
import chess.infrastructure.persistence.mapper.ChessGameMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ChessGameEntity 은")
class ChessGameEntityTest {

    @Test
    void ChessGame_으로부터_생성될_수_있다() {
        // given
        final ChessGame chessGame = ChessGame.start(new ChessBoardFactory().create());

        // when
        final ChessGameEntity chessGameEntity = ChessGameMapper.fromDomain(chessGame);

        // then
        assertAll(
                () -> assertThat(chessGameEntity.id()).isNull(),
                () -> assertThat(chessGameEntity.turn()).isEqualTo("WHITE"),
                () -> assertThat(chessGameEntity.winner()).isNull()
        );
    }

    @Test
    void ChessGame_을_생성할_수_있다() {
        // given
        final ChessGameEntity chessGameEntity = new ChessGameEntity(1L, GameState.END.name(), null, "BLACK");

        // when
        final ChessGame chessGame = ChessGameMapper.toDomain(chessGameEntity, new ArrayList<>());

        // then
        assertAll(
                () -> assertThat(chessGame.id()).isEqualTo(1L),
                () -> assertThat(chessGame.state()).isEqualTo(GameState.END),
                () -> assertThat(chessGame.winColor()).isEqualTo(Color.BLACK)
        );
    }
}
