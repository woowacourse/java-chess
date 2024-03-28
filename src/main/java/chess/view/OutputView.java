package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.HashMap;
import java.util.Map;

public class OutputView {
    private static final OutputView INSTANCE = new OutputView();
    private static final Map<Piece, String> BLACK_PIECE_VIEWS = Map.of(
            Pawn.from(Color.BLACK), "P",
            Knight.from(Color.BLACK), "N",
            Bishop.from(Color.BLACK), "B",
            Rook.from(Color.BLACK), "R",
            Queen.from(Color.BLACK), "Q",
            King.from(Color.BLACK), "K"
    );
    private static final Map<Piece, String> WHITE_PIECE_VIEWS = Map.of(
            Pawn.from(Color.WHITE), "p",
            Knight.from(Color.WHITE), "n",
            Bishop.from(Color.WHITE), "b",
            Rook.from(Color.WHITE), "r",
            Queen.from(Color.WHITE), "q",
            King.from(Color.WHITE), "k"
    );
    private static final Map<Piece, String> pieceViews = new HashMap<>();

    static {
        pieceViews.putAll(BLACK_PIECE_VIEWS);
        pieceViews.putAll(WHITE_PIECE_VIEWS);
        pieceViews.put(Empty.getInstance(), ".");
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

    public void printChessBoard(Map<Position, Piece> pieces) {
        for (Rank rank : Rank.values()) {
            printRank(pieces, rank);
            System.out.println();
        }
        System.out.println();
    }

    private void printRank(Map<Position, Piece> pieces, Rank rank) {
        for (File file : File.values()) {
            Piece piece = pieces.get(new Position(file, rank));
            System.out.print(pieceViews.get(piece));
        }
    }
}
