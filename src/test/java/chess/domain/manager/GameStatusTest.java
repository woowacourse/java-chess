package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameStatusTest {

    private GameStatus gameStatus;
    private Map<Position, Piece> pieceMap;

    @BeforeEach
    void setUp() {
        pieceMap = new HashMap<>();
        for (Horizontal horizontal : Horizontal.values()) {
            for (Vertical vertical : Vertical.values()) {
                pieceMap.put(Position.of(horizontal, vertical), EmptyPiece.getInstance());
            }
        }
        gameStatus = GameStatus.statusOfBoard(BoardInitializer.initiateBoard());
    }

    @Test
    @DisplayName("객체 생성된다.")
    void createStatusTest() {
        assertThat(gameStatus).isInstanceOf(GameStatus.class);
        assertThat(gameStatus.blackScore()).isEqualTo(38.0d);
        assertThat(gameStatus.whiteScore()).isEqualTo(38.0d);
    }

    @Test
    @DisplayName("각 체스말이 1개씩 있을때 점수 합계는 20.5점이다.")
    void calculateScoreTest() {
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("b1"), Rook.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("c1"), Knight.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("d1"), Bishop.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("e1"), Queen.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("b7"), Rook.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("c7"), Knight.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("d7"), Bishop.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("e7"), Queen.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);

        //when
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //then
        assertThat(gameStatus.blackScore()).isEqualTo(20.5d);
        assertThat(gameStatus.whiteScore()).isEqualTo(20.5d);
    }

    @Test
    @DisplayName("킹이 없으면 점수는 0점으로 처리된다.")
    void calculateScoreIgnoreKing() {
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("b1"), Rook.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("c1"), Knight.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("d1"), Bishop.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("e1"), Queen.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a8"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("b8"), Rook.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("c8"), Knight.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("d8"), Bishop.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("e8"), Queen.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);

        //when
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //then
        assertThat(gameStatus.blackScore()).isEqualTo(0.0d);
        assertThat(gameStatus.whiteScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("퀸을 제외한 모든 체스말 1개씩이 있으면 11.5점이다.")
    void calculateScoreIgnoreQueen() {
        Map<Position, Piece> pieceMap = new HashMap<>();
        for (Horizontal horizontal : Horizontal.values()) {
            for (Vertical vertical : Vertical.values()) {
                pieceMap.put(Position.of(horizontal, vertical), EmptyPiece.getInstance());
            }
        }
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("b1"), Rook.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("c1"), Knight.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("d1"), Bishop.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("b7"), Rook.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("c7"), Knight.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("d7"), Bishop.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);

        //when
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //then
        assertThat(gameStatus.blackScore()).isEqualTo(11.5d);
        assertThat(gameStatus.whiteScore()).isEqualTo(11.5d);
    }

    @Test
    @DisplayName("룩을 제외한 모든 체스말 1개씩이 있으면 15.5점이다.")
    void calculateScoreIgnoreRookTest() {
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("c1"), Knight.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("d1"), Bishop.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("e1"), Queen.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("c7"), Knight.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("d7"), Bishop.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("e7"), Queen.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);

        //when
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //then
        assertThat(gameStatus.blackScore()).isEqualTo(15.5d);
        assertThat(gameStatus.whiteScore()).isEqualTo(15.5d);
    }

    @Test
    @DisplayName("비숍을 제외한 모든 체스말 1개씩이 있으면 17.5점이다.")
    void calculateScoreIgnoreBishopTest() {
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("c1"), Knight.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("d1"), Rook.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("e1"), Queen.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("c7"), Knight.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("d7"), Rook.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("e7"), Queen.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);

        //when
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //then
        assertThat(gameStatus.blackScore()).isEqualTo(17.5d);
        assertThat(gameStatus.whiteScore()).isEqualTo(17.5d);
    }

    @Test
    @DisplayName("나이트를 제외한 모든 체스말 1개씩이 있으면 18.0점이다.")
    void calculateScoreIgnoreKnightTest() {
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("c1"), Bishop.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("d1"), Rook.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("e1"), Queen.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("c7"), Bishop.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("d7"), Rook.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("e7"), Queen.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);

        //when
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //then
        assertThat(gameStatus.blackScore()).isEqualTo(18.0d);
        assertThat(gameStatus.whiteScore()).isEqualTo(18.0d);
    }

    @Test
    @DisplayName("폰을 제외한 모든 체스말 1개씩이 있으면 19.5점이다.")
    void calculateScoreIgnorePawnTest() {
        pieceMap.put(Position.of("a1"), Knight.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("c1"), Bishop.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("d1"), Rook.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("e1"), Queen.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Knight.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("c7"), Bishop.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("d7"), Rook.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("e7"), Queen.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);

        //when
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //then
        assertThat(gameStatus.blackScore()).isEqualTo(19.5d);
        assertThat(gameStatus.whiteScore()).isEqualTo(19.5d);
    }

    @Test
    @DisplayName("하나의 세로열에 색이 같은 폰이 여러개면 폰은 개당 0.5점으로 계산된다.")
    void calculateScoreSameVerticalPawnScoreTest() {
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("a2"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("a8"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);

        //when
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //then
        assertThat(gameStatus.blackScore()).isEqualTo(1.0d);
        assertThat(gameStatus.whiteScore()).isEqualTo(1.0d);
    }

    @Test
    @DisplayName("승자 판단시 점수가 같다면 Owner.NONE 이 반환된다.")
    void judgeWinnerIfSameScoreTest() {
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //when
        Owner owner = gameStatus.judgeWinner();

        //then
        assertThat(owner).isEqualTo(Owner.NONE);
    }

    @Test
    @DisplayName("승자 판단시 White 진영의 점수가 높으면 승자로 Owner.White 가 반환된다.")
    void judgeWinnerIfWhiteWinnerTest() {
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("d1"), Rook.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //when
        Owner owner = gameStatus.judgeWinner();

        //then
        assertThat(owner).isEqualTo(Owner.WHITE);
    }

    @Test
    @DisplayName("승자 판단시 Black 진영의 점수가 높으면 승자로 Owner.BLACK 이 반환된다.")
    void judgeWinnerIfBlackWinnerTest() {
        pieceMap.put(Position.of("a1"), Pawn.getInstanceOf(Owner.WHITE));
        pieceMap.put(Position.of("f1"), King.getInstanceOf(Owner.WHITE));

        pieceMap.put(Position.of("a7"), Pawn.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("d7"), Rook.getInstanceOf(Owner.BLACK));
        pieceMap.put(Position.of("f7"), King.getInstanceOf(Owner.BLACK));
        Board board = new Board(pieceMap);
        GameStatus gameStatus = GameStatus.statusOfBoard(board);

        //when
        Owner owner = gameStatus.judgeWinner();

        //then
        assertThat(owner).isEqualTo(Owner.BLACK);
    }
}