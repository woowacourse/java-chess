package chess.domain;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    public static final Position A2 = new Position(File.A, Rank.TWO);
    public static final Position A3 = new Position(File.A, Rank.THREE);

    @DisplayName("턴을 진행하면 턴이 바뀐다")
    @Test
    void playTurn_change() {
        ChessGame chessGame = new ChessGame();
        final var source = A2;
        final var target = A3;
        chessGame.playTurn(source, target);
        assertThat(chessGame.getTurn()).isEqualTo(Color.BLACK);
    }
}
