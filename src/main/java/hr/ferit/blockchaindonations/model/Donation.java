package hr.ferit.blockchaindonations.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "donation")
@NoArgsConstructor
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User owner;
    @OneToOne
    @JoinColumn(name = "blockchain_id")
    public Blockchain blockchain;
    public Donation(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }
}
