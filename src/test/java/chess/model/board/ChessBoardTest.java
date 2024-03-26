package chess.model.board;

import chess.model.piece.*;
import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;
<<<<<<< Updated upstream
import chess.model.rule.Turn;
=======
import chess.model.game.Turn;
>>>>>>> Stashed changes
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ChessBoardTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard(new ChessBoardInitializer().create());
    }

    @Test
    @DisplayName("Source 위치의 기물을 Target 위치로 이동한다.")
    void move() {
        ChessPosition source = ChessPosition.of(File.B, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.B, Rank.FOUR);
        chessBoard.move(source, target, Turn.from(Side.WHITE));

        Map<ChessPosition, Piece> board = chessBoard.getBoard();

        assertAll(
                () -> assertThat(board).containsEntry(source, Blank.INSTANCE),
                () -> assertThat(board).doesNotContainEntry(target, Blank.INSTANCE)
        );
    }

    @Test
    @DisplayName("Source 위치에 기물이 없으면 예외가 발생한다.")
    void moveWithBlankSource() {
        // given
        ChessPosition source = ChessPosition.of(File.H, Rank.SEVEN);
        ChessPosition target = ChessPosition.of(File.D, Rank.TWO);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target, Turn.from(Side.BLACK)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임 차례에 맞지 않는 Source 기물의 위치를 입력하면 예외가 발생한다.")
    void moveWithInvalidTurn() {
        // given
        ChessPosition source = ChessPosition.of(File.B, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.B, Rank.FOUR);
        Turn turn = Turn.from(Side.BLACK);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target, turn))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Target 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void moveWhenTargetIsSameSide() {
        // given
        ChessPosition source = ChessPosition.of(File.A, Rank.ONE);
        ChessPosition target = ChessPosition.of(File.A, Rank.TWO);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, Pawn.from(Side.BLACK), target, Bishop.from(Side.BLACK)));

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target, Turn.from(Side.WHITE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("경로가 비어있다면 예외가 발생한다.")
    void moveWhenPathEmpty() {
        // given
        ChessPosition source = ChessPosition.of(File.A, Rank.ONE);
        ChessPosition target = ChessPosition.of(File.D, Rank.TWO);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target, Turn.from(Side.WHITE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 경로에 기물이 존재한다면 예외가 발생한다.")
    void moveWhenPathContainsPiece() {
        // given
        ChessPosition source = ChessPosition.of(File.A, Rank.ONE);
        ChessPosition target = ChessPosition.of(File.A, Rank.SIX);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target, Turn.from(Side.WHITE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Knight는 이동 경로에 기물이 존재해도 뛰어넘을 수 있다.")
    void moveKnightWhenPathContainsPiece() {
        // given
        Knight knight = Knight.from(Side.WHITE);
        ChessPosition knightPosition = ChessPosition.of(File.A, Rank.ONE);
        ChessBoard customChessBoard = createCustomChessBoard(knight, knightPosition);

        ChessPosition targetPosition = ChessPosition.of(File.B, Rank.THREE);

        // when
        customChessBoard.move(knightPosition, targetPosition, Turn.from(Side.WHITE));

        // then
        Map<ChessPosition, Piece> actualBoard = customChessBoard.getBoard();
        assertThat(actualBoard).containsEntry(targetPosition, knight);
    }

    private ChessBoard createCustomChessBoard(Knight knight, ChessPosition knightPosition) {
        Map<ChessPosition, Piece> chessBoard = Arrays.stream(File.values())
                .flatMap(this::createChessPositionStream)
                .collect(toMap(identity(), chessPosition -> Blank.INSTANCE));
        chessBoard.put(knightPosition, knight);
        chessBoard.put(knightPosition.calculateNextPosition(0, 1), Pawn.from(Side.WHITE));
        return new ChessBoard(chessBoard);
    }

    private Stream<ChessPosition> createChessPositionStream(File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> ChessPosition.of(file, rank));
    }
}
