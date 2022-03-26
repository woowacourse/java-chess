package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.chessboard.BoardGenerator;
import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardGenerator;
import domain.piece.BlackPawn;
import domain.piece.Piece;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.position.Rank;
import domain.position.File;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @Test
    @DisplayName("각 기물들은 이동할 수 있는 위치에 같은 색 말이 있다면 이동할 수 없다.")
    void runExceptionSameTargetSameColor() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.A, Rank.TWO);

        assertThatThrownBy(() -> chessBoard.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("각 기물들은 이동할 수 있는 위치에 다른 색 말이 있다면 이동할 수 있다.")
    void MoveDifferentColor() {
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                board.put(Position.of(File.A, Rank.ONE), new Rook(Player.WHITE));
                board.put(Position.of(File.A, Rank.SEVEN), new BlackPawn(Player.BLACK));
                return board;
            }
        });

        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.A, Rank.SEVEN);

        chessBoard.move(source, target);

        assertThat(chessBoard.getSymbol(source)).isEqualTo(".");
        assertThat(chessBoard.getSymbol(target)).isEqualTo("r");
    }

    @Test
    @DisplayName("각 기물들은 Target 위치까지 가는 경로에 말이 있다면 그 이상 이동할 수 없다.")
    void canNotMoveMore() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.A, Rank.SEVEN);

        assertThatThrownBy(() -> chessBoard.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Knight는 다른 기물을 뛰어 넘어 이동할 수 있다.")
    void jumpPiece_Knight() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        Position source = Position.of(File.B, Rank.ONE);
        Position target = Position.of(File.C, Rank.THREE);

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    @Test
    @DisplayName("Pawn은 직진으로만 이동이 가능하며 목적지에 기물이 없어야만 이동할 수 있다.")
    void movePawnRule_success() {
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                board.put(Position.of(File.C, Rank.TWO), new WhitePawn(Player.WHITE));
                return board;
            }
        });
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.C, Rank.THREE);

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    @Test
    @DisplayName("Pawn은 직진으로만 이동이 가능하며 목적지에 기물이 존재한다면 이동할 수 없다.")
    void movePawnRule_fail() {
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                board.put(Position.of(File.C, Rank.TWO), new WhitePawn(Player.WHITE));
                board.put(Position.of(File.C, Rank.THREE), new WhitePawn(Player.WHITE));
                return board;
            }
        });
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.C, Rank.THREE);

        assertThatThrownBy(() -> chessBoard.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Pawn은 대각선(앞방향)으로만 공격이 가능하며 목적지에 상대편의 기물이 존재해야만 공격할 수 있다.")
    void attackPawnRule_success() {
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                board.put(Position.of(File.C, Rank.TWO), new WhitePawn(Player.WHITE));
                board.put(Position.of(File.D, Rank.THREE), new BlackPawn(Player.BLACK));
                return board;
            }
        });
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.D, Rank.THREE);

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    @Test
    @DisplayName("Pawn은 대각선(앞방향)으로만 공격이 가능하며 목적지에 상대편의 기물이 존재하지 않는다면 공격할 수 없다.")
    void attackPawnRule_fail() {
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                board.put(Position.of(File.C, Rank.TWO), new WhitePawn(Player.WHITE));
                return board;
            }
        });
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.D, Rank.THREE);

        assertThatThrownBy(() -> chessBoard.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Pawn은 첫 번째 이동이면서 이동 위치 및 경로에 기물이 없다면 2칸 이동할 수 있다.")
    void moveTwoSpacePawn_FirstTime_success() {
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                board.put(Position.of(File.C, Rank.TWO), new WhitePawn(Player.WHITE));
                return board;
            }
        });
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.C, Rank.FOUR);

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    @Test
    @DisplayName("Pawn은 첫 번째 이동이면서 이동 위치에 기물이 있다면 2칸 이동할 수 없다.")
    void moveTwoSpacePawn_FirstTime_Target_fail() {
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                board.put(Position.of(File.C, Rank.TWO), new WhitePawn(Player.WHITE));
                board.put(Position.of(File.C, Rank.FOUR), new WhitePawn(Player.WHITE));
                return board;
            }
        });
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.C, Rank.FOUR);

        assertThatThrownBy(() -> chessBoard.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Pawn은 첫 번째 이동이면서 이동 경로에 기물이 있다면 2칸 이동할 수 없다.")
    void moveTwoSpacePawn_FirstTime_WayPoint_fail() {
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                board.put(Position.of(File.C, Rank.TWO), new WhitePawn(Player.WHITE));
                board.put(Position.of(File.C, Rank.THREE), new WhitePawn(Player.WHITE));
                return board;
            }
        });
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.C, Rank.FOUR);

        assertThatThrownBy(() -> chessBoard.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
