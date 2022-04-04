package chess.board.piece;

import chess.board.Team;
import chess.board.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @Test
    @DisplayName("말의 위치를 확인하여 체스판 초기화 테스트")
    void findByPosition() {
        Pieces pieces = Pieces.createInit();
        Piece piece = pieces.findByPosition(Position.of('a', '8'));

        Rook rook = new Rook(Position.of('a', '8'), Team.BLACK);
        assertThat(piece).isEqualTo(rook);
    }

    @Test
    @DisplayName("체스판에 남은 말들의 점수를 계산한다. - 폰이 전부 가로로 있을 때")
    void getTotalScoreWhenPawnIsWithHorizontalTest() {
        Pieces pieces = Pieces.from(List.of(
                new Pawn(Position.of('a', '7'), Team.BLACK),
                new Pawn(Position.of('b', '7'), Team.BLACK),
                new Pawn(Position.of('c', '7'), Team.BLACK),
                new Pawn(Position.of('d', '7'), Team.BLACK),
                new Pawn(Position.of('e', '7'), Team.BLACK),
                new Pawn(Position.of('f', '7'), Team.BLACK),
                new Pawn(Position.of('g', '7'), Team.BLACK),
                new Pawn(Position.of('h', '7'), Team.BLACK)
        ));
        double totalScore = pieces.getTotalScore(Team.BLACK);
        assertThat(totalScore).isEqualTo(8D);
    }

    @Test
    @DisplayName("체스판에 남은 말들의 점수를 계산한다. - 폰이 세로로 3개, 2개 있고 나머지 3개는 가로로 있을 때")
    void getTotalScoreTest() {
        Pieces pieces = Pieces.from(List.of(
                new Pawn(Position.of('a', '7'), Team.WHITE),
                new Pawn(Position.of('a', '6'), Team.WHITE),
                new Pawn(Position.of('a', '5'), Team.WHITE),

                new Pawn(Position.of('b', '7'), Team.WHITE),
                new Pawn(Position.of('b', '6'), Team.WHITE),

                new Pawn(Position.of('c', '6'), Team.WHITE),
                new Pawn(Position.of('d', '6'), Team.WHITE),
                new Pawn(Position.of('e', '6'), Team.WHITE)
        ));
        double totalScore = pieces.getTotalScore(Team.WHITE);
        assertThat(totalScore).isEqualTo(5.5D);
    }


    @Test
    @DisplayName("초기 체스판에는 킹의 개수가 2개이다.")
    void countOfKing_2_Test() {
        Pieces pieces = Pieces.createInit();
        assertThat(pieces.countOfKing()).isEqualTo(2);
    }

    @Test
    @DisplayName("체스판에는 킹이 1개 있으면 1개 카운팅한다.")
    void countOfKing_1_Test() {
        Pieces pieces = Pieces.from(
                List.of(
                        new King(Position.of('a', '2'), Team.WHITE),
                        new Pawn(Position.of('b', '4'), Team.WHITE)
                )
        );
        assertThat(pieces.countOfKing()).isEqualTo(1);
    }

}
