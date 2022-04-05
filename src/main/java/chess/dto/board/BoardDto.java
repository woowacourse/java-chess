package chess.dto.board;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import chess.domain.state.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {
    private static final int BOARD_SIZE = 8;
    private static final String BOARD_BLANK_SYMBOL = ".";

    private List<List<PieceDto>> cells;

    private BoardDto(final List<List<PieceDto>> board) {
        this.cells = board;
    }

    public static BoardDto of(final State state) {
        final Board board = state.board();
        final Map<Position, Piece> cells = board.cells();
        final List<List<PieceDto>> newBoard = mapBoardToListBoard(cells);

        return new BoardDto(newBoard);
    }

    private static List<List<PieceDto>> mapBoardToListBoard(final Map<Position, Piece> cells) {
        List<List<PieceDto>> newBoard = new ArrayList<>();

        for (int i = BOARD_SIZE; i >= 1; i--) {
            newBoard.add(getRank(Rank.from(i), cells));
        }

        return newBoard;
    }

    private static List<PieceDto> getRank(final Rank rank, final Map<Position, Piece> cells) {
        List<PieceDto> newRank = new ArrayList<>();
        for (int j = 1; j <= BOARD_SIZE; j++) {
            File file = File.from((j));
            Position position = new Position(file, rank);
            String symbol = getBoardSymbol(position, cells);
            newRank.add(PieceDto.of(symbol, file, rank, background(position)));
        }

        return newRank;
    }

    private static String getBoardSymbol(final Position position, final Map<Position, Piece> cells) {
        if (cells.containsKey(position)) {
            Piece piece = cells.get(position);

            return piece.symbol();
        }

        return BOARD_BLANK_SYMBOL;
    }

    private static String background(Position position) {
        final File file = position.getFile();
        final Rank rank = position.getRank();

        if ((file.getNumber() + rank.getNumber()) % 2 == 0) {
            return "black";
        }

        return "white";
    }

    public List<List<PieceDto>> getCells() {
        return cells;
    }
}
