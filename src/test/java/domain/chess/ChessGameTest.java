package domain.chess;

import domain.chess.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameTest {

    @DisplayName("king이 잡히면, 게임이 끝난다.")
    @Test
    void game_over_if_king_dead() {
        ChessGame chessGame = new ChessGame(PieceFactory.createPieces());
        chessGame.move(Position.Of(1, 4), Position.Of(3, 4));
        chessGame.move(Position.Of(6, 3), Position.Of(4, 3));
        chessGame.move(Position.Of(0, 5), Position.Of(4, 1));
        chessGame.move(Position.Of(6, 4), Position.Of(4, 4));
        chessGame.move(Position.Of(4, 1), Position.Of(7, 4));
        assertThat(chessGame.isEnd()).isTrue();
    }

    @DisplayName("체스 판의 말들을 통해 점수를 계산한다.")
    @Test
    void chess_game_score_test() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(King.Of("K", Position.Of(0, 1), true));
        pieces.add(Rook.Of("R", Position.Of(0, 2), true));
        pieces.add(Pawn.Of("P", Position.Of(1, 0), true));
        pieces.add(Pawn.Of("P", Position.Of(1, 2), true));
        pieces.add(Bishop.Of("B", Position.Of(1, 3), true));
        pieces.add(Pawn.Of("P", Position.Of(2, 1), true));
        pieces.add(Queen.Of("Q", Position.Of(2, 4), true));

        pieces.add(Knight.Of("n", Position.Of(4, 5), false));
        pieces.add(Queen.Of("q", Position.Of(4, 6), false));
        pieces.add(Pawn.Of("p", Position.Of(5, 5), false));
        pieces.add(Pawn.Of("p", Position.Of(5, 7), false));
        pieces.add(Pawn.Of("p", Position.Of(6, 5), false));
        pieces.add(Pawn.Of("p", Position.Of(6, 6), false));
        pieces.add(Rook.Of("r", Position.Of(7, 4), false));
        pieces.add(King.Of("k", Position.Of(7, 5), false));

        ChessGame chessGame = new ChessGame(pieces);
        Map<Boolean, Score> result = chessGame.piecesScore();
        assertThat(result.get(true)).isEqualTo(new Score(20));
        assertThat(result.get(false)).isEqualTo(new Score(19.5));

    }
}
