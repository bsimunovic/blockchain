package hr.ferit.blockchaindonations.repository;

import hr.ferit.blockchaindonations.model.Blockchain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockchainRepository extends JpaRepository<Blockchain, Long> {
}
