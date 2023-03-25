package chess.domain.chessgame;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piecesfactory.StartingPiecesFactory;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.File.A;
import static chess.domain.Rank.FOUR;
import static chess.domain.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class EndChessGameTest {
    @Test
    void 시작하면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }

    @Test
    void 움직이면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        //then
        assertThatThrownBy(() -> chessGame.move(new Position(A, TWO), new Position(A, FOUR)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }

    @Test
    void 게임이_진행중이_아니다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        final boolean actual = chessGame.isPlaying();

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void 게임이_끝이다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        final boolean actual = chessGame.isGameOver();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    void 게임을_끝내면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::end)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }

    @Test
    void 색깔별_점수를_계산하면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::calculateScoreByColor)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }

    @Test
    void 우승자를_찾으면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::findScoreWinner)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }

    @Test
    void 체스말을_꺼내면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::getPieces)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }
}