package chess.model;

import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Rook;
import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("말의 위치를 확인하여 체스판 초기화 테스트")
    void findByPosition() {
        Board board = BoardFactory.create();
        Piece piece = board.get(Position.from("a8"));

        Rook rook = new Rook(Team.BLACK);
        assertThat(piece).isEqualTo(rook);
    }

    @Test
    @DisplayName("판에 남은 King의 개수를 반환한다.")
    void countKingTest() {
        Board board = BoardFactory.create();

        assertThat(board.countKing()).isEqualTo(2);
    }

    @Test
    @DisplayName("체스판에 남은 말들의 점수를 계산한다. - 폰이 전부 가로로 있을 때")
    void getTotalScoreWhenPawnIsWithHorizontalTest() {
        Board board = BoardFactory.create();
        double totalScore = board.getTotalScore(Team.BLACK);
        assertThat(totalScore).isEqualTo(38D);
    }

    @Test
    @DisplayName("체스판에 남은 말들의 점수를 계산한다. - 폰이 세로로 3개, 2개 있고 나머지 3개는 가로로 있을 때")
    void getTotalScoreTest() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.from("a7"), new Pawn(Team.WHITE));
        boardMap.put(Position.from("a6"), new Pawn(Team.WHITE));
        boardMap.put(Position.from("a5"), new Pawn(Team.WHITE));

        boardMap.put(Position.from("b7"), new Pawn(Team.WHITE));
        boardMap.put(Position.from("b6"), new Pawn(Team.WHITE));

        boardMap.put(Position.from("c6"), new Pawn(Team.WHITE));
        boardMap.put(Position.from("d6"), new Pawn(Team.WHITE));
        boardMap.put(Position.from("e6"), new Pawn(Team.WHITE));

        Board board = new Board(boardMap);

        double totalScore = board.getTotalScore(Team.WHITE);
        assertThat(totalScore).isEqualTo(5.5D);
    }
}

