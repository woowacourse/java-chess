package chess.domain.game;

import boardStrategy.EmptyBoardStrategy;
import chess.boardstrategy.InitialBoardStrategy;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.EmptyPiece;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static chess.domain.piece.PieceType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class ChessBoardTest {

    private static final EmptyBoardStrategy emptyBoardStrategy = new EmptyBoardStrategy();
    private static final InitialBoardStrategy initialBoardStrategy = new InitialBoardStrategy();
    private ChessBoard chessBoard;

    @BeforeEach
    void setup() {
        chessBoard = new ChessBoard();
    }

    @Test
    void 체스보드가_초기화_되었으면_true를_반환한다() {
        chessBoard.initialize(initialBoardStrategy.generate());
        assertThat(chessBoard.isInitialized())
                .isTrue();
    }

    @Test
    void 체스보드가_초기화_되지않았으면_false를_반환한다() {
        assertThat(chessBoard.isInitialized())
                .isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"EIGHT, BLACK", "ONE, WHITE"})
    void 초기화된_체스보드의_Rank8과_Rank1에_예상한_기물들이_초기화되어_있다(Rank rank, Color color) {
        chessBoard.initialize(initialBoardStrategy.generate());
        Map<Position, Piece> initializedChessBoard = chessBoard.getChessBoard();
        List<PieceType> firstRowPieces = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);

        for(int i = 0 ; i < 8 ; i++) {
            Piece piece = initializedChessBoard.get(Position.of(Column.findColumnByIndex(i+1), rank));
            assertThat(piece.getPieceType())
                    .isEqualTo(firstRowPieces.get(i));
            assertThat(piece.getColor())
                    .isEqualTo(color);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"SEVEN, BLACK", "TWO, WHITE"})
    void 초기화된_체스보드의_Rank7과_Rank2에_예상한_Pawn이_초기화되어_있다(Rank rank, Color color) {
        chessBoard.initialize(initialBoardStrategy.generate());
        Map<Position, Piece> initializedChessBoard = chessBoard.getChessBoard();

        for(Column column :Column.getOrderedColumns()) {
            Piece piece = initializedChessBoard.get(Position.of(column, rank));
            assertThat(piece.getPieceType())
                    .isEqualTo(PAWN);
            assertThat(piece.getColor())
                    .isEqualTo(color);
        }
    }


    @Test
    void 출발위치와_도착위치가_같으면_예외() {
        chessBoard.initialize(initialBoardStrategy.generate());
        Position startPosition = Position.of(Column.D, Rank.TWO);
        Position endPosition = Position.of(Column.D, Rank.TWO);

        assertThatThrownBy(()->chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("제자리로는 이동할 수 없습니다");
    }

    @Test
    void WHIGHT_턴인데_BLACK기물을_이동하면_예외() {
        chessBoard.initialize(initialBoardStrategy.generate());
        Position startPosition = Position.of(Column.D, Rank.SEVEN);
        Position endPosition = Position.of(Column.D, Rank.SIX);

        assertThatThrownBy(()->chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편의 기물을 움직일 수 없습니다");
    }

    @Test
    void BLACK턴인데_WHIGHT기물을_이동하면_예외() {
        chessBoard.initialize(initialBoardStrategy.generate());
        chessBoard.move(Position.of(Column.D, Rank.TWO), Position.of(Column.D, Rank.THREE));
        Position startPosition = Position.of(Column.C, Rank.TWO);
        Position endPosition = Position.of(Column.C, Rank.THREE);

        assertThatThrownBy(()->chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편의 기물을 움직일 수 없습니다");
    }

    /**
     * mock

     * ........ 8
     * ........ 7
     * .....p.. 6
     * ..B..... 5
     * ...b.... 4
     * ........ 3
     * ........ 2
     * ........ 1

     * abcdefgh
     */
    @ParameterizedTest
    @CsvSource(value = {"A,SEVEN","H,EIGHT"})
    void 이동_경로에_기물이_있으면_예외(Column columnToMove, Rank rankToMove) {
        Map<Position, Piece> mockBoard = emptyBoardStrategy.generate();
        mockBoard.put(Position.of(Column.D, Rank.FOUR), new Bishop(Color.WHITE));
        mockBoard.put(Position.of(Column.C, Rank.FIVE), new Bishop(Color.BLACK));
        mockBoard.put(Position.of(Column.F, Rank.SIX), new Pawn(Color.WHITE));

        chessBoard.initialize(mockBoard);

        Position startPosition = Position.of(Column.D, Rank.FOUR);
        Position endPosition = Position.of(columnToMove, rankToMove);

        assertThatThrownBy(()->chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 기물이 있으므로 이동할 수 없습니다");

    }

    @Test
    void 이동하려는_위치에_기물이_없으면_예외() {
        chessBoard.initialize(emptyBoardStrategy.generate());

        Position startPosition = Position.of(Column.D, Rank.FOUR);
        Position endPosition = Position.of(Column.D, Rank.FIVE);

        assertThatThrownBy(()->chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동할 수 있는 기물이 없습니다");
    }

    @Test
    void 조건을_모두_충족하고_기물이_이동가능한_거리이면_기물을_이동한다() {
        chessBoard.initialize(initialBoardStrategy.generate());
        Position startPosition = Position.of(Column.B, Rank.TWO);
        Position endPosition = Position.of(Column.B, Rank.THREE);
        Piece pieceAtStartPoint = chessBoard.getChessBoard().get(startPosition);

        chessBoard.move(startPosition, endPosition);

        assertThat(chessBoard.getChessBoard())
                .containsEntry(endPosition, pieceAtStartPoint)
                .containsEntry(startPosition, EmptyPiece.of());

    }
}