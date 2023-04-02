package com.example.datajpaa.repository;

import com.example.datajpaa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    List<Member> findListByUsername(String username); //반환 타입 컬렉션
    Member findMemberByUsername(String username); //반환 타입 단건
    Optional<Member> findOptionalByUsername(String username); //반환 타입 단건 Optional

    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    //@EntityGraph join fetch 편하게 쓰기
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    //override 하지 않고 쿼리에서 join fetch를 쓰고 싶을때.
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    // 메서드 이름으로 join fetch 하고 싶을때
    @EntityGraph(attributePaths = {"team"})
//    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

//    List<UsernameOnly> findProjectionsByUsername(String username);
//    List<UsernameOnly> findProjectionsByAge(int age);

//    List<UsernameOnlyDto> findProjectionsByUsername(String username);
    <T> List<T> findProjectionsByUsername(String username, Class<T> type);

    @Query(value = "select * from Member where username =?", nativeQuery = true)
    Member findByNativeQuery(String username);

    @Query(value = "select m.member_id as id, m.username, t.name as teamName " +
            "from Member m left join team t",
            countQuery = "select count(*) from Member",
            nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);

}
