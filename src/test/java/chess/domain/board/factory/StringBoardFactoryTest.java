package chess.domain.board.factory;

import static chess.domain.board.position.File.C;
import static chess.domain.board.position.File.D;
import static chess.domain.board.position.File.E;
import static chess.domain.board.position.Rank.ONE;
import static chess.domain.board.position.Rank.TWO;
import static chess.domain.board.factory.StringBoardFactory.INVALID_FILE_SIZE_MESSAGE;
import static chess.domain.board.factory.StringBoardFactory.INVALID_RANK_SIZE_MESSAGE;
import static chess.domain.board.factory.StringBoardFactory.NOW_ALLOWED_SYMBOL_MESSAGE;
import static chess.domain.piece.PieceTeam.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.position.File;
import chess.domain.board.position.Rank;
import chess.domain.board.position.Position;
import chess.domain.board.position.Positions;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringBoardFactoryTest {

    @Test
    @DisplayName("입력이 비정상이면 예외을 발생시킨다: Rank 크기")
    void exception_rank() {
        List<String> stringChessBoard = List.of(
                "...QK..."
        );

        assertThatThrownBy(() -> StringBoardFactory.getInstance(stringChessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_RANK_SIZE_MESSAGE);
    }

    @Test
    @DisplayName("입력이 비정상이면 예외을 발생시킨다: File 크기")
    void exception_file() {
        List<String> stringChessBoard = List.of(
                "........",
                "........",
                "........",
                "........",
                "........",
                "........",
                "........",
                "........."
        );

        assertThatThrownBy(() -> StringBoardFactory.getInstance(stringChessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_FILE_SIZE_MESSAGE);

    }

    @Test
    @DisplayName("입력이 비정상이면 예외을 발생시킨다: 허용하지 않는 기물 심볼")
    void exception_symbol() {
        List<String> stringChessBoard = List.of(
                "RNBQKBNR",
                "PPPPPPPP",
                "........",
                "........",
                ".....A..",
                "........",
                "........",
                "........"
        );

        assertThatThrownBy(() -> StringBoardFactory.getInstance(stringChessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOW_ALLOWED_SYMBOL_MESSAGE);
    }

    @Test
    @DisplayName("정상적인 입력의 경우 예외가 생기지 않으며 체스보드를 만들 수 있다")
    void can_make_chess_map() {
        List<String> stringChessBoard = List.of(
                "...QK...",
                "..P.....",
                "........",
                "........",
                "........",
                "........",
                "........",
                "........"
        );
        BoardFactory boardFactory = StringBoardFactory.getInstance(stringChessBoard);
        Map<Position, Piece> board = boardFactory.create();

        assertThat(findPiece(board, D, ONE)).isEqualTo(new Queen(BLACK));
        assertThat(findPiece(board, E, ONE)).isEqualTo(new King(BLACK));
        assertThat(findPiece(board, C, TWO)).isEqualTo(new Pawn(BLACK));
    }

    private Piece findPiece(Map<Position, Piece> board, File file, Rank rank) {
        return board.get(Positions.findPositionBy(file, rank));
    }
}
