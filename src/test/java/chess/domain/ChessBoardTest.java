package chess.domain;

import chess.domain.piece.ChessPiece;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ChessBoardTest {

    @Test
    @DisplayName("체스기물을 이동한다")
    void moveTest() {
        ChessBoard chessBoard = ChessBoard.initialize();
        ChessBoardPosition source = ChessBoardPosition.of(1, 2);
        ChessBoardPosition target = ChessBoardPosition.of(1, 4);
        chessBoard.move(source, target);
        Map<ChessBoardPosition, ChessPiece> mapInfo = chessBoard.getMapInformation();
        assertThat(!mapInfo.containsKey(source) && mapInfo.containsKey(target)).isTrue();
    }

    @Test
    @DisplayName("길이 막혀있으면 예외를 발생한다")
    void moveTest2() {
        ChessBoard chessBoard = ChessBoard.initialize();
        ChessBoardPosition source = ChessBoardPosition.of(1, 1);
        ChessBoardPosition target = ChessBoardPosition.of(1, 4);
        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("sourcePosition에 우리팀이 없으면 예외를 발생한다")
    void moveTest3() {
        ChessBoard chessBoard = ChessBoard.initialize();
        ChessBoardPosition source = ChessBoardPosition.of(1, 7);
        ChessBoardPosition target = ChessBoardPosition.of(1, 5);
        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("targetPosition에 우리팀이 있으면 예외를 발생한다")
    void moveTest4() {
        ChessBoard chessBoard = ChessBoard.initialize();
        ChessBoardPosition source = ChessBoardPosition.of(1, 1);
        ChessBoardPosition target = ChessBoardPosition.of(1, 2);
        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("targetPosition에 적 팀이 있으면 잡고 이동한다")
    void moveTest5() {
        ChessBoard chessBoard = ChessBoard.initialize();
        chessBoard.move(ChessBoardPosition.of(1, 2), ChessBoardPosition.of(1, 4));
        chessBoard.move(ChessBoardPosition.of(2, 7), ChessBoardPosition.of(2, 5));
        chessBoard.move(ChessBoardPosition.of(1, 4), ChessBoardPosition.of(2, 5));
        Map<ChessBoardPosition, ChessPiece> mapInfo = chessBoard.getMapInformation();
        assertThat(mapInfo.keySet().size() == 31
                && chessBoard.pickChessPiece(ChessBoardPosition.of(2, 5))
                .isSameTeam(Team.WHITE)).isTrue();
    }
}
