package dao;

import dto.PieceDto;

import java.util.List;

public interface PieceDao {
    void save(List<PieceDto> pieceDtos);

    List<PieceDto> find();

    void update(List<PieceDto> pieceDtos);

    void delete();
}
