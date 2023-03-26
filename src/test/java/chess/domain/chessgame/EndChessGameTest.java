package chess.domain.chessgame;

import chess.domain.Color;
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
class EndChessGameTest {

    private static final List<Piece> STARTING_PIECES = new StartingPiecesFactory().generate();

    @Test
    void 시작하면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(STARTING_PIECES), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }

    @Test
    void 움직이면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(STARTING_PIECES), Color.BLACK);

        //when
        //then
        assertThatThrownBy(() -> chessGame.move(new Position(A, TWO), new Position(A, FOUR)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }

    @Test
    void 게임이_진행중이_아니다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(STARTING_PIECES), Color.BLACK);

        //when
        final boolean actual = chessGame.isPlaying();

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void 게임이_끝이다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(STARTING_PIECES), Color.BLACK);

        //when
        final boolean actual = chessGame.isGameOver();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    void 게임을_끝내면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(STARTING_PIECES), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::end)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }

    @Test
    void 색깔별_점수를_계산하면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(STARTING_PIECES), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::calculateScoreByColor)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }

    @Test
    void 우승자를_찾으면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(STARTING_PIECES), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::findScoreWinner)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 끝났습니다.");
    }


    @Test
    void 체스말을_꺼낸다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(
                List.of(new King(E, EIGHT, BLACK), new King(E, ONE, WHITE))),
                Color.BLACK);

        //when
        final List<Piece> pieces = chessGame.getPieces();

        //then
        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .contains(
                        tuple(new Position(E, EIGHT), BLACK, King.class),
                        tuple(new Position(E, ONE), WHITE, King.class)
                );
    }

    @Test
    void 현재_순서_색깔을_꺼낸다() {
        //given
        final ChessGame chessGame = new EndChessGame(Board.from(
                List.of(new King(E, EIGHT, BLACK), new King(E, ONE, WHITE))),
                Color.BLACK);

        //when
        final Color currentTurnColor = chessGame.getCurrentTurnColor();

        //then
        assertThat(currentTurnColor).isEqualTo(BLACK);
    }
}