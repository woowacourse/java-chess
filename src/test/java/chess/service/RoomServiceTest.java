package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.room.Room;
import chess.repository.RoomDao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RoomServiceTest {
    private RoomDao mockRoomDao;
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        mockRoomDao = new RoomDao() {
            private final List<Room> rooms = new ArrayList<>();
            private int index = 0;

            @Override
            public void save(final String roomName, final int userId) {
                final Room room = new Room(++index, roomName, true, userId);
                rooms.add(room);
            }

            @Override
            public List<Room> findAllByUserId(final int userId) {
                return rooms.stream()
                        .filter(room -> room.getUserId() == userId)
                        .collect(Collectors.toList());
            }

            @Override
            public Room findById(final int roomId) {
                return rooms.stream()
                        .filter(room -> room.getId() == roomId)
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public void deleteAll() {
            }
        };
        roomService = new RoomService(mockRoomDao);
    }

    @Test
    void 게임을_생성한다() {
        // given
        final String roomName = "방1";
        final int userId = 1;

        // when
        roomService.save(roomName, userId);

        // then
        final Room room = roomService.findById(1, userId);
        assertAll(
                () -> assertThat(room.getId()).isEqualTo(1),
                () -> assertThat(room.getName()).isEqualTo("방1")
        );
    }

    @Test
    void 입력한_사용자_아이디에_대한_모든_게임을_조회한다() {
        // given
        final int userId = 1;
        roomService.save("방1", userId);
        roomService.save("방2", userId);

        // when
        final List<Room> result = roomService.findAllByUserId(userId);

        // then
        assertThat(result).hasSize(2);
    }

    @Test
    void 단일_조회시_방이_존재하지_않는_경우_예외를_던진다() {
        // given
        final int userId = 1;

        // expect
        assertThatThrownBy(() -> roomService.findById(1, userId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아이디에 해당하는 방이 없습니다.");
    }

    @Test
    void 단일_조회시_방을_생성한_사람이_아닌_다른사람의_아이디를_입력하는_경우_예외를_던진다() {
        // given
        final int userId = 1;
        roomService.save("방1", userId);

        // expect
        assertThatThrownBy(() -> roomService.findById(1, 9999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("방의 주인이 아닙니다.");
    }

    @Test
    void 방_번호와_사용자_아이디를_입력받아_게임을_반환한다() {
        // given
        final int userId = 1;
        roomService.save("방1", userId);

        // when
        final Room room = roomService.findById(1, userId);

        // then
        assertAll(
                () -> assertThat(room.getId()).isEqualTo(1),
                () -> assertThat(room.getName()).isEqualTo("방1")
        );
    }
}
