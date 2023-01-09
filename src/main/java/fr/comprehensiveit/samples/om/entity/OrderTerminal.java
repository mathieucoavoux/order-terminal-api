package fr.comprehensiveit.samples.om.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_terminal")
public class OrderTerminal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long orderId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Timestamp orderTimestamp;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Terminal> terminals;

    @OneToOne
    @JoinColumn(name="customerId",nullable = false)
    private Customer customer;
}
