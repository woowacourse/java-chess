package domain;

import domain.piece.BoardGeneratorStub;
import domain.piece.ChessBoardGenerator;
import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.type.Pawn;
import domain.position.Position;
import dto.BoardStatus;
import dto.PieceInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ChessBoardTest {

    @DisplayName("보드 정보를 통해 체스보드를 생성한다")
    @Test
    void createChessBoard() {
        assertThatCode(() -> new ChessBoard(ChessBoardGenerator.getInstance()))
                .doesNotThrowAnyException();
    }

    @DisplayName("b2에 있는 화이트폰을 b3로 이동한다")
    @Test
    void movePawnB2ToB3() {
        // given
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.getInstance());

        // when
        chessBoard.move("b2", "b3");

        // then
        BoardStatus boardStatus = chessBoard.status();
        List<PieceInfo> pieceInfos = boardStatus.pieceInfos();

        assertAll(
                () -> assertThat(pieceInfos.contains(new PieceInfo(1, 2, "p"))).isTrue(),
                () -> assertThat(pieceInfos.contains(new PieceInfo(1, 1, "p"))).isFalse()
        );
    }

    @DisplayName("폰은 앞에 기물이 있으면 이동할 수 없다.")
    @Test
    void cannotMovePawn() {
        // given
        BoardGeneratorStub generatorStub = new BoardGeneratorStub();
        HashMap<Position, Piece> board = new HashMap<>();
        board.put(new Position("b2"), new Pawn(PieceColor.WHITE));
        board.put(new Position("b3"), new Pawn(PieceColor.WHITE));

        generatorStub.setBoard(board);
        ChessBoard chessBoard = new ChessBoard(generatorStub.generate());

        // when & then
        assertThatThrownBy(() -> chessBoard.move("b2", "b3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @DisplayName("Source와 Target이 같으면 이동할 수 없다.")
    @Test
    void isSamePosition() {
        // given
        BoardGeneratorStub generatorStub = new BoardGeneratorStub();
        HashMap<Position, Piece> board = new HashMap<>();
        board.put(new Position("b2"), new Pawn(PieceColor.WHITE));

        generatorStub.setBoard(board);
        ChessBoard chessBoard = new ChessBoard(generatorStub.generate());

        // when & then
        assertThatThrownBy(() -> chessBoard.move("b2", "b2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @DisplayName("Source와 Target이 같은 색이면 이동할 수 없다.")
    @Test
    void isTargetSameColor() {
        // given
        BoardGeneratorStub generatorStub = new BoardGeneratorStub();
        HashMap<Position, Piece> board = new HashMap<>();
        board.put(new Position("b2"), new Pawn(PieceColor.WHITE));
        board.put(new Position("b3"), new Pawn(PieceColor.WHITE));

        generatorStub.setBoard(board);
        ChessBoard chessBoard = new ChessBoard(generatorStub.generate());

        // when & then
        assertThatThrownBy(() -> chessBoard.move("b2", "b3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능합니다.");
    }
}
