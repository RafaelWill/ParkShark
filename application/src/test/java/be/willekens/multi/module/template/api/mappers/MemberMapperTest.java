package be.willekens.multi.module.template.api.mappers;

import be.willekens.multi.module.template.api.dtos.ReceiveMemberDto;
import be.willekens.multi.module.template.domain.models.account.Account;
import be.willekens.multi.module.template.domain.models.account.Role;
import be.willekens.multi.module.template.domain.models.address.Address;
import be.willekens.multi.module.template.domain.models.address.PostalCode;
import be.willekens.multi.module.template.domain.models.member.LicencePlate;
import be.willekens.multi.module.template.domain.models.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class MemberMapperTest {

    @Mock
    private AddressMapper addressMapper;
    @InjectMocks
    private MemberMapper memberMapper;

    @Test
    void name() {
        Member member = createMemberFactory();

        ReceiveMemberDto receiveMemberDto = memberMapper.member_to_receiveMemberDto(member);

        Assertions.assertThat(receiveMemberDto.getFirstName()).isEqualTo(member.getFirstName());
    }

    private Member createMemberFactory() {
        Member member = new Member();
        member.setFirstName("Dumb");
        member.setLastName("Dummy");
        member.setPhoneNumber("0002");
        member.setAddress(createAddressFactory());
        member.setLicencePlate(createLicencePlate());
        member.setRegistrationDate(LocalDate.now());
        member.setAccount(new Account("test@test.be","", Role.MEMBER));
        return member;
    }

    private LicencePlate createLicencePlate() {
         return new LicencePlate("1-GHK-800","BE");
    }

    private Address createAddressFactory(){
        Address address = new Address();
        address.setStreetName("Baker Street");
        address.setStreetNumber("221B");
        address.setPostalCode(new PostalCode("1000","London"));
        return address;
    }
}