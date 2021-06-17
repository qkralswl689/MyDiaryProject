package com.goodee.diary.demo.user.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Table(name = "diaryuser")
@Data
@SequenceGenerator(
        name="DIARYUSER_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="diaryuser_seq", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DIARYUSER_SEQ_GEN"
    )
    private int userid;

    @NotNull(message = "이메일은 필수입니다. ")
    @NotEmpty(message = "이메일 작성해주세여")
    private String useremail;

    @NotNull(message = "패스워드 필수!.")
    @NotEmpty(message = "패스워드 입력해라..")
    private String userpw;

    @NotNull(message = "이름은 필수 사항입니다.")
    @NotEmpty(message = "이름을 작성하십시오.")
    private String username;

    @NotNull(message = "생년월일을 입력하십시오.")
    @Past(message = "생일은 금일 기준 이전 일이 들어가야 합니다")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date userbirth;

    private String userimage;

    public User() {
    }

    public User(String useremail, String userpw, String username, Date userbirth) {
        this.useremail = useremail;
        this.userpw = userpw;
        this.username = username;
        this.userbirth = userbirth;
    }
    public User(String useremail, String userpw, String username, Date userbirth, String userimage) {
        this.useremail = useremail;
        this.userpw = userpw;
        this.username = username;
        this.userbirth = userbirth;
        this.userimage = userimage;
    }
}
