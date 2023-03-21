package chess.domain.validateMove;

import chess.domain.ChessGame;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class knightMoveValidatorTest {
    @DisplayName("나이트는 앞에 기물이 있어도")
    @Test
    void pawnTrueTest() {
        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();
        Square source = new Square(File.B, Rank.ONE);
        Square target = new Square(File.A, Rank.THREE);
        ValidateMove validateMove = new SourceMoveValidator();
        assertThat(validateMove.validate(new ValidateData(source, target, chessboard))).isTrue();
    }

}
