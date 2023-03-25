package chess.domain.board;

import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.InitPawnPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.PositionFixture.A1;
import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.A3;
import static chess.domain.PositionFixture.A4;
import static chess.domain.PositionFixture.B1;
import static chess.domain.PositionFixture.B2;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ScoreTest {

    @Test
    void King_이_존재하지_않는다면_점수는_0점이다() {
        //given
        HashMap<Position, Piece> data = new HashMap<>();

        //when
        data.put(A1, new PawnPiece(Color.WHITE));
        Score score = new Score(data, Color.WHITE);
        double result = score.getScore();

        //then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void King_이_존재하지_않을_경우_점수를_가질_수_있다() {
        //given
        HashMap<Position, Piece> data = new HashMap<>();

        //when
        data.put(A1, new PawnPiece(Color.WHITE));
        data.put(B1, new KingPiece(Color.WHITE));
        Score score = new Score(data, Color.WHITE);
        double result = score.getScore();

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void 폰은_다른_File에_있으면_1점으로_계산된다() {
        //given
        HashMap<Position, Piece> data = new HashMap<>();

        //when
        data.put(A1, new PawnPiece(Color.WHITE));
        data.put(B1, new InitPawnPiece(Color.WHITE));
        data.put(B2, new KingPiece(Color.WHITE));
        Score score = new Score(data, Color.WHITE);
        double result = score.getScore();

        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void 폰은_같은_File에_있으면_0_5_점으로_계산된다() {
        //given
        HashMap<Position, Piece> data = new HashMap<>();

        //when
        data.put(A1, new PawnPiece(Color.WHITE));
        data.put(A2, new InitPawnPiece(Color.WHITE));
        data.put(A3, new PawnPiece(Color.WHITE));
        data.put(A4, new KingPiece(Color.WHITE));
        Score score = new Score(data, Color.WHITE);
        double result = score.getScore();

        //then
        assertThat(result).isEqualTo(1.5);
    }

    @Test
    void Score_는_같은_색에의한_점수_값이다() {
        //given
        HashMap<Position, Piece> data = new HashMap<>();

        //when
        data.put(A1, new PawnPiece(Color.BLACK));
        data.put(A2, new PawnPiece(Color.WHITE));
        data.put(A3, new PawnPiece(Color.WHITE));
        data.put(A4, new KingPiece(Color.WHITE));

        Score score = new Score(data, Color.WHITE);
        double result = score.getScore();
        Color color = score.getColor();

        //then
        assertThat(result).isEqualTo(1.0);
        assertThat(color).isEqualTo(Color.WHITE);
    }

    @Test
    void 초기_보드는_38점_이다() {
        //given
        Board board = BoardGenerator.makeBoard();
        Map<Position, Piece> board1 = board.getBoardData();

        //when
        Score score = new Score(board1, Color.WHITE);
        double result = score.getScore();
        Color color = score.getColor();

        //then
        assertThat(result).isEqualTo(38);
        assertThat(color).isEqualTo(Color.WHITE);
    }


}
