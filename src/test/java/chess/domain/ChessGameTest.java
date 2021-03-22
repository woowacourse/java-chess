package chess.domain;


import chess.domain.piece.*;
import chess.exception.ImpossibleMoveException;
import chess.exception.PieceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ChessGameTest {

    @Test
    @DisplayName("board 초기화 테스트")
    void testBoardInit() throws PieceNotFoundException {
        //given
        ChessGame chessGame = new ChessGame();

        //when
        Piece rook = chessGame.piece(Position.of(0, 0)).orElseThrow(PieceNotFoundException::new);
        Piece knight = chessGame.piece(Position.of(1, 0)).orElseThrow(PieceNotFoundException::new);
        Piece bishop = chessGame.piece(Position.of(2, 0)).orElseThrow(PieceNotFoundException::new);
        Piece queen = chessGame.piece(Position.of(3, 0)).orElseThrow(PieceNotFoundException::new);
        Piece king = chessGame.piece(Position.of(4, 0)).orElseThrow(PieceNotFoundException::new);

        //then
        assertThat(rook).isInstanceOf(Rook.class);
        assertThat(knight).isInstanceOf(Knight.class);
        assertThat(bishop).isInstanceOf(Bishop.class);
        assertThat(queen).isInstanceOf(Queen.class);
        assertThat(king).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("기물 이동 테스트")
    void testMove() {
        //given
        Piece queen = new Queen(TeamColor.WHITE, Position.of(3, 0));
        Pieces pieces = new Pieces();
        pieces.add(queen);
        ChessGame chessGame = new ChessGame(pieces, TeamColor.WHITE);

        //when
        chessGame.move(Position.of(3, 0), Position.of(5, 2));
        Piece expectedPiece = chessGame.piece(Position.of(5, 2)).get();

        //than
        assertThat(chessGame.piece(Position.of(3, 0))).isEmpty();
        assertThat(expectedPiece).isEqualTo(queen);
    }

    @Test
    @DisplayName("다른 팀 기물 움직이려 할때 예외 테스트")
    void testMoveException() {
        //given
        Piece queen = new Queen(TeamColor.BLACK, Position.of(3, 0));
        Pieces pieces = new Pieces();
        pieces.add(queen);
        ChessGame chessGame = new ChessGame(pieces, TeamColor.WHITE);

        //than
        assertThatExceptionOfType(ImpossibleMoveException.class).isThrownBy(() ->
                chessGame.move(Position.of(3, 0), Position.of(5, 2))
        ).withMessage("WHITE팀 차례 입니다.");
    }

    @Test
    @DisplayName("King이 죽었을 때 테스트")
    void testKingDead() {
        //given
        Piece queen = new Queen(TeamColor.WHITE, Position.of(3, 0));
        Piece king = new King(TeamColor.BLACK, Position.of(3, 3));
        Pieces pieces = new Pieces();
        pieces.add(queen);
        pieces.add(king);
        ChessGame chessGame = new ChessGame(pieces, TeamColor.WHITE);

        //when
        chessGame.move(Position.of(3, 0), Position.of(3, 3));
        boolean isKingDead = chessGame.isKingDead();

        //than
        assertThat(isKingDead).isTrue();
    }

    @Test
    @DisplayName("최종 스코어 테스트")
    void testTotalScore() {
        //given
        Piece queen = new Queen(TeamColor.WHITE, Position.of(3, 0));
        Piece knight = new Knight(TeamColor.BLACK, Position.of(3, 3));
        Pieces pieces = new Pieces();
        pieces.add(queen);
        pieces.add(knight);
        ChessGame chessGame = new ChessGame(pieces, TeamColor.WHITE);

        //when
        Score whiteScore = chessGame.totalScoreByTeamColor(TeamColor.WHITE);
        Score blackScore = chessGame.totalScoreByTeamColor(TeamColor.BLACK);

        //than
        assertThat(whiteScore).isEqualTo(Score.from(9));
        assertThat(blackScore).isEqualTo(Score.from(2.5));
    }
}
