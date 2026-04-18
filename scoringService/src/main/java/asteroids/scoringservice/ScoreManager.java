package asteroids.scoringservice;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/score")

public class ScoreManager {


    /* testing for GET
    @GetMapping
    public int testScore() {
        return 123;
    }
     */

    @PostMapping
    public int calculateScore(@RequestBody GameScore result) {
        return result.getKills() * 100;
    }
}
