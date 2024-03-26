package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("생성 테스트")
    void create() {
        assertThatCode(ChessBoardFactory::makeChessBoard)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("현재 체스판 위에 존재하는 모든 기물을 구할 수 있다.")
    void findAllPieces() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 1);
        positionPiece.put(sourcePosition, Queen.colorOf(Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(positionPiece);

        List<Piece> allPieces = chessBoard.findAllPieces();

        assertThat(allPieces.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("대상 위치에 존재하는 기물을 타켓 위치로 옮긴다.")
    void movePiece() {
        ChessBoard chessBoard = ChessBoardFactory.makeChessBoard();
        Position source = Position.of('b', 2);
        Position target = Position.of('b', 3);
        Piece pieceBeforeMove = chessBoard.findPieceByPosition(source);

        chessBoard.move(source, target);

        Piece pieceAfterMove = chessBoard.findPieceByPosition(target);
        assertThat(pieceBeforeMove).isEqualTo(pieceAfterMove);
    }

    @Test
    @DisplayName("기물이 이동하면 기존 위치에는 기물이 존재하지 않게 된다.")
    void noPieceOnSourcePositionWhenPieceMoves() {
        ChessBoard chessBoard = ChessBoardFactory.makeChessBoard();
        Position source = Position.of('b', 2);
        Position target = Position.of('b', 3);

        chessBoard.move(source, target);

        Piece pieceAtSourcePositionAfterMove = chessBoard.findPieceByPosition(source);
        assertThat(pieceAtSourcePositionAfterMove.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("이동하려는 지점에 같은 팀이 존재하는 경우 예외가 발생한다")
    void cannotMoveIfAllyAlreadyExist() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 1);
        positionPiece.put(sourcePosition, King.colorOf(Color.BLACK));
        Position targetPosition = Position.of('a', 2);
        positionPiece.put(targetPosition, Pawn.colorOf(Color.BLACK));

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동하려는 위치에 아군 기물이 존재합니다.");
    }

    @Test
    @DisplayName("기물이 이동했음에도 타겟 위치까지 도달하지 못하는 경우 예외가 발생한다.")
    void cannotReachTarget() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 1);
        positionPiece.put(sourcePosition, King.colorOf(Color.BLACK));
        Position targetPosition = Position.of('a', 3);
        positionPiece.put(targetPosition, Empty.of());

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택한 기물은 해당 위치에 도달할 수 없습니다.");
    }

    @Test
    @DisplayName("나이트는 기물을 넘어 이동할 수 있다.")
    void knightCanJumpOverPiece() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 1);
        positionPiece.put(sourcePosition, Knight.colorOf(Color.BLACK));
        Position obstaclePosition = Position.of('a', 2);
        positionPiece.put(obstaclePosition, Pawn.colorOf(Color.BLACK));
        Position targetPosition = Position.of('b', 3);
        positionPiece.put(targetPosition, Empty.of());

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("선택한 기물이 이동할 수 없는 방향으로 이동을 요청하면 예외가 발생한다.")
    void invalidDirection() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 1);
        positionPiece.put(sourcePosition, Rook.colorOf(Color.BLACK));
        Position targetPosition = Position.of('c', 3);
        positionPiece.put(targetPosition, Empty.of());

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택한 기물이 이동할 수 없는 방향입니다.");
    }

    @Test
    @DisplayName("target 지점에 적팀 기물이 존재하는 경우, 덮어씌운다")
    void kill() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 1);
        Rook sourcePiece = Rook.colorOf(Color.BLACK);
        positionPiece.put(sourcePosition, sourcePiece);
        Position targetPosition = Position.of('a', 3);
        Rook targetPiece = Rook.colorOf(Color.WHITE);
        positionPiece.put(targetPosition, targetPiece);

        positionPiece.put(Position.of('a', 2), Empty.of());

        ChessBoard chessBoard = new ChessBoard(positionPiece);
        chessBoard.move(sourcePosition, targetPosition);

        Piece pieceAtTargetPosition = chessBoard.findPieceByPosition(targetPosition);
        assertThat(pieceAtTargetPosition).isEqualTo(sourcePiece);
    }

    @Test
    @DisplayName("체스판의 모서리까지 이동할 수 있다.")
    void fromEdgeToEdge() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 1);
        positionPiece.put(sourcePosition, Queen.colorOf(Color.BLACK));

        for (int i = 2; i <= 8; i++) {
            Position emptyPosition = Position.of('a', i);
            positionPiece.put(emptyPosition, Empty.of());
        }

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        Position targetPosition = Position.of('a', 8);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰은 시작 지점에 있을 때 앞으로 두 칸 전진할 수 있다.")
    void pawnInitialMove() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 2);
        Position emptyPosition = Position.of('a', 3);
        Position targetPosition = Position.of('a', 4);
        positionPiece.put(sourcePosition, Pawn.colorOf(Color.WHITE));
        positionPiece.put(emptyPosition, Empty.of());
        positionPiece.put(targetPosition, Empty.of());

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰은 시작 지점에 있더라도 경로 상에 기물이 존재하는 경우, 이동할 수 없다.")
    void pawnInitialMoveWithObstacleOnRoute() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 2);
        Position emptyPosition = Position.of('a', 3);
        Position targetPosition = Position.of('a', 4);
        positionPiece.put(sourcePosition, Pawn.colorOf(Color.WHITE));
        positionPiece.put(emptyPosition, Pawn.colorOf(Color.BLACK));
        positionPiece.put(targetPosition, Empty.of());

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 시작 지점에 있더라도 도착 지점에 기물이 존재한다면 이동할 수 없다.")
    void pawnInitialMoveWithObstacleOnTargetPosition() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 2);
        Position emptyPosition = Position.of('a', 3);
        Position targetPosition = Position.of('a', 4);
        positionPiece.put(sourcePosition, Pawn.colorOf(Color.WHITE));
        positionPiece.put(emptyPosition, Empty.of());
        positionPiece.put(targetPosition, Pawn.colorOf(Color.BLACK));

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 도착 지점에 적 기물이 없다면 대각선으로 이동할 수 없다.")
    void pawnCannotMoveDiagonalWithoutEnemy() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 2);
        Position targetPosition = Position.of('b', 3);
        positionPiece.put(sourcePosition, Pawn.colorOf(Color.WHITE));
        positionPiece.put(targetPosition, Empty.of());

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 도착 지점에 적 기물이 있다면 대각선으로 이동할 수 있다.")
    void pawnCanMoveDiagonalWithEnemy() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 2);
        Position targetPosition = Position.of('b', 3);
        positionPiece.put(sourcePosition, Pawn.colorOf(Color.WHITE));
        positionPiece.put(targetPosition, Pawn.colorOf(Color.BLACK));

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰은 시작 지점에 위치하더라도 대각선 방향으로는 두 칸 전진할 수 없다.")
    void pawnCantMoveInitialDiagonal() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of('a', 2);
        Position emptyPosition = Position.of('b', 3);
        Position targetPosition = Position.of('c', 4);
        positionPiece.put(sourcePosition, Pawn.colorOf(Color.WHITE));
        positionPiece.put(emptyPosition, Empty.of());
        positionPiece.put(targetPosition, Pawn.colorOf(Color.BLACK));

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
