package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {    //psvm으로 간편하게 main메소드 생성 가능
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();   //데이터베이스 커넥션을 하나 받았다 생각

        EntityTransaction tx = em.getTransaction(); //JPA는 트랜잭션이 꼭 필요하다.
        tx.begin(); //트랜잭션 시작

        try {   //try catch finally 사용하는 게 정석이긴 하나, 실제로는 스프링이 다 해준다.

/*insert
            Member member = new Member();

            member.setId(2L);
            member.setName("HelloB");
            em.persist(member); //데이터베이스 저장
*/
/*select
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
*/
/*delete
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
*/

/*update-> 마지막에 em.persist("객체") 해야할 것이라고 사람들이 생각하나, 그럴 필요가 없다.
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
*/
/*JPQL로 select
            //JPQL
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();
*/

/*JPQL로 페이징 처리하는 방법
            List<Member> result2 = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
*/


/*출력 부분
            for (Member member : result) {  //iter 자동완성으로 간단하게 상향된 for문 사용 가능
                System.out.println("member.getName() = " + member.getName());   //soutv 자동완성으로 간단하게 작성 가능
            }
*/

/*영속성 1차 캐시 확인을 위해 주석처리
            //비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            //영속
            System.out.println("===BEFORE===");   //persist함수를 사용했을 때, DB에 저장되는지 아닌지 타이밍을 찾기 위한 출력
            em.persist(member);
//            em.detach(member);  //영속성 컨텍스트에서 지움
            System.out.println("===AFTER===");    //persist함수를 사용했을 때, DB에 저장되는지 아닌지 타이밍을 찾기 위한 출력

            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
*/

/*1차 캐시 확인
            Member member1 = em.find(Member.class, 101L);
            Member member2 = em.find(Member.class, 101L);   //select문 한 번만 나옴. 이유는 1차 캐시 기능.

            System.out.println("result = " + (member1 == member2) );    //true-> 동일성 보장
*/

/*쿼리 한번에 모아서 DB에 보내는 것을 확인할 수 있음
            Member member1 = new Member(150L,"A");
            Member member2 = new Member(160L,"B");

            em.persist(member1);
            em.persist(member2);
            System.out.println("==============================");
*/

/*
            Member findMember = em.find(Member.class, 150L);
            findMember.setName("ZZZZZ");    //update문은 persist 메소드 호출 할 필요 없다.
                                            //영속성 컨텍스트 내에는 최초 상태의 영속성 컨텍스트를 스냅샷으로 기억해두는 기능 있다.
                                            //만약 변경이 되었으면 스냅샷과 비교를 통해 업데이트를 자동으로 진행시킴
*/

/*flush
            Member member = new Member(200L, "member200");
            em.persist(member); //영속 상태가 되며, 영속성 컨텍스트에 쿼리 축적

            em.flush(); // 일반적으로 commit함수에서 축적된 쿼리가 DB에 전달이 되나, 미리 축적된 SQL을 보내는 방법.
                        // 주의! -> flush 함수를 사용한다고 해서, 영속성 컨텍스트가 비워지는 것은 아니다.
*/

            /* 필드와 컬럼 매핑 강의를 위해 주석처리
            Member member = em.find(Member.class, 150L);    //영속성 컨텍스트에 정보가 있음
            member.setName("AAAAA");    //update 쿼리가 영속성 컨텍스트에 축적
            em.detach(member);      // 영속성 컨텍스트에서 제거하였으니, update 쿼리 실행 안되었을 것 ---> 준영속 상태라고 부름
            */
            /*준영속 상태를 만드는 방법
            1. em.detach(entity)
            특정 엔티티만 준영속 상태로 전환

            2. em.clear()
            영속성 컨텍스트를 완전히 초기화

            3. em.close()
            영속성 컨텍스트를 종료
            * */

/*
            Member member = new Member();
            member.setId(1L);
            member.setUsername("A");
            member.setRoleType(RoleType.USER);
            em.persist(member);

            Member member2 = new Member();
            member2.setId(2L);
            member2.setUsername("B");
            member2.setRoleType(RoleType.ADMIN);
            em.persist(member2);
*/
/*
            Member member = new Member();
            member.setUsername("C");
            em.persist(member);
*/


/*
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());

            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId); // Team 객체를 찾는데 너무 번거롭고, 오래 걸림
*/

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);


/* 영속성 컨텍스트에서 가져오는 것 말고 DB에서 가져오는 것 보고 싶을 경우,
flush로 모아둔 쿼리 내보내고 clear로 영속성컨텍스트 초기화
            em.flush();
            em.clear();
*/

//            System.out.println("findTeam.getName() = " + findTeam.getName());

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            List<Member> members = findTeam.getMembers();
            for (Member member1 : members) {
                System.out.println("member1.getUsername() = " + member1.getUsername());
            }


            tx.commit();    //커밋까지 완료   //DB에 저장되는 타이밍

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}