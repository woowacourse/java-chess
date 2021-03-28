package domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {

    @DisplayName("체스판 초기화시 2개의 King이 올바른 자리에 위치한다.")
    @Test
    void initKings() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("e8"))).isInstanceOf(King.class);
        assertThat(board.piece(new Position("e8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("e1"))).isInstanceOf(King.class);
        assertThat(board.piece(new Position("e1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 2개의 퀸이 올바른 자리에 위치한다.")
    @Test
    void initQueen() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("d8"))).isInstanceOf(Queen.class);
        assertThat(board.piece(new Position("d8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("d1"))).isInstanceOf(Queen.class);
        assertThat(board.piece(new Position("d1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 4개의 Bishop이 올바른 자리에 위치한다.")
    @Test
    void initBishop() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("c8"))).isInstanceOf(Bishop.class);
        assertThat(board.piece(new Position("c8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("f8"))).isInstanceOf(Bishop.class);
        assertThat(board.piece(new Position("f8")).isBlack()).isTrue();

        assertThat(board.piece(new Position("c1"))).isInstanceOf(Bishop.class);
        assertThat(board.piece(new Position("c1")).isBlack()).isFalse();
        assertThat(board.piece(new Position("f1"))).isInstanceOf(Bishop.class);
        assertThat(board.piece(new Position("f1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 4개의 Knight가 올바른 자리에 위치한다.")
    @Test
    void initKnight() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("b8"))).isInstanceOf(Knight.class);
        assertThat(board.piece(new Position("b8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("g8"))).isInstanceOf(Knight.class);
        assertThat(board.piece(new Position("g8")).isBlack()).isTrue();

        assertThat(board.piece(new Position("b1"))).isInstanceOf(Knight.class);
        assertThat(board.piece(new Position("b1")).isBlack()).isFalse();
        assertThat(board.piece(new Position("g1"))).isInstanceOf(Knight.class);
        assertThat(board.piece(new Position("g1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 4개의 Rook이 올바른 자리에 위치한다.")
    @Test
    void initRook() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("a8"))).isInstanceOf(Rook.class);
        assertThat(board.piece(new Position("a8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("h8"))).isInstanceOf(Rook.class);
        assertThat(board.piece(new Position("h8")).isBlack()).isTrue();

        assertThat(board.piece(new Position("a1"))).isInstanceOf(Rook.class);
        assertThat(board.piece(new Position("a1")).isBlack()).isFalse();
        assertThat(board.piece(new Position("h1"))).isInstanceOf(Rook.class);
        assertThat(board.piece(new Position("h1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 16개의 Pawn이 올바른 자리에 위치한다.")
    @ParameterizedTest
    @CsvSource(value = {"a7,true", "b7,true", "c7,true", "d7,true", "e7,true", "f7,true", "g7,true", "h7,true",
        "a2,false", "b2,false", "c2,false", "d2,false", "e2,false", "f2,false", "g2,false", "h2,false"}, delimiter = ',')
    void initPawn(String pawnInitialPosition, boolean isBlack) {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position(pawnInitialPosition))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position(pawnInitialPosition)).isBlack()).isEqualTo(isBlack);
    }

    @DisplayName("점수 계산 테스트")
    @Test
    void calculateChessScoreTest() {
        Board board = new Board();

        board.put(new Position("b8"), new King(true));
        board.put(new Position("c8"), new Rook(true));
        board.put(new Position("a7"), new Pawn(true));
        board.put(new Position("c7"), new Pawn(true));
        board.put(new Position("d7"), new Bishop(true));
        board.put(new Position("b6"), new Pawn(true));
        board.put(new Position("e6"), new Queen(true));

        board.put(new Position("f4"), new Knight(false));
        board.put(new Position("g4"), new Queen(false));
        board.put(new Position("f3"), new Pawn(false));
        board.put(new Position("h3"), new Pawn(false));
        board.put(new Position("f2"), new Pawn(false));
        board.put(new Position("g2"), new Pawn(false));
        board.put(new Position("e1"), new Rook(false));
        board.put(new Position("f1"), new King(false));

        assertThat(board.piecesScore(true)).isEqualTo(new Score(20));
        assertThat(board.piecesScore(false)).isEqualTo(new Score(19.5));
    }


    @DisplayName("기물은 같은 팀이 있는 곳으로 이동할 수 없다.")
    @Test
    void moveSameColorPiecePositionTest() {
        Board board = new Board();
        King blackKing = new King(true);
        Rook blackRook = new Rook(true);
        Position source = new Position(3,3);
        Position target = new Position(4,3);

        board.put(source, blackKing);
        board.put(target, blackRook);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 같은 팀 기물이 존재하는 위치로 이동할 수 없습니다.");
    }

    @DisplayName("기물은 체스판 범위가 넘어서는 곳으로 이동할 수 없다.")
    @Test
    void moveOverChessBoardTest() {
        Board board = new Board();
        King blackKing = new King(true);
        Position source = new Position(3,3);
        Position target = new Position(10,10);

        board.put(source, blackKing);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 해당 기물은 target 위치로 이동할 수 없습니다.");
    }

    @DisplayName("source 위치에 기물이 없으면 이동할 수 없다.")
    @Test
    void emptySourceTest() {
        Board board = new Board();
        King blackKing = new King(true);
        Position source = new Position(1,1);
        Position target = new Position(4,3);

        board.put(target, blackKing);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] source 위치에 기물이 없습니다.");
    }
}
