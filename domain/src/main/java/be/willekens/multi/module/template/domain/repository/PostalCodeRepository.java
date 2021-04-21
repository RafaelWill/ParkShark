package be.willekens.multi.module.template.domain.repository;

import be.willekens.multi.module.template.domain.models.parking_lot.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostalCodeRepository extends JpaRepository<PostalCode, Integer> {


    PostalCode findByPostalCode(String postalCode);
}
