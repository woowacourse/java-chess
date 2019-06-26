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
    private static Map<Type, Function<ChessPiece, String>> chessPieceFunctionMap = new HashMap<>();

    static {
        chessPieceFunctionMap.put(King.class, (chessPiece) -> toLowerCase(chessPiece, "K"));
        chessPieceFunctionMap.put(Queen.class, (chessPiece) -> toLowerCase(chessPiece, "Q"));
        chessPieceFunctionMap.put(Rook.class, (chessPiece) -> toLowerCase(chessPiece, "R"));
        chessPieceFunctionMap.put(Bishop.class, (chessPiece) -> toLowerCase(chessPiece, "B"));
        chessPieceFunctionMap.put(Knight.class, (chessPiece) -> toLowerCase(chessPiece, "N"));
        chessPieceFunctionMap.put(Pawn.class, (chessPiece) -> toLowerCase(chessPiece, "P"));
        chessPieceFunctionMap.put(Blank.class, (chessPiece) -> toLowerCase(chessPiece, "S"));
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

        for (int i = 0; i < 8; i++) {
            StringBuilder row = new StringBuilder();
            appendChessPiece(board, i, row);
            rows.add(row.toString());
        }

        return new ChessBoardDto(rows);
    }

    private static void appendChessPiece(Map<Position, ChessPiece> board, int i, StringBuilder row) {
        for (int j = 0; j < 8; j++) {
            ChessPiece chessPiece = board.get(Position.of(i, j));
            row.append(chessPieceFunctionMap.get(chessPiece.getClass()).apply(chessPiece));
        }
    }
}

