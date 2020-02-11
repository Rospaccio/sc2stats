package xyz.codevomit.sc2stats.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_record")
@Getter
@Setter
public class GameRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "game_date_time")
    private LocalDateTime gameDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player principal;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player opponent;

    @Enumerated(EnumType.STRING)
    @Column(name = "played_race")
    private StarcraftRace playedRace;

    @Enumerated(EnumType.STRING)
    @Column(name = "opponent_race")
    private StarcraftRace opponentRace;

    @Enumerated(EnumType.STRING)
    @Column(name = "outcome")
    private GameOutcome outcome;
}
