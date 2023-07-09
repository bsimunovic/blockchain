package hr.ferit.blockchaindonations.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "transaction")
@NoArgsConstructor
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;
    @OneToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiver;

    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "block_id", referencedColumnName = "id")
    private Block block;

    private Timestamp timestamp;
    public Transaction(User sender, User receiver, BigDecimal amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
