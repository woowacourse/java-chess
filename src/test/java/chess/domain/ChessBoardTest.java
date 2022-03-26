package chess.domain;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Piece;
import chess.domain.piece.multiple.Bishop;
import chess.domain.piece.multiple.Queen;
import chess.domain.piece.multiple.Rook;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.single.King;
import chess.domain.piece.single.Knight;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardTest {

    @Test
    @DisplayName("생성 시 null 예외발생")
    void constructorNullException() {
        assertThatThrownBy(() -> new ChessBoard(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("pieces는 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("새로운 기본 체스판 생성")
    void createNewChessBoard() {
        ChessBoard chessBoard = ChessBoard.createNewChessBoard();
        assertThat(chessBoard.getPieces()).hasSize(32);
    }

    @Test
    @DisplayName("게임 종료 후 기물 이동 시 예외발생")
    void movePieceException() {
        Position position = Position.of('a', '1');
        Position target = Position.of('a', '2');
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new King(WHITE)));

        assertThatThrownBy(() -> chessBoard.movePiece(position, target, WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되어 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("상대 진영의 기물 이동 시 예외발생")
    void moveOtherTeamPieceException() {
        Position position = Position.of('a', '1');
        Position target = Position.of('a', '2');
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new King(WHITE),
                Position.of('e', '8'), new King(BLACK)));

        assertThatThrownBy(() -> chessBoard.movePiece(position, target, BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("상대 진영의 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("프로모션이 불가능할 때 예외발생")
    void promotionExceptionByStatus() {
        ChessBoard chessBoard = new ChessBoard(Map.of(Position.of('a','2'), new WhitePawn()));
        assertThatThrownBy(() -> chessBoard.promotion(PromotionPiece.QUEEN, WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("프로모션 프로모션 가능한 기물이 없습니다.");
    }

    @Test
    @DisplayName("프로모션 진행")
    void promotion() {
        Position position = Position.of('a', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new WhitePawn()));
        chessBoard.promotion(PromotionPiece.QUEEN, WHITE);
        assertThat(chessBoard.pieceByPosition(position)).isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,false", "a,2,true"})
    @DisplayName("해당 위치가 비어있는지 확인")
    void isPositionEmpty(char col, char row, boolean expected) {
        Position position = Position.of('a', '1');
        Piece piece = new Knight(WHITE);
        ChessBoard chessBoard = new ChessBoard(Map.of(position, piece));

        assertThat(chessBoard.isPositionEmpty(Position.of(col, row))).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력 위치에 기물이 없을 때 예외발생")
    void exceptionPieceByPosition() {
        ChessBoard chessBoard = new ChessBoard(new HashMap<>());
        Position position = Position.of('a', '1');

        assertThatThrownBy(() -> chessBoard.pieceByPosition(position))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 위치에 존재하는 기물이 없습니다.");
    }

    @Test
    @DisplayName("입력 위치에 기물 반환")
    void pieceByPosition() {
        Position position = Position.of('a', '1');
        Piece piece = new Knight(WHITE);
        ChessBoard chessBoard = new ChessBoard(Map.of(position, piece));

        assertThat(chessBoard.pieceByPosition(position)).isEqualTo(piece);
    }

    @Test
    @DisplayName("각 플레이어의 점수 반환")
    void calcualteScoreStaus() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                Position.of('a', '2'), new WhitePawn(),
                Position.of('a', '3'), new WhitePawn(),
                Position.of('c', '3'), new WhitePawn(),
                Position.of('b', '1'), new Knight(WHITE),
                Position.of('d', '1'), new Queen(WHITE),
                Position.of('e', '1'), new King(WHITE),

                Position.of('a', '7'), new BlackPawn(),
                Position.of('c', '8'), new Bishop(BLACK),
                Position.of('h', '8'), new Rook(BLACK),
                Position.of('e', '8'), new King(BLACK)
        ));
        Map<Color, Double> expected = Map.of(WHITE, 13.5, BLACK, 9.0);

        assertThat(chessBoard.calcualteScoreStatus()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("isFinished")
    @DisplayName("게임이 종료되었는지 확인")
    void isFinished(ChessBoard chessBoard, boolean expected) {
        assertThat(chessBoard.isFinished()).isEqualTo(expected);
    }

    private static Stream<Arguments> isFinished() {
        return Stream.of(
                Arguments.of(
                        new ChessBoard(Map.of(
                                Position.of('e', '1'), new King(WHITE),
                                Position.of('e', '8'), new King(BLACK))), false
                ),
                Arguments.of(
                        new ChessBoard(Map.of(Position.of('e', '1'), new King(WHITE))), true
                )
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"a,8,WHITE,true", "a,7,WHITE,false", "a,8,BLACK,false"})
    @DisplayName("프로모션 상태 여부 확인")
    void isPromotionStatus(char column, char row, Color color, boolean expected) {
        ChessBoard chessBoard = new ChessBoard(Map.of(Position.of(column, row), new WhitePawn()));
        assertThat(chessBoard.isPromotionStatus(color)).isEqualTo(expected);
    }
}
