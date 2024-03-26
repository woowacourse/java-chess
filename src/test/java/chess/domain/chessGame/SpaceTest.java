package chess.domain.chessGame;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.PieceSign;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

import static chess.domain.chessGame.PieceFixture.blackBishop;
import static chess.domain.chessGame.PieceFixture.blackKing;
import static chess.domain.chessGame.PieceFixture.blackKnight;
import static chess.domain.chessGame.PieceFixture.blackPawn;
import static chess.domain.chessGame.PieceFixture.blackQueen;
import static chess.domain.chessGame.PieceFixture.blackRook;
import static chess.domain.chessGame.PieceFixture.emptyPiece;
import static chess.domain.chessGame.PieceFixture.whiteKing;
import static chess.domain.chessGame.PieceFixture.whitePawn;
import static chess.domain.chessGame.PieceFixture.whiteRook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpaceTest {

    @Test
    @DisplayName("피스를 움직이면 이동하려는 위치에 해당피스가 있다")
    void should_move_piece() {
        Space space1 = new Space(whiteRook, new Position(File.a, Rank.ONE));
        Space space2 = new Space(emptyPiece, new Position(File.a, Rank.TWO));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo(PieceSign.findSign(whiteRook));
    }

    @Test
    @DisplayName("피스를 움직이면 기존 위치가 비어있다")
    void should_be_empty_starting_space_when_move() {
        Space space1 = new Space(whiteKing, new Position(File.a, Rank.ONE));
        Space space2 = new Space(emptyPiece, new Position(File.a, Rank.TWO));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space1.doesNotHavePiece()).isTrue();
    }

    @Test
    @DisplayName("피스가 없는 지 체크 할 수 있다.")
    void should_check_piece_exist() {
        Space space = new Space(emptyPiece, new Position(File.a, Rank.ONE));

        assertThat(space.doesNotHavePiece()).isTrue();
    }

    @Test
    @DisplayName("이동할 위치에 나의 말이 있으면 이동할 수 없다")
    void should_not_move_when_target_space_has_same_color_piece() {
        Space space1 = new Space(whitePawn, new Position(File.a, Rank.ONE));
        Space space2 = new Space(whitePawn, new Position(File.a, Rank.TWO));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 피스가 이미 있습니다.");
    }

    @Test
    @DisplayName("이동할 위치에 상대 말이 있고 잡을 수 있으면 이동할 수 있다")
    void should_move_when_target_space_has_other_color_piece_and_catchable() {
        Space space1 = new Space(whitePawn, new Position(File.a, Rank.ONE));
        Space space2 = new Space(blackPawn, new Position(File.b, Rank.TWO));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("p");
    }

    @Test
    @DisplayName("이동할 위치에 상대 말이 있지만 잡을 수 없으면 이동할 수 없다")
    void should_not_move_when_target_space_has_other_color_piece_and_not_catchable() {
        Space space1 = new Space(whitePawn, new Position(File.a, Rank.ONE));
        Space space2 = new Space(blackPawn, new Position(File.a, Rank.TWO));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치의 상대 말을 잡을 수 없습니다.");
    }

    @Test
    @DisplayName("이동경로에 피스가 있으면 움직일 수 없다")
    void should_not_move_when_route_has_piece() {
        Space space1 = new Space(whiteRook, new Position(File.a, Rank.ONE));
        Space space2 = new Space(blackKnight, new Position(File.a, Rank.TWO));
        Space space3 = new Space(emptyPiece, new Position(File.a, Rank.THREE));

        assertThatThrownBy(() -> space1.movePiece(space3, List.of(space1, space2, space3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("루트에 피스가 있습니다.");
    }

    @ParameterizedTest
    @MethodSource("providePiecesAndScores")
    @DisplayName("보유 중인 Piece의 점수를 반환할 수 있다(specialPiece)")
    void should_return_score_when_special_piece(Piece piece, Double score) {
        Space space = new Space(piece, new Position(File.a, Rank.ONE));
        Score spaceScore = space.score();

        assertThat(spaceScore.asDouble()).isEqualTo(score);

    }

    private static Stream<Arguments> providePiecesAndScores() {
        return Stream.of(
                Arguments.of(blackRook, 5.0),
                Arguments.of(blackKnight, 2.5),
                Arguments.of(blackBishop, 3.0),
                Arguments.of(blackQueen, 9.0),
                Arguments.of(blackKing, 0.0)
        );
    }

    @Test
    @DisplayName("보유 중인 Pawn의 점수를 반환할 수 있다")
    void should_return_score_when_pawn() {
        Space space = new Space(blackPawn, new Position(File.a, Rank.ONE));

        assertThat(space.calculateScore()).isEqualTo(1.0);
    }
}
