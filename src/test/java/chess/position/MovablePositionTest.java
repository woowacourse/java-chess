package chess.position;

import chess.ChessBoard;
import chess.position.MovablePosition;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MovablePositionTest {
    private static final List<Position> crossVector = List.of(Position.initPosition(1, 0), Position.initPosition(0, 1),
            Position.initPosition(-1, 0), Position.initPosition(0, -1));
    private static final List<Position> diagonalVector = List.of(Position.initPosition(1, 1),
            Position.initPosition(1, -1), Position.initPosition(-1, 1), Position.initPosition(-1, -1));
    private static final List<Position> knightMoveVector = List.of(Position.initPosition(1, 2),
            Position.initPosition(-1, 2), Position.initPosition(1, -2), Position.initPosition(-1, -2),
            Position.initPosition(2, 1), Position.initPosition(-2, 1), Position.initPosition(2, -1),
            Position.initPosition(-2, -1));
    private static final List<Position> blackPawnVector = List.of(Position.initPosition(0, -1));
    private static final List<Position> whitePawnVector = List.of(Position.initPosition(0, 1));

    @Test
    @DisplayName("기물의 현재 포지션을 입력하면 십자가 문양으로 이동한 경로 그룹이 생성된다.")
    void shouldSuccessGenerateCrossPosition() {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.addCrossOrDiagonalPosition(ChessBoard.generateChessBoard(), Position.initPosition(5, 3), crossVector,true);

        assertThat(movablePosition.getMovablePosition()).containsAll(List.of(Position.initPosition(6, 3), Position.initPosition(7, 3), Position.initPosition(8, 3),
                Position.initPosition(4, 3), Position.initPosition(3, 3), Position.initPosition(2, 3), Position.initPosition(1, 3), Position.initPosition(5, 4),
                Position.initPosition(5, 5), Position.initPosition(5, 6), Position.initPosition(5, 7), Position.initPosition(5, 2)
        ));
    }

    @Test
    @DisplayName("나이트의 위치를 입력받으면 해당 나이트가 이동할 수 있는 위치 리스트를 반환한다.")
    void shouldSuccessFindKnightRoute() {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.addKnightPosition(ChessBoard.generateChessBoard(), Position.initPosition(2, 1));

        assertThat(movablePosition.getMovablePosition()).containsAll(List.of(Position.initPosition(1, 3), Position.initPosition(3, 3)
        ));
    }


    @Test
    @DisplayName("흑 팀 폰의 위치를 입력받으면 해당 폰이 이동할 수 있는 위치 리스트를 반환한다.")
    void shouldSuccessFindBlackPawnRoute() {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.addPawnPosition(ChessBoard.generateChessBoard(), Position.initPosition(1, 7), blackPawnVector, 7);

        assertThat(movablePosition.getMovablePosition()).containsAll(List.of(Position.initPosition(1, 6), Position.initPosition(1, 5)
        ));
    }

    @Test
    @DisplayName("백 팀 폰의 위치를 입력받으면 해당 폰이 이동할 수 있는 위치 리스트를 반환한다.")
    void shouldSuccessFindWhitePawnRoute() {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.addPawnPosition(ChessBoard.generateChessBoard(), Position.initPosition(1, 2), whitePawnVector, 2);

        assertThat(movablePosition.getMovablePosition()).containsAll(List.of(Position.initPosition(1, 3), Position.initPosition(1, 4)
        ));
    }
}
