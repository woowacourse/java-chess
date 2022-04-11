package chess.dao;

import chess.model.board.ChessInitializer;
import chess.model.board.Square;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import chess.service.dto.BoardDto;
import chess.service.dto.PieceWithSquareDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryBoardDao implements BoardDao {
    private final Map<Integer, BoardDto> boardTable = new HashMap<>();

    @Override
    public void initBoard(int gameId) {
        Map<Square, Piece> board = new ChessInitializer().initPieces();
        List<PieceWithSquareDto> pieces = board.keySet().stream()
                .map(square -> new PieceWithSquareDto(square.getName(), PieceType.getName(board.get(square)),
                        board.get(square).getColor().name()))
                .collect(Collectors.toList());
        boardTable.put(gameId, new BoardDto(pieces));
    }

    @Override
    public BoardDto getBoardByGameId(int gameId) {
        return boardTable.get(gameId);
    }

    @Override
    public void remove(int gameId) {
        boardTable.remove(gameId);
    }

    @Override
    public void update(PieceWithSquareDto replacePiece, int gameId) {
        BoardDto boardDto = boardTable.get(gameId);
        for (int i = 0; i < boardDto.getPieces().size(); i++) {
            replaceIfSquareEquals(replacePiece, boardDto, i);
        }
    }

    private void replaceIfSquareEquals(PieceWithSquareDto replacePiece, BoardDto boardDto, int i) {
        if (replacePiece.getSquare().equals(boardDto.getPieces().get(i).getSquare())) {
            boardDto.getPieces().set(i, replacePiece);
        }
    }

    public Map<Integer, BoardDto> getBoardTable() {
        return boardTable;
    }
}
