package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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
        Position sourcePosition = Position.of(File.A, Rank.ONE);
        positionPiece.put(sourcePosition, Queen.of(Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(positionPiece);

        List<Piece> allPieces = chessBoard.findAllPieces();

        assertThat(allPieces.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("대상 위치에 존재하는 기물을 타켓 위치로 옮긴다.")
    void movePiece() {
        ChessBoard chessBoard = ChessBoardFactory.makeChessBoard();
        Position source = Position.of(File.B, Rank.TWO);
        Position target = Position.of(File.B, Rank.THREE);
        Piece pieceBeforeMove = chessBoard.findPieceByPosition(source);

        chessBoard.move(source, target);

        Piece pieceAfterMove = chessBoard.findPieceByPosition(target);
        assertThat(pieceBeforeMove).isEqualTo(pieceAfterMove);
    }

    @Test
    @DisplayName("기물이 이동하면 기존 위치에는 기물이 존재하지 않게 된다.")
    void noPieceOnSourcePositionWhenPieceMoves() {
        ChessBoard chessBoard = ChessBoardFactory.makeChessBoard();
        Position source = Position.of(File.B, Rank.TWO);
        Position target = Position.of(File.B, Rank.THREE);

        chessBoard.move(source, target);

        Piece pieceAtSourcePositionAfterMove = chessBoard.findPieceByPosition(source);
        assertThat(pieceAtSourcePositionAfterMove).isNull();
    }

    @Test
    @DisplayName("이동하려는 지점에 같은 팀이 존재하는 경우 예외가 발생한다")
    void cannotMoveIfAllyAlreadyExist() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of(File.A, Rank.ONE);
        positionPiece.put(sourcePosition, King.of(Color.BLACK));
        Position targetPosition = Position.of(File.A, Rank.TWO);
        positionPiece.put(targetPosition, Pawn.of(Color.BLACK));

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동하려는 위치에 아군 기물이 존재합니다.");
    }

    @Test
    @DisplayName("기물이 이동했음에도 타겟 위치까지 도달하지 못하는 경우 예외가 발생한다.")
    void cannotReachTarget() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of(File.A, Rank.ONE);
        positionPiece.put(sourcePosition, King.of(Color.BLACK));
        Position targetPosition = Position.of(File.A, Rank.THREE);
        positionPiece.put(targetPosition, null);

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택한 기물은 해당 위치에 도달할 수 없습니다.");
    }

    @Test
    @DisplayName("나이트는 기물을 넘어 이동할 수 있다.")
    void knightCanJumpOverPiece() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of(File.A, Rank.ONE);
        positionPiece.put(sourcePosition, Knight.of(Color.BLACK));
        Position obstaclePosition = Position.of(File.A, Rank.TWO);
        positionPiece.put(obstaclePosition, Pawn.of(Color.BLACK));
        Position targetPosition = Position.of(File.B, Rank.THREE);
        positionPiece.put(targetPosition, null);

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("선택한 기물이 이동할 수 없는 방향으로 이동을 요청하면 예외가 발생한다.")
    void invalidDirection() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of(File.A, Rank.ONE);
        positionPiece.put(sourcePosition, Rook.of(Color.BLACK));
        Position targetPosition = Position.of(File.C, Rank.THREE);
        positionPiece.put(targetPosition, null);

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택한 기물이 이동할 수 없는 방향입니다.");
    }

    @Test
    @DisplayName("target 지점에 적팀 기물이 존재하는 경우, 덮어씌운다")
    void kill() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of(File.A, Rank.ONE);
        Rook sourcePiece = Rook.of(Color.BLACK);
        positionPiece.put(sourcePosition, sourcePiece);
        Position targetPosition = Position.of(File.A, Rank.THREE);
        Rook targetPiece = Rook.of(Color.WHITE);
        positionPiece.put(targetPosition, targetPiece);

        positionPiece.put(Position.of(File.A, Rank.TWO), null);

        ChessBoard chessBoard = new ChessBoard(positionPiece);
        chessBoard.move(sourcePosition, targetPosition);

        Piece pieceAtTargetPosition = chessBoard.findPieceByPosition(targetPosition);
        assertThat(pieceAtTargetPosition).isEqualTo(sourcePiece);
    }

    @Test
    @DisplayName("체스판의 모서리까지 이동할 수 있다.")
    void fromEdgeToEdge() {
        Map<Position, Piece> positionPiece = new LinkedHashMap<>();
        Position sourcePosition = Position.of(File.A, Rank.ONE);
        positionPiece.put(sourcePosition, Queen.of(Color.BLACK));
        Position targetPosition = Position.of(File.A, Rank.EIGHT);
        positionPiece.put(targetPosition, null);

        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatCode(() -> chessBoard.move(sourcePosition, targetPosition))
                .doesNotThrowAnyException();
    }
}
