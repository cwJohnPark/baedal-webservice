package me.cwpark.baedal.delivery;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import me.cwpark.baedal.delivery.domain.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

	@Query("SELECT d FROM Delivery d "
		+ " JOIN d.member m "
		+ " WHERE m.email = :email "
		+ " AND d.createdAt >= :orderedAfter "
		+ " ORDER BY d.createdAt DESC "
		+ "")
	List<Delivery> findAllByMemberEmail(String email, LocalDateTime orderedAfter);
}
