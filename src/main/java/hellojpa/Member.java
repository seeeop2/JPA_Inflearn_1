package hellojpa;

import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member {

/*
    @Id
    private long id;
//    @Column(name = "USERNAME") DB 컬럼명 지정할 수 있다.
    @Column(unique = true, length = 10)  //컬럼에 unique 제약조건 추가, 길이는 10자 까지
    private String name;
    private int age;
//    private int gogo2;

    //JPA는 생성자 만들 때, 기본 생성자도 만들어줘야한다.
    public Member() {
    }

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
*/

    /*기본키 매핑 강의를 위해 주석
    @Id
    private Long id;

//    @Column(name = "name")  //객체는 username이라 쓰는데, DB 컬럼에는 name을 써야할 때
    *//*
    @Column(updatable = false)   // 기본은 true임. false일시, 컬럼 변경되지 않으나, DB에서 강제로 변경하면 변경되긴 함. 비슷한 것 : insertable
    @Column(nullable = false)   //not null 기능.
    @Column(unique = true)  //잘 사용하지 않음. 제약 이름이 랜덤이라서 오류가 났을 때 알아보기 힘듦. 따라서, class 위 @Table(uniqueConstraints = 제약이름 )
    @Column(columnDefinition = "varchar(100) default 'EMPTY'")  //column 정의를 직접 할 수 있다.
    *//*
    private String username;

    private Integer age;    //int도 가능하지만, integer도 가능

    @Enumerated(EnumType.STRING)    //대부분의 DB는 Enum타입이 없다. 그래서 @Enumerated 어노테이션을 이용하면 된다.
                                    //EnumType 2가지 존재. 기본은 ORDINAL이다. 그러나 STRING이 무조건 유리하다.
                                    //ORDINAL : Enum 순서를 저장
                                    //STRING : Enum 이름을 저장
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)   //Date를 사용할 땐, @Temporal 어노테이션 사용. Type으로 DATE, TIME, TIMESTAMP 중에 지정 필요
                                        //최근에는 Date 대신 LocalDate, LocalDateTime이 도입되었음
                                        //LocalDate, LocalDateTime 사용 시, 어노테이션 불필요
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;

    private LocalDateTime testLocalDateTime;

    @Lob    //varchar OR varchar2 크기를 넘어서는 긴 길이를 저장할 때 사용
            //매핑하는 필드 타입이 문자면 CLOB, 나머지는 BLOB
    private String description;

    @Transient  //DB에 컬럼 생기지 않게 해준다
    private int temp;

*//*
    @Column(precision = 19) //BigDecimal타입(매우 큰 수)에서 활용 가능. precision-소수점을 포함한 전체 자릿, scale-소수의 자릿
                            // *float, double 에는 적용 불가
    private BigDecimal num;
*//*

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
    */
/*
    @Id //직접 할당 방식
//    @GeneratedValue(strategy = GenerationType.AUTO) //자동할당 방법1. DB방언에 맞춰서 자동으로 생성
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동할당 방법2. 기본키 생성을 DB에 위임.
                                                        //대표적으로 MySQL의 AutoIncrement
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) //자동할당 방법3. 시퀀스 오브젝트를 통해서 값을 설정
                                                        //대표적으로 Oracle의 Sequence
                                                        //타입은 Long을 권장함. int, Integer은 추천 안함

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="member_seq_generator" ) //class위 어노테이션 참고
//    @GeneratedValue(strategy = GenerationType.TABLE)//자동할당 방법4.
                                                      //별도의 테이블을 생성해서 id값 저장. 모든 DB에서 사용할 수 있다는 장점. 단점은 성능
    private Long id;
    *//*
    기본 키 제약 조건 : Not null, 유일, 변하면 안됨
    권장하는 방법 : Long 타입 + 대체키 + 키 생성전략 사용
    기본키는 주민등록번호 같은 수 말고 임의의 수 추천함
    *//*

    @Column(name = "name",nullable = false)
    private String username;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

    //단방향 연관관계 시작
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

/* 이렇게 하면 객체지향적이지 않다
    @Column(name = "TEAM_ID")
    private Long teamId;
*/
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")   //외래키가 있는 곳을 '진짜 매핑'(연관 관계의 주인)으로 설정
                                    //외래키가 있는 곳이 '다' 이며, @ManyToOne 어노테이션을 쓰는 쪽이 무조건 연관 관계의 주인이 된다
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}