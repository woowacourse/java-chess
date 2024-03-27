package chess.view;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import chess.domain.square.piece.unified.Bishop;
import chess.domain.square.piece.Color;
import chess.domain.square.piece.unified.King;
import chess.domain.square.piece.unified.Knight;
import chess.domain.square.piece.Pawn;
import chess.domain.square.piece.unified.Queen;
import chess.domain.square.piece.unified.Rook;

import java.util.HashMap;
import java.util.Map;

public class OutputView {
    private static final OutputView INSTANCE = new OutputView();
    private static final Map<Square, String> BLACK_SQUARE_VIEWS = Map.of(
            Pawn.from(Color.BLACK), "P",
            Knight.from(Color.BLACK), "N",
            Bishop.from(Color.BLACK), "B",
            Rook.from(Color.BLACK), "R",
            Queen.from(Color.BLACK), "Q",
            King.from(Color.BLACK), "K"
    );
    private static final Map<Square, String> WHITE_SQUARE_VIEWS = Map.of(
            Pawn.from(Color.WHITE), "p",
            Knight.from(Color.WHITE), "n",
            Bishop.from(Color.WHITE), "b",
            Rook.from(Color.WHITE), "r",
            Queen.from(Color.WHITE), "q",
            King.from(Color.WHITE), "k"
    );
    private static final Map<Square, String> squareViews = new HashMap<>();

    static {
        squareViews.putAll(BLACK_SQUARE_VIEWS);
        squareViews.putAll(WHITE_SQUARE_VIEWS);
        squareViews.put(Empty.getInstance(), ".");
    }


    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(Map<Position, Square> squares) {
        for (Rank rank : Rank.values()) {
            printRank(squares, rank);
            System.out.println();
        }
        System.out.println();
    }

    private void printRank(Map<Position, Square> squares, Rank rank) {
        for (File file : File.values()) {
            Square square = squares.get(new Position(rank, file));
            System.out.print(squareViews.get(square));
        }
    }
}
