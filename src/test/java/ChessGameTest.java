import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.ChessGame;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.dto.BoardDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    @DisplayName("보드의 초기 설정")
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board, new Turn());

        chessGame.start();
    }

    @Test
    @DisplayName("팀 화이트 초기설정 테스트")
    void initializeBoard() {
        BoardDto actualBoard = board.boardDto();
        List<List<String>> expectedBoard = new ArrayList<>();
        expectedBoard.add(Arrays.asList("R", "N", "B", "Q", "K", "B", "N", "R"));
        expectedBoard.add(Arrays.asList("P", "P", "P", "P", "P", "P", "P", "P"));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList("p", "p", "p", "p", "p", "p", "p", "p"));
        expectedBoard.add(Arrays.asList("r", "n", "b", "q", "k", "b", "n", "r"));

        assertThat(expectedBoard).isEqualTo(actualBoard.board());
    }

    @Test
    @DisplayName("킹 이동 테스트(이동 위치에 아군 말이 있는 경우 예외처리)")
    void kingWithInvalidMove() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("e1"), Point.of("e2")))
            .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("킹 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void kingsMoveToInvalidPoint() {
        board.move(Point.of("e2"), Point.of("f3"));
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("e1"), Point.of("e3")))
            .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("킹을 유효한 위치로 이동 테스트")
    void kingWithValidMove() {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board, new Turn() {
            @Override
            public Team now() {
                return Team.WHITE;
            }
        });
        chessGame.start();
        board.move(Point.of("e2"), Point.of("f3"));
        board.move(Point.of("e1"), Point.of("e3"));
        assertThatCode(() -> {
            chessGame.move(Point.of("e3"), Point.of("f4"));
            chessGame.move(Point.of("f4"), Point.of("f5"));
            chessGame.move(Point.of("f5"), Point.of("e6"));
            chessGame.move(Point.of("e6"), Point.of("d6"));
            chessGame.move(Point.of("d6"), Point.of("c5"));
            chessGame.move(Point.of("c5"), Point.of("c4"));
            chessGame.move(Point.of("c4"), Point.of("d3"));
            chessGame.move(Point.of("d3"), Point.of("e3"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("퀸을 유효한 위치로 이동 테스트")
    void queenWithValidMove() {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board, new Turn() {
            @Override
            public Team now() {
                return Team.WHITE;
            }
        });
        chessGame.start();
        board.move(Point.of("d1"), Point.of("d3"));

        assertThatCode(() -> {
            chessGame.move(Point.of("d3"), Point.of("d6"));
            chessGame.move(Point.of("d6"), Point.of("d3"));
            chessGame.move(Point.of("d3"), Point.of("g6"));
            chessGame.move(Point.of("g6"), Point.of("d3"));
            chessGame.move(Point.of("d3"), Point.of("a6"));
            chessGame.move(Point.of("a6"), Point.of("d3"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("퀸 이동 테스트(이동 위치에 아군 말이 있는 경우 예외처리)")
    void queensMoveToInvalidMove() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("d1"), Point.of("d2"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("퀸 이동 테스트(해당 위치로 가는 길이 막힌 경우 예외처리)")
    void queensMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("d1"), Point.of("d3"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("비숍을 유효한 위치로 이동 테스트")
    void bishopWithValidMove() {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board, new Turn() {
            @Override
            public Team now() {
                return Team.WHITE;
            }
        });
        chessGame.start();
        board.move(Point.of("d2"), Point.of("d3"));
        board.move(Point.of("b2"), Point.of("b3"));

        assertThatCode(() -> {
            chessGame.move(Point.of("c1"), Point.of("h6"));
            chessGame.move(Point.of("h6"), Point.of("c1"));
            chessGame.move(Point.of("c1"), Point.of("a3"));
            chessGame.move(Point.of("a3"), Point.of("c1"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("비숍 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void bishopMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("c1"), Point.of("c4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("룩을 유효한 위치로 이동 테스트")
    void rookWithValidMove() {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board, new Turn() {
            @Override
            public Team now() {
                return Team.WHITE;
            }
        });
        chessGame.start();
        board.move(Point.of("a2"), Point.of("a6"));

        assertThatCode(() -> {
            chessGame.move(Point.of("a1"), Point.of("a5"));
            chessGame.move(Point.of("a5"), Point.of("h5"));
            chessGame.move(Point.of("h5"), Point.of("a5"));
            chessGame.move(Point.of("a5"), Point.of("a1"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("룩 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void rookMoveToInvalidPoint() {
        board.move(Point.of("b2"), Point.of("b3"));
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("a1"), Point.of("f6"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("나이트을 유효한 위치로 이동 테스트")
    void knightWithValidMove() {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board, new Turn() {
            @Override
            public Team now() {
                return Team.WHITE;
            }
        });
        chessGame.start();
        assertThatCode(() -> {
            chessGame.move(Point.of("b1"), Point.of("c3"));
            chessGame.move(Point.of("c3"), Point.of("e4"));
            chessGame.move(Point.of("e4"), Point.of("c3"));
            chessGame.move(Point.of("c3"), Point.of("a4"));
            chessGame.move(Point.of("a4"), Point.of("c3"));
            chessGame.move(Point.of("c3"), Point.of("b5"));
            chessGame.move(Point.of("b5"), Point.of("c3"));
            chessGame.move(Point.of("c3"), Point.of("b1"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("나이트 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void knightMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b1"), Point.of("b3"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트")
    void pawnWithValidMove() {
        assertThatCode(() -> {
            chessGame.move(Point.of("b2"), Point.of("b3"));
            chessGame.move(Point.of("b7"), Point.of("b6"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰은 뒤로 이동할 수 없다.")
    void pawnMovesBack() {
        board.move(Point.of("b2"), Point.of("b3"));
        board.move(Point.of("b7"), Point.of("b6"));
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b3"), Point.of("b2"))
        ).withMessage("불가능한 이동입니다.");
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b6"), Point.of("b7"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(첫 이동인 경우 2칸 허용)")
    void pawnWithValidMoveWhenFirstMove() {
        assertThatCode(() -> {
            chessGame.move(Point.of("b2"), Point.of("b4"));
            chessGame.move(Point.of("b7"), Point.of("b5"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(2칸 이동시 앞에 길이 막혀있으면 예외처리)")
    void pawnWithInvalidMoveWhenFirstMove() {
        board.move(Point.of("b2"), Point.of("c3"));
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("c2"), Point.of("c4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰 이동 테스트(첫 이동 외에 2칸 이동을 시도한 경우 예외처리)")
    void pawnMoveToInvalidPointWhenSecondMove() {
        board.move(Point.of("b2"), Point.of("b3"));
        board.move(Point.of("c7"), Point.of("c6"));
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b3"), Point.of("b5"))
        ).withMessage("불가능한 이동입니다.");
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("c6"), Point.of("c4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(대각선에 적이 있는 경우에만 대각선 이동 허용)")
    void pawnWithValidMoveWhenFindEnemy() {
        board.move(Point.of("e7"), Point.of("e5"));
        board.move(Point.of("d2"), Point.of("d4"));

        assertThatCode(() -> chessGame.move(Point.of("d4"), Point.of("e5")))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(뒤쪽 대각선에 적이 있는 경우에는 예외처리)")
    void pawnWithInvalidMoveWhenFindEnemy() {
        board.move(Point.of("e7"), Point.of("e4"));
        board.move(Point.of("d2"), Point.of("d5"));
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("d5"), Point.of("e4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰 이동 테스트(적이 없을 때 대각선으로 이동하려는 경우 예외처리)")
    void pawnMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b2"), Point.of("c3"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("빈 공간을 이동하려는 경우 예외 처리")
    void moveEmptyPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("c3"), Point.of("c4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("자신의 턴이 아닌 말을 이동시키려는 경우 예외처리")
    void moveEnemyPiece() {
        chessGame.move(Point.of("b2"), Point.of("b3"));
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("a2"), Point.of("a3"))
        ).withMessage("불가능한 이동입니다.");
    }
}
