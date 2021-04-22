package be.willekens.multi.module.template.service;

import be.willekens.multi.module.template.domain.models.LicencePlate;
import be.willekens.multi.module.template.domain.models.parking_lot.Address;
import be.willekens.multi.module.template.domain.models.parking_lot.PostalCode;
import be.willekens.multi.module.template.domain.models.users.Account;
import be.willekens.multi.module.template.domain.models.users.Member;
import be.willekens.multi.module.template.domain.models.users.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MemberServiceSpringTest {

    @Autowired
    private MemberService memberService;


    @Test
    void createMember_whenPostalCodeAlreadyExists(){
        Member member = memberFactory();
        member.getAddress().setPostalCode(new PostalCode("1234","Buenos Aires"));
        Member dbMember = memberService.createMember(member);

        Assertions.assertThat(dbMember.getAddress().getPostalCode()).isEqualTo(member.getAddress().getPostalCode());
    }

    @Test
    void createMember_whenPostalCodeDoesNotAlreadyExistInDB(){
        Member member = memberFactory();
        member.getAddress().setPostalCode(new PostalCode("xxxx","Test town"));
        Member dbMember = memberService.createMember(member);

        Assertions.assertThat(dbMember.getAddress().getPostalCode()).isEqualTo(member.getAddress().getPostalCode());
    }

    @Test
    void createMember_whenPostalCodeAlreadyExistsInDBButCityNameIsDifferent_thenUsesCityInDatabase(){
        Member member = memberFactory();
        member.getAddress().setPostalCode(new PostalCode("1234","Test town"));
        Member dbMember = memberService.createMember(member);

        Assertions.assertThat(dbMember.getAddress().getPostalCode().getPostalCode()).isEqualTo(member.getAddress().getPostalCode().getPostalCode());
        Assertions.assertThat(dbMember.getAddress().getPostalCode().getLabel()).isEqualTo("Buenos Aires");
    }

    private Member memberFactory(){
        return new Member()
                .setAccount(new Account("test@test.test","testtest", Role.MEMBER))
                .setFirstName("John")
                .setLastName("Doe")
                .setLicencePlate(new LicencePlate("xxx555","BE"))
                .setPhoneNumber("011223344")
                .setRegistrationDate(LocalDate.now())
                .setAddress(new Address()
                    .setStreetName("testystreet")
                    .setStreetNumber("test")
                );
    }


}