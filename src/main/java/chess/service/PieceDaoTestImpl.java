package chess.service;

import chess.dao.PieceDao;
import chess.dto.PieceDto;
import java.util.ArrayList;
import java.util.List;

public class PieceDaoTestImpl implements PieceDao {

    private List<PieceDto> pieces = new ArrayList<>();
    private int boardId;


    @Override
    public void create(List<PieceDto> pieces, int boardId) {
        this.pieces = pieces;
        this.boardId = boardId;
    }

    @Override
    public void updatePosition(String from, String to) {
        PieceDto before = findByPosition(from);
        PieceDto after = new PieceDto(before.getTeam(), before.getType(), to);

        int index = pieces.indexOf(before);
        pieces.remove(index);
        pieces.add(index, after);
    }

    @Override
    public List<PieceDto> findByBoardId(int id) {
        if (id == boardId) {
            return pieces;
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(String position) {
        pieces.remove(findByPosition(position));
    }

    private PieceDto findByPosition(String position) {
        return pieces.stream()
                .filter(piece -> position.equals(piece.getPosition()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치의 piece가 존재하지 않습니다."));
    }
}
