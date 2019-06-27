package chess.model;

import chess.model.board.Board;
import chess.model.boardcreatestrategy.CreateStrategy;

import java.util.Arrays;
import java.util.List;

public class ChessGame {
    public static String BLACK_KING_SYMBOL = "K";
    public static String BLACK_QUEEN_SYMBOL = "Q";
    public static String BLACK_BISHOP_SYMBOL = "B";
    public static String BLACK_ROOK_SYMBOL = "R";
    public static String BLACK_KNIGHT_SYMBOL = "N";
    public static String BLACK_PAWN_SYMBOL = "P";
    public static String EMPTY_SYMBOL = "#";
    public static String WHITE_KING_SYMBOL = "k";
    public static String WHITE_QUEEN_SYMBOL = "q";
    public static String WHITE_BISHOP_SYMBOL = "b";
    public static String WHITE_ROOK_SYMBOL = "r";
    public static String WHITE_KNIGHT_SYMBOL = "n";
    public static String WHITE_PAWN_SYMBOL = "p";
    public static String WHITE_TEAM_COLOR = "white";
    public static String BLACK_TEAM_COLOR = "black";
    
    private Board board;
    private int turn;

    public ChessGame(CreateStrategy strategy, int turn) {
        this.turn = turn;
        board = new Board(strategy);
    }

    public void movePiece(String sourceCoordinate, String targetCoordinate) {
        if (isRightTurn(sourceCoordinate)) {
            board.movePiece(Arrays.asList(sourceCoordinate, targetCoordinate));
            turn++;

            return;
        }

        throw new IllegalArgumentException("다음턴에 움직여야합니다.");
    }

    public boolean isRightTurn(String sourceCoordinate) {
        return board.isRightTurn(sourceCoordinate, turn);
    }

    public List<String> convertToList() {
        return board.convertToList();
    }

    public ScoreResult calculateScore() {
        return board.makeScoreResult();
    }

    public boolean checkKingDead() {
        return !board.checkKingAlive();
    }

    public String getCurrentTeam() {
        if (turn % 2 == 0) {
            return BLACK_TEAM_COLOR;
        }

        return WHITE_TEAM_COLOR;
    }
}
