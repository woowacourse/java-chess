package chess.service;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.chesspiece.*;
import chess.dto.ChessBoardDto;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChessAssembler {
    private static final int MIN_LINE = 0;
    private static final int MAX_LINE = 7;
    private static final String KING = "K";
    private static final String QUEEN = "Q";
    private static final String ROOK = "R";
    private static final String BISHOP = "B";
    private static final String KNIGHT = "N";
    private static final String PAWN = "P";
    private static final String BLANK = "S";

    private static Map<Type, Function<ChessPiece, String>> chessPieceFunctionMap = new HashMap<>();

    static {
        chessPieceFunctionMap.put(King.class, (chessPiece) -> toLowerCase(chessPiece, KING));
        chessPieceFunctionMap.put(Queen.class, (chessPiece) -> toLowerCase(chessPiece, QUEEN));
        chessPieceFunctionMap.put(Rook.class, (chessPiece) -> toLowerCase(chessPiece, ROOK));
        chessPieceFunctionMap.put(Bishop.class, (chessPiece) -> toLowerCase(chessPiece, BISHOP));
        chessPieceFunctionMap.put(Knight.class, (chessPiece) -> toLowerCase(chessPiece, KNIGHT));
        chessPieceFunctionMap.put(Pawn.class, (chessPiece) -> toLowerCase(chessPiece, PAWN));
        chessPieceFunctionMap.put(Blank.class, (chessPiece) -> toLowerCase(chessPiece, BLANK));
    }

    private static String toLowerCase(ChessPiece chessPiece, String symbol) {
        if (chessPiece.isSameTeam(Team.WHITE)) {
            return symbol.toLowerCase();
        }
        return symbol;
    }

    public static ChessBoardDto toDto(ChessBoard chessBoard) {
        List<String> rows = new ArrayList<>();
        Map<Position, ChessPiece> board = chessBoard.getChessBoard();

        for (int i = MIN_LINE; i <= MAX_LINE; i++) {
            StringBuilder row = new StringBuilder();
            appendChessPiece(board, i, row);
            rows.add(row.toString());
        }

        return new ChessBoardDto(rows, chessBoard.isGameOver());
    }

    private static void appendChessPiece(Map<Position, ChessPiece> board, int i, StringBuilder row) {
        for (int j = 0; j < 8; j++) {
            ChessPiece chessPiece = board.get(Position.of(i, j));
            row.append(chessPieceFunctionMap.get(chessPiece.getClass()).apply(chessPiece));
        }
    }
}

