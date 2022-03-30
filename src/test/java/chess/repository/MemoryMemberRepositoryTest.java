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

    @Test
    @DisplayName("멤버를 저장소에 저장한다.")
    void save() {
        MemoryMemberRepository repository = new MemoryMemberRepository();
        Member member = new Member("alex");
        repository.save(member);
        assertThat(repository.findById(1L).get().getName()).isEqualTo(member.getName());
    }

    @Test
    @DisplayName("저장소에 저장된 모든 멤버를 불러온다.")
    void findAll() {
        MemoryMemberRepository repository = new MemoryMemberRepository();
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