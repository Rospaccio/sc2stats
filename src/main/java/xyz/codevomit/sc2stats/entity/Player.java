package xyz.codevomit.sc2stats.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column
    private String nickname;

    @OneToMany(mappedBy = "opponent")
    private List<GameRecord> gameRecordsAsOpponent;

    @OneToMany(mappedBy = "principal")
    private List<GameRecord> gameRecordsAsPrincipal;

    @Column(name = "username")
    private String username;

}
