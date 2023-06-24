package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려*/
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();


    // 싱글톤일 때는 생성자를 private로 막아줘야 한다.
    private MemberRepository() {
    }
    public static MemberRepository getInstance(){
        return instance;
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        // store.values()는 Collection이라서 바로 ArrayList에 넣을 수 없다.
        // store를 보호하기 위해 새로운 ArrayList에 넣어서 반환한다.
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
