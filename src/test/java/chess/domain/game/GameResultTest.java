package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import chess.domain.pieces.pawn.Pawn;
import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.Score;
import chess.domain.square.Square;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임 결과")
class GameResultTest {

    @DisplayName("기본 점수의 합을 반환한다.")
    @Test
    void gameResult() {
        //given
        Color black = Color.BLACK;

        Map<Square, Piece> pieces = Map.of(
                Square.from("c8"), new Rook(black),
                Square.from("d7"), new Bishop(black),
                Square.from("e6"), new Queen(black),
                Square.from("a7"), Pawn.of(black),
                Square.from("b6"), Pawn.of(black),
                Square.from("c7"), Pawn.of(black),
                Square.from("b8"), new King(black)
        );

        //when
        GameResult gameResult = new GameResult(pieces);

        //then
        assertThat(gameResult.getScore(black)).isEqualTo(Score.of(20));
    }
}
