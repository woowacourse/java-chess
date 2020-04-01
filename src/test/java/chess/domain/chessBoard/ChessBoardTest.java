package chess.domain.chessBoard;

import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.Rook;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessBoardTest {
    @Test
    void ChessBoard_MapOfPositionAndChessPiece_GenerateInstance() {
        assertThat(new ChessBoard(ChessBoardFactory.create())).isInstanceOf(ChessBoard.class);
    }

    @Test
    void contains_containsPosition_returnTrue() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());

        assertThat(chessBoard.contains(ChessFile.from('a'), ChessRank.from(1))).isTrue();
    }

    @Test
    void getChessPiece_containValue_returnChessPiece() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());

        assertThat(chessBoard.getChessPiece(ChessFile.from('a'), ChessRank.from(1))).isInstanceOf(Rook.class);
    }

    @Test
    void getPlayerColor_InitialPlayerTurn_returnBLACK() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());

        assertThat(chessBoard.getPlayerColor()).isEqualTo(PieceColor.BLACK);
    }

    @Test
    void getChessBoard_creatBoard_returnChessBoard() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());

        assertThat(chessBoard.getChessBoard()).isInstanceOf(Map.class);
    }

    @ParameterizedTest
    @NullSource
    void move_nullSource_ExceptionThrown(Position source) {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());

        assertThatThrownBy(() -> chessBoard.move(source, Position.of("b5")))
                .isInstanceOf(NullPointerException.class).hasMessage("소스 위치가 null입니다.");
    }

    @ParameterizedTest
    @NullSource
    void move_nullTarget_ExceptionThrown(Position target) {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());

        assertThatThrownBy(() -> chessBoard.move(Position.of("b7"), target))
                .isInstanceOf(NullPointerException.class).hasMessage("타겟 위치가 null입니다.");
    }

    @Test
    void move_MoveSourceToTarget_sourcePieceAtTargetPosition() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());
        Position source = Position.of("b7");
        Position target = Position.of("b5");

        chessBoard.move(source, target);

        assertThat(chessBoard.getChessPiece(ChessFile.from('b'), ChessRank.from(5))).isInstanceOf(Pawn.class);
    }
}
