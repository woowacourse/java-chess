package chess.domain;

import chess.domain.piece.*;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = ChessBoard.generate();
    }

    @DisplayName("ChessBoard 객체 생성 확인")
    @Test
    void 현재_기물_객체_생성() {
        assertThat(chessBoard.getChessBoard().size()).isEqualTo(32);
    }

    @DisplayName("해당 위치에 있는 기물을 찾는다.")
    @Test
    void 해당_위치에_있는_기물_찾기() {
        Piece sourcePiece = chessBoard.findByPosition(Position.of('e', '8'));

        assertThat(sourcePiece).isInstanceOf(King.class);
    }

    @DisplayName("해당 위치에 있는 기물을 찾는다. - 없을 경우 Empty")
    @Test
    void 해당_위치에_있는_기물_찾기_EMPTY() {
        Piece sourcePiece = chessBoard.findByPosition(Position.of('e', '4'));

        assertThat(sourcePiece).isInstanceOf(Empty.class);
    }

    @DisplayName("현재 기물들의 팀별 점수를 계산한다.")
    @Test
    void 팀별_점수_계산() {
        assertThat(chessBoard.sumScoreByColor(Color.WHITE)).isEqualTo(38);
        assertThat(chessBoard.sumScoreByColor(Color.BLACK)).isEqualTo(38);
    }

    @DisplayName("현재 기물들의 팀별 점수를 계산한다. - 세로줄에 같은 색 폰이 있는 경우")
    @Test
    void 팀별_점수_계산_세로줄_같은색_폰() {
        Map<Position, Piece> current = new HashMap<>();
        current.put(Position.of('a', '8'), new Pawn("P", Color.BLACK));
        current.put(Position.of('a', '7'), new Pawn("P", Color.BLACK));
        current.put(Position.of('d', '8'), new Pawn("P", Color.BLACK));
        current.put(Position.of('d', '7'), new Pawn("P", Color.BLACK));
        current.put(Position.of('c', '1'), new Pawn("p", Color.WHITE));
        current.put(Position.of('c', '2'), new Pawn("p", Color.WHITE));

        ChessBoard chessBoard = new ChessBoard(current);

        assertThat(chessBoard.sumScoreByColor(Color.BLACK)).isEqualTo(2);
        assertThat(chessBoard.sumScoreByColor(Color.WHITE)).isEqualTo(1);
    }
}
