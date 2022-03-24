package chess.domain;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.pawn.WhitePawn;
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
        Position position = new Position('a', '1');
        Position target = new Position('a', '2');
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new King(Color.WHITE)));

        assertThatThrownBy(() -> chessBoard.movePiece(position, target, WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되어 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("상대 진영의 기물 이동 시 예외발생")
    void moveOtherTeamPieceException() {
        Position position = new Position('a', '1');
        Position target = new Position('a', '2');
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new King(Color.WHITE),
                new Position('e', '8'), new King(Color.BLACK)));

        assertThatThrownBy(() -> chessBoard.movePiece(position, target, Color.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("상대 진영의 기물을 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,false", "a,2,true"})
    @DisplayName("해당 위치가 비어있는지 확인")
    void isPositionEmpty(char col, char row, boolean expected) {
        Position position = new Position('a', '1');
        Piece piece = new Knight(WHITE);
        ChessBoard chessBoard = new ChessBoard(Map.of(position, piece));

        assertThat(chessBoard.isPositionEmpty(new Position(col, row))).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력 위치에 기물이 없을 때 예외발생")
    void exceptionPieceByPosition() {
        ChessBoard chessBoard = new ChessBoard(new HashMap<>());
        Position position = new Position('a', '1');

        assertThatThrownBy(() -> chessBoard.pieceByPosition(position))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 위치에 존재하는 기물이 없습니다.");
    }

    @Test
    @DisplayName("입력 위치에 기물 반환")
    void pieceByPosition() {
        Position position = new Position('a', '1');
        Piece piece = new Knight(WHITE);
        ChessBoard chessBoard = new ChessBoard(Map.of(position, piece));

        assertThat(chessBoard.pieceByPosition(position)).isEqualTo(piece);
    }

    @Test
    @DisplayName("각 플레이어의 점수 반환")
    void calcualteScoreStaus() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                new Position('a', '2'), new WhiteFirstPawn(),
                new Position('a', '3'), new WhiteFirstPawn(),
                new Position('c', '3'), new WhitePawn(),
                new Position('b', '1'), new Knight(Color.WHITE),
                new Position('d', '1'), new Queen(Color.WHITE),
                new Position('e', '1'), new King(Color.WHITE),

                new Position('a', '7'), new BlackPawn(),
                new Position('c', '8'), new Bishop(Color.BLACK),
                new Position('h', '8'), new Rook(Color.BLACK),
                new Position('e', '8'), new King(Color.BLACK)
        ));
        Map<Color, Double> expected = Map.of(Color.WHITE, 13.5, Color.BLACK, 9.0);

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
                                new Position('e', '1'), new King(Color.WHITE),
                                new Position('e', '8'), new King(Color.BLACK))), false
                ),
                Arguments.of(
                        new ChessBoard(Map.of(new Position('e', '1'), new King(Color.WHITE))), true
                )
        );
    }
}
