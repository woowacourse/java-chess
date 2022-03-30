package chess.repository;

import static org.assertj.core.api.Assertions.*;

import chess.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("멤버를 저장소에 저장한다.")
    void save() {
        Member member = new Member("alex");
        repository.save(member);
        assertThat(repository.findById(1L).get()).isEqualTo(member);
    }

    @Test
    @DisplayName("저장소에 저장된 모든 멤버를 불러온다.")
    void findAll() {
        List<String> memberNames = new ArrayList<>();
        memberNames.add("alex");
        memberNames.add("eve");
        memberNames.add("alien");
        memberNames.add("baekara");
        memberNames.add("sun");
        memberNames.add("corin");
        for (String memberName : memberNames) {
            repository.save(new Member(memberName));
        }
        List<String> stored = repository.findAll()
                .stream()
                .map(Member::getName)
                .collect(Collectors.toList());
        assertThat(stored).isEqualTo(memberNames);
    }
}