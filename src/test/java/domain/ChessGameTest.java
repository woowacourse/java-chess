package domain;

import domain.piece.objects.*;
import domain.piece.position.Position;
import domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameTest {

    @DisplayName("king이 잡히면, 게임이 끝난다.")
    @Test
    void game_over_if_king_dead() {
        ChessGame chessGame = new ChessGame();
        chessGame.start(PieceFactory.createPieces());
        chessGame.move(Position.of("e2"), Position.of("e4"));
        chessGame.move(Position.of("e7"), Position.of("e5"));
        chessGame.move(Position.of("e1"), Position.of("e2"));
        chessGame.move(Position.of("f8"), Position.of("c5"));
        chessGame.move(Position.of("e2"), Position.of("e3"));
        chessGame.move(Position.of("c5"), Position.of("e3"));
        assertThat(chessGame.isEnd()).isTrue();
    }

    @DisplayName("체스 판의 말들을 통해 점수를 계산한다.")
    @Test
    void chess_game_score_test() {
        Map<Position, Piece> pieces = new HashMap<Position, Piece>() {{
            put(Position.of("b8"), King.of("K", true));
            put(Position.of("c8"), Rook.of("R", true));
            put(Position.of("a7"), Pawn.of("P", true));
            put(Position.of("c7"), Pawn.of("P", true));
            put(Position.of("d7"), Bishop.of("B", true));
            put(Position.of("b6"), Pawn.of("P", true));
            put(Position.of("e6"), Queen.of("Q", true));

            put(Position.of("f4"), Knight.of("n", false));
            put(Position.of("g4"), Queen.of("q", false));
            put(Position.of("f3"), Pawn.of("p", false));
            put(Position.of("h3"), Pawn.of("p", false));
            put(Position.of("f2"), Pawn.of("p", false));
            put(Position.of("g2"), Pawn.of("p", false));
            put(Position.of("e1"), Rook.of("r", false));
            put(Position.of("f1"), King.of("k", false));
        }};
        ChessGame chessGame = new ChessGame();
        chessGame.start(pieces);
        assertThat(chessGame.blackScore()).isEqualTo(20);
        assertThat(chessGame.whiteScore()).isEqualTo(19.5);
    }
}
