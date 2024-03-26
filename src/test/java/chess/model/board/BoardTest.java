package chess.model.board;

import static chess.model.Fixtures.B1;
import static chess.model.Fixtures.B2;
import static chess.model.Fixtures.B4;
import static chess.model.Fixtures.B5;
import static chess.model.Fixtures.B6;
import static chess.model.Fixtures.B7;
import static chess.model.Fixtures.C1;
import static chess.model.Fixtures.C2;
import static chess.model.Fixtures.C3;
import static chess.model.Fixtures.C4;
import static chess.model.Fixtures.C5;
import static chess.model.Fixtures.E3;
import static chess.model.material.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dto.BoardDto;
import chess.model.CustomBoardFactory;
import chess.model.piece.Piece;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    BoardFactory boardFactory = new InitialBoardFactory();

    @DisplayName("체스판에 기물을 초기화 한다")
    @Test
    void generateInitialBoard() {
        Board board = boardFactory.generate();
        BoardDto boardDto = BoardDto.from(board);
        List<String> actual = combineRanks(boardDto);
        List<String> expected = List.of(
            "RNBQKBNR",
            "PPPPPPPP",
            "........",
            "........",
            "........",
            "........",
            "pppppppp",
            "rnbqkbnr"
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    private List<String> combineRanks(BoardDto boardDto) {
        return boardDto.getRanks()
            .stream()
            .map(rankDto -> String.join("", rankDto.getRank()))
            .toList();
    }

    @DisplayName("기물들의 스냅샷으로 체스판을 생성한다")
    @Test
    void generateCustomBoard() {
        List<String> snapShot = List.of(
            "........",
            "K...P...",
            "PP......",
            "........",
            "...p....",
            ".pn.....",
            ".p.q....",
            "....k..."
        );
        BoardFactory boardFactory = new CustomBoardFactory(snapShot, BLACK);
        Board board = boardFactory.generate();
        BoardDto boardDto = BoardDto.from(board);
        List<String> actual = combineRanks(boardDto);

        assertThat(actual).containsExactlyElementsOf(snapShot);
    }

    @DisplayName("White 차례에 Black 기물 이동 시 예외가 발생한다.")
    @Test
    void notBlackTurn() {
        Board board = boardFactory.generate();
        assertThatThrownBy(() -> board.move(B7, B6))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("지금은 WHITE 차례입니다.");
    }

    @DisplayName("Black 차례에 White 기물 이동 시 예외가 발생한다.")
    @Test
    void notWhiteTurn() {
        Board board = boardFactory.generate();
        board.move(B2, B4);
        assertThatThrownBy(() -> board.move(B4, B5))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("지금은 BLACK 차례입니다.");
    }

    @DisplayName("이동 시 source 위치에 기물이 없으면 예외가 발생한다")
    @Test
    void pieceNotExistsOnSourceCoordinate() {
        Board board = boardFactory.generate();
        assertThatThrownBy(() -> board.move(C4, C5))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("source 위치에 기물이 존재하지 않습니다.");
    }

    @DisplayName("이동 시 target 위치에 내 기물이 있으면 예외가 발생한다")
    @Test
    void pieceExistsOnTargetCoordinate() {
        Board board = boardFactory.generate();
        assertThatThrownBy(() -> board.move(C2, B2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("target 위치에 내 기물이 존재합니다.");
    }

    @DisplayName("이동 경로 상에 기물이 존재하면 예외가 발생한다.")
    @Test
    void obstacleOnRoute() {
        Board board = boardFactory.generate();
        assertThatThrownBy(() -> board.move(C1, E3))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로 상에 다른 기물이 존재합니다.");
    }

    @DisplayName("knight는 이동 경로 상에 기물이 존재해도 예외가 발생하지 않는다")
    @Test
    void obstacleOnRouteButKnight() {
        Board board = boardFactory.generate();
        assertThatCode(() -> board.move(B1, C3))
            .doesNotThrowAnyException();
    }

    @DisplayName("b1에서 c3로 기물이 이동한다")
    @Test
    void moveSuccess() {
        Board board = boardFactory.generate();
        Piece sourcePiece = board.findPiece(B1);

        board.move(B1, C3);

        Piece targetPiece = board.findPiece(C3);
        Piece emptyPiece = board.findPiece(B1);

        assertAll(
            () -> assertThat(targetPiece).isEqualTo(sourcePiece),
            () -> assertThat(emptyPiece.isNone()).isTrue()
        );
    }
}
