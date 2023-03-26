package chess.domain.chessgame;

import chess.TestPiecesFactory;
import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piecesfactory.StartingPiecesFactory;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.File.A;
import static chess.domain.File.E;
import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class InitialChessGameTest {

    @Test
    void 시작하는지_확인한다() {
        //given
        final ChessGame chessGame = new InitialChessGame(Board.from(new StartingPiecesFactory()));

        //when
        final ChessGame playingChessGame = chessGame.start();

        //then
        final boolean actual = playingChessGame.isPlaying();
        assertThat(actual).isTrue();
    }

    @Test
    void 움직이면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new InitialChessGame(Board.from(new StartingPiecesFactory()));

        //when
        //then
        assertThatThrownBy(() -> chessGame.move(new Position(A, TWO), new Position(A, FOUR)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    void 게임이_진행중이_아니다() {
        //given
        final ChessGame chessGame = new InitialChessGame(Board.from(new StartingPiecesFactory()));

        //when
        final boolean actual = chessGame.isPlaying();

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void 게임이_끝이_아니다() {
        //given
        final ChessGame chessGame = new InitialChessGame(Board.from(new StartingPiecesFactory()));

        //when
        final boolean actual = chessGame.isGameOver();

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void 게임이_끝난다() {
        //given
        final ChessGame chessGame = new InitialChessGame(Board.from(new StartingPiecesFactory()));

        //when
        final ChessGame endChessGame = chessGame.end();

        //then
        final boolean actual = endChessGame.isGameOver();
        assertThat(actual).isTrue();
    }

    @Test
    void 색깔별_점수를_계산하면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new InitialChessGame(Board.from(new StartingPiecesFactory()));

        //when
        //then
        assertThatThrownBy(chessGame::calculateScoreByColor)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    void 우승자를_찾으면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new InitialChessGame(Board.from(new StartingPiecesFactory()));

        //when
        //then
        assertThatThrownBy(chessGame::findScoreWinner)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    void 체스말을_꺼낸다() {
        //given
        final ChessGame chessGame = new InitialChessGame(Board.from(new TestPiecesFactory(List.of(
                new King(E, EIGHT, BLACK), new King(E, ONE, WHITE)))));

        //when
        final List<Piece> pieces = chessGame.getPieces();

        //then
        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .contains(
                        tuple(new Position(E, EIGHT), BLACK, King.class),
                        tuple(new Position(E, ONE), WHITE, King.class)
                );
    }
}