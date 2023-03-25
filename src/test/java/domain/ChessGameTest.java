package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.board.ChessAlignmentMock;
import domain.fixture.TestFixture;
import domain.piece.Piece;
import domain.piece.Team;
import domain.position.Positions;
import gameinitializer.InitialChessAlignment;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    @DisplayName("같은 팀의 말을 두 번 이상 둘 수 없다.")
    @Test
    void playAlternately() {
        //given
        Piece blackKing = TestFixture.BLACK_KING;
        Piece whiteKing = TestFixture.WHITE_KING;
        ChessGame chessGame = ChessGame.initGame(
                ChessAlignmentMock.testStrategyWithTeam(
                        Map.of(
                                Positions.from("a1"), blackKing,
                                Positions.from("a2"), whiteKing)
                        , Team.WHITE));

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            chessGame.movePiece(Positions.from("a2"), Positions.from("a3"));
            chessGame.movePiece(Positions.from("a3"), Positions.from("a4"));
        });
    }

    @DisplayName("잡으면 게임이 종료되는 말(킹)을 잡으면 true를 반환한다.")
    @Test
    void isDeadKing() {
        //given
        Piece blackKing = TestFixture.BLACK_KING;
        Piece whiteKing = TestFixture.WHITE_KING;
        ChessGame chessGame = ChessGame.initGame(
                ChessAlignmentMock.testStrategyWithTeam(
                        Map.of(
                                Positions.from("a1"), blackKing,
                                Positions.from("a2"), whiteKing)
                        , Team.WHITE));

        //when

        //then
        assertThat(chessGame.movePiece(Positions.from("a2"), Positions.from("a1"))).isTrue();
    }

    @DisplayName("게임 중 팀 점수를 계산한다.")
    @Test
    void teamScoreCalculate() {
        //given
        ChessGame chessGame = ChessGame.initGame(new InitialChessAlignment());

        //when

        //then
        assertThat(chessGame.calculateTeamScore(Team.BLACK).getScore()).isEqualTo(38.0);
        assertThat(chessGame.calculateTeamScore(Team.WHITE).getScore()).isEqualTo(38.0);
    }

    @DisplayName("폰이 세로 직접로 있는 경우 0.5점 취급한다.")
    @Test
    void teamScoreCalculateForPawnInColumn() {
        //given
        Piece blackPawn = TestFixture.BLACK_PAWN;
        ChessGame chessGame = ChessGame.initGame(
                ChessAlignmentMock.testStrategyWithTeam(
                        Map.of(
                                Positions.from("a3"), blackPawn,
                                Positions.from("a4"), blackPawn,
                                Positions.from("a5"), blackPawn,
                                Positions.from("a7"), blackPawn)
                        , Team.WHITE));

        //when

        //then
        assertThat(chessGame.calculateTeamScore(Team.BLACK).getScore()).isEqualTo(2.0);
    }
}
