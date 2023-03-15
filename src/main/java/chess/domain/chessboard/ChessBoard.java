package chess.domain.chessboard;

import chess.domain.chessboard.state.Team;
import chess.domain.chessboard.state.piece.Bishop;
import chess.domain.chessboard.state.piece.King;
import chess.domain.chessboard.state.piece.Knight;
import chess.domain.chessboard.state.piece.Pawn;
import chess.domain.chessboard.state.piece.Queen;
import chess.domain.chessboard.state.piece.Rook;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {

    private final Map<Coordinate, Square> squares = new LinkedHashMap<>();

    public ChessBoard() {
        initSquares();
    }

    private void initSquares() {

        createSideRankByTeam('1', Team.WHITE);
        createPawnRankByTeam('2', Team.WHITE);
        createBlankRanks();
        createPawnRankByTeam('7', Team.BLACK);
        createSideRankByTeam('8', Team.BLACK);
    }

    private void createSideRankByTeam(final char rank, final Team team) {
        final List<Square> squares = createSideSquaresByTeam(team);

        char file = 'a';
        for (Square square : squares) {
            this.squares.put(Coordinate.of(""+(file)+rank), square);
            file++;
        }
    }

    private List<Square> createSideSquaresByTeam(final Team team) {
        return List.of(
                new Square(new Rook(team)),
                new Square(new Knight(team)),
                new Square(new Bishop(team)),
                new Square(new Queen(team)),
                new Square(new King(team)),
                new Square(new Bishop(team)),
                new Square(new Knight(team)),
                new Square(new Rook(team)));
    }

    private void createPawnRankByTeam(final char rank, final Team team) {
        for (char file = 'a'; file <= 'h'; file++) {
            squares.put(Coordinate.of(""+file+rank), createPawnSquareByTeam(team));
        }
    }

    private Square createPawnSquareByTeam(final Team team) {
        return new Square(new Pawn(team));
    }

    private void createBlankRanks() {
        for (char file = 'a'; file <= 'h'; file++) {
            createBlankRank(file);
        }
    }

    private void createBlankRank(final char file) {
        for(char rank = '3'; rank<='6'; rank++){
            squares.put(Coordinate.of(""+file +rank), new Square());
        }
    }

    public List<Square> getSquares() {
        return this.squares.keySet()
                .stream()
                .map(squares::get).collect(Collectors.toUnmodifiableList());
    }
}
