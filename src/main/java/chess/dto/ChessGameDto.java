package chess.dto;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ChessGameDto {
    private final String name;


    private final ChessGame chessGame;

    public ChessGameDto(String name, ChessGame chessGame) {
        this.name = name;
        this.chessGame = chessGame;
    }

    public ChessGameDto(ChessGame chessGame) {
        this.name = "";
        this.chessGame = chessGame;
    }

    public String getName() {
        return name;
    }

    public List<PieceDto> getBoardWeb() {
        List<PieceDto> result = new ArrayList<>();
        for (Rank rank : Rank.reverseRanks()) {
            addPiecesDtoRank(result, chessGame.getBoard().getSquares(), rank);
        }
        return result;
    }

    public Set<Entry<String, String>> getSquaresOfDB() {
        Map<String, String> squaresDB = new HashMap<>();
        chessGame.getBoard().getSquares().forEach((position, piece) ->
                squaresDB.put(position.toString(),
                        piece.getName().getValue(piece.getTeam()))
        );
        return squaresDB.entrySet();
    }

    private void addPiecesDtoRank(List<PieceDto> result, Map<Position, Piece> squares, Rank rank) {
        for (Column column : Column.values()) {
            Piece piece = squares.get(new Position(column, rank));
            result.add(new PieceDto(piece));
        }
    }

    public Map<Position, Piece> getSquares() {
        return chessGame.getBoard().getSquares();
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    public String getTurn() {
        return chessGame.getTurn().name();
    }

    public String findPiece(String position) {
        Piece piece = getSquares().get(Position.from(position));
        return piece.getName().getValue(piece.getTeam());
    }
}
