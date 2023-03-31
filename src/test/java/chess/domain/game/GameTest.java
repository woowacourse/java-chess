package chess.domain.game;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.KingPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static chess.domain.PositionFixture.A1;
import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.A3;
import static chess.domain.PositionFixture.A4;
import static chess.domain.PositionFixture.A5;
import static chess.domain.PositionFixture.B1;
import static chess.domain.PositionFixture.B2;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GameTest {

    @Test
    void 두_진영이_왕과_최소_한개의_기물이_존재하면_해당_게임은_진행가능한_게임이다() {
        //given
        HashMap<Position, Piece> data = new HashMap<>();
        data.put(A1, new PawnPiece(Color.BLACK));
        data.put(A5, new KingPiece(Color.BLACK));
        data.put(A2, new PawnPiece(Color.WHITE));
        data.put(A3, new PawnPiece(Color.WHITE));
        data.put(A4, new KingPiece(Color.WHITE));

        //when
        Game game = Game.of(new Board(data, Color.WHITE));

        //when
        assertThat(game)
                .isInstanceOf(RunningGame.class);
    }


    @Test
    void 두_진영이_중_한쪽이라도_왕이_없으면_끝난_게임이다() {
        //given
        HashMap<Position, Piece> data = new HashMap<>();
        data.put(A1, new PawnPiece(Color.BLACK));
        data.put(A2, new PawnPiece(Color.WHITE));
        data.put(A3, new PawnPiece(Color.WHITE));
        data.put(A4, new KingPiece(Color.WHITE));

        //when
        Game game = Game.of(new Board(data, Color.WHITE));

        //when
        assertThat(game)
                .isInstanceOf(EndGame.class);
    }

    @Test
    void 두_진영이_중_한쪽이라도_왕_밖에_없으면_게임_끝이다() {
        //given
        HashMap<Position, Piece> data = new HashMap<>();
        data.put(A1, new KingPiece(Color.BLACK));

        data.put(A2, new PawnPiece(Color.WHITE));
        data.put(A3, new PawnPiece(Color.WHITE));
        data.put(A4, new KingPiece(Color.WHITE));

        //when
        Game game = Game.of(new Board(data, Color.WHITE));

        //when
        assertThat(game)
                .isInstanceOf(EndGame.class);
    }

    @Test
    void 왕을_잡으면_게임은_끝난다() {
        //given
        HashMap<Position, Piece> data = new HashMap<>();
        data.put(A1, new KingPiece(Color.BLACK));
        data.put(B1, new PawnPiece(Color.BLACK));

        data.put(B2, new KingPiece(Color.WHITE));
        data.put(A2, new PawnPiece(Color.WHITE));
        data.put(A3, new PawnPiece(Color.WHITE));

        //when
        Game game = Game.of(new Board(data, Color.WHITE));
        assertThat(game)
                .isInstanceOf(RunningGame.class);

        Game movedGame = game.move(B2, A1);

        //when
        assertThat(movedGame)
                .isInstanceOf(EndGame.class);
    }
}
