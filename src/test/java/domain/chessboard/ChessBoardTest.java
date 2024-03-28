package domain.chessboard;

import domain.Team;
import domain.piece.Piece;
import domain.square.File;
import domain.square.Rank;
import domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class ChessBoardTest {

    @DisplayName("Source에 기물이 없으면 움직일 수 없다.")
    @Test
    void validateEmptySource() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();
        final Square source = new Square(File.A, Rank.THREE);
        final Square target = new Square(File.A, Rank.FOUR);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }

    @DisplayName("제자리 이동은 불가능하다.")
    @Test
    void validateSameSquare() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();
        final Square source = new Square(File.A, Rank.TWO);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, source))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제자리 이동은 불가합니다.");
    }

    @DisplayName("White팀이 처음 시작하고 현재 턴인 팀의 기물만 움직일 수 있다.")
    @Test
    void validateTeam() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();
        final Square blackPawnSource = new Square(File.A, Rank.SEVEN);
        final Square target = new Square(File.A, Rank.SIX);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(blackPawnSource, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대방의 말을 움직일 수 없습니다.");
    }

    @DisplayName("기물에 맞지 않는 움직임으로 이동할 수 없다. 폰은 공격이 아닌경우 대각선으로 이동할 수 없다.")
    @Test
    void validateDirection() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();
        final Square pawnSource = new Square(File.B, Rank.TWO);
        final Square target = new Square(File.C, Rank.THREE);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(pawnSource, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 경로입니다.");

    }

    @DisplayName("Piece를 움직인다.")
    @TestFactory
    Collection<DynamicTest> move() {
        final ChessBoard chessBoard = ChessBoard.create();

        /*
        RNBQKBNR  8 (rank 8)
        PPPPPPPP  7
        ........  6
        ........  5
        ........  4
        ........  3
        pppppppp  2
        rnbqkbnr  1 (rank 1)

        abcdefgh
         */
        return List.of(
                /*
                RNBQKBNR  8 (rank 8)
                PPPPPPPP  7
                ........  6
                ........  5
                .p......  4
                ........  3
                p.pppppp  2
                rnbqkbnr  1 (rank 1)

                abcdefgh
                 */
                dynamicTest("폰은 초기에 2칸 앞으로 움직일 수 있다.", () -> {
                    // given
                    final Square source = new Square(File.B, Rank.TWO);
                    final Square target = new Square(File.B, Rank.FOUR);

                    final Piece whitePawn = chessBoard.getPieceSquares().get(source);

                    // when
                    chessBoard.move(source, target);

                    //then
                    final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
                    final Piece movedPiece = pieceSquares.get(target);

                    assertThat(movedPiece).isEqualTo(whitePawn);
                }),
                dynamicTest("이동 후 턴이 변경된다.", () ->
                        assertThat(chessBoard).extracting("currentTeam")
                                .isEqualTo(Team.BLACK)
                ),
                /*
                R.BQKBNR  8 (rank 8)
                PPPPPPPP  7
                ..N.....  6
                ........  5
                .p......  4
                ........  3
                p.pppppp  2
                rnbqkbnr  1 (rank 1)

                abcdefgh
                 */
                dynamicTest("나이트는 기물을 건너 뛰어 움직일 수 있다.", () -> {
                    // given
                    final Square source = new Square(File.B, Rank.EIGHT);
                    final Square target = new Square(File.C, Rank.SIX);

                    final Piece blackKnight = chessBoard.getPieceSquares().get(source);

                    // when
                    chessBoard.move(source, target);

                    //then
                    final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
                    final Piece movedPiece = pieceSquares.get(target);

                    assertThat(movedPiece).isEqualTo(blackKnight);
                }),
                dynamicTest("폰은 초기 Rank가 아니면 2칸 앞으로 움직일 수 없다.", () -> {
                    // given
                    final Square pawnSource = new Square(File.B, Rank.FOUR);
                    final Square target = new Square(File.B, Rank.SIX);

                    // when & then
                    assertThatThrownBy(() -> chessBoard.move(pawnSource, target))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage("움직일 수 없는 경로입니다.");
                }),
                dynamicTest("다른 기물에 가로막히면 이동할 수 없다.", () -> {
                    // given
                    final Square queenSource = new Square(File.D, Rank.ONE);
                    final Square target = new Square(File.D, Rank.SIX);

                    // when & then
                    assertThatThrownBy(() -> chessBoard.move(queenSource, target))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage("기물에 가로막혀 갈 수 없는 경로입니다.");
                }),
                /*
                R.BQKBNR  8 (rank 8)
                PPPPPPPP  7
                ..N.....  6
                ........  5
                .p......  4
                ..p.....  3
                p..ppppp  2
                rnbqkbnr  1 (rank 1)

                abcdefgh
                 */
                dynamicTest("폰은 초기에 1칸 앞으로도 움직일 수 있다.", () -> {
                    // given
                    final Square pawnSource = new Square(File.C, Rank.TWO);
                    final Square target = new Square(File.C, Rank.THREE);

                    final Piece whitePawn = chessBoard.getPieceSquares().get(pawnSource);

                    // when
                    chessBoard.move(pawnSource, target);

                    //then
                    final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
                    final Piece movedPiece = pieceSquares.get(target);

                    assertThat(movedPiece).isEqualTo(whitePawn);
                }),
                /*
                R.BQKBNR  8 (rank 8)
                PPPPPPPP  7
                ........  6
                ........  5
                .N......  4
                ..p.....  3
                p..ppppp  2
                rnbqkbnr  1 (rank 1)

                abcdefgh
                 */
                dynamicTest("상대방 기물 위치로 이동하면 상대방 기물이 제거된다.", () -> {
                    // given
                    final Square knightSource = new Square(File.C, Rank.SIX);
                    final Square pawnSource = new Square(File.B, Rank.FOUR);
                    final Map<Square, Piece> beforePieceSquares = chessBoard.getPieceSquares();
                    final int size = beforePieceSquares.size();
                    final Piece blackKnight = beforePieceSquares.get(knightSource);

                    // when
                    chessBoard.move(knightSource, pawnSource);

                    //then
                    final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
                    final Piece movedPiece = pieceSquares.get(pawnSource);

                    assertAll(
                            () -> assertThat(pieceSquares.values()).hasSize(size - 1),
                            () -> assertThat(movedPiece).isEqualTo(blackKnight)
                    );
                }),
                /*
                R.BQKBNR  8 (rank 8)
                PPPPPPPP  7
                ........  6
                ........  5
                .p......  4
                ........  3
                p..ppppp  2
                rnbqkbnr  1 (rank 1)

                abcdefgh
                 */
                dynamicTest("폰은 공격시에 대각선으로 이동할 수 있다.", () -> {
                    // given
                    final Square pawnSource = new Square(File.C, Rank.THREE);
                    final Square knightSource = new Square(File.B, Rank.FOUR);

                    final Piece whitePawn = chessBoard.getPieceSquares().get(pawnSource);

                    // when
                    chessBoard.move(pawnSource, knightSource);

                    //then
                    final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
                    final Piece movedPiece = pieceSquares.get(knightSource);

                    assertThat(movedPiece).isEqualTo(whitePawn);
                }),
                /*
                R.BQKBNR  8 (rank 8)
                P.PPPPPP  7
                ........  6
                .P......  5
                .p......  4
                ........  3
                p..ppppp  2
                rnbqkbnr  1 (rank 1)

                abcdefgh
                 */
                dynamicTest("폰은 앞으로 공격할 수 없다.", () -> {
                    // given
                    final Square blackPawnSource = new Square(File.B, Rank.SEVEN);
                    final Square target = new Square(File.B, Rank.FIVE);
                    chessBoard.move(blackPawnSource, target);

                    final Square whitePawnSource = new Square(File.B, Rank.FOUR);

                    // when & then
                    assertThatThrownBy(() -> chessBoard.move(whitePawnSource, target))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage("공격할 수 없는 경로입니다.");
                })
        );
    }
}
