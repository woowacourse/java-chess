package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import chess.domain.piece.Piece;

public class Board {
    private final Map<Position, Optional<Piece>> board;

    private Board(final Map<Position, Optional<Piece>> board) {
        this.board = board;
    }

    public static Board init() {
        return new Board(initialPlacing());
    }

    private static Map<Position, Optional<Piece>> initialPlacing() {
        return Position.getAllPositions()
            .stream()
            .collect(toMap(Function.identity(), Board::findInitialPieceOn));
    }

    private static Optional<Piece> findInitialPieceOn(Position position) {
        return Piece.getPieces()
            .stream()
            .filter(piece -> piece.canBePlacedOn(position))
            .findAny();
    }

    public Optional<Piece> findPieceBy(Position position) {
        return board.get(position);
    }

    // TODO: 경로 구하는 알고리즘 (직선, 대각선, 박스 형태)
    // 직선(가로, 세로), 대각선, 나이트/폰 의 움직임 Map으로 모아서 구현
    // TODO: 말을 실제로 옮기는 move 마무리
    // TODO: 게임 끝남 판단 (킹이 잡힌 경우)
    // TODO: 점수 구하기 (Board에서 count & 폰의 위치를 판단하는 로직 필요)

}
