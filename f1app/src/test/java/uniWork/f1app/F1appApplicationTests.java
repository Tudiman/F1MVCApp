package uniWork.f1app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uniWork.f1app.Controllers.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class F1appApplicationTests {

	@Autowired
	private HomeController homeController;
	@Autowired
	private CarController carController;
	@Autowired
	private ChampionshipController championshipController;
	@Autowired
	private ChampionshipRegistrationController championshipRegistrationController;
	@Autowired
	private DriverContractController driverContractController;
	@Autowired
	private DriverController driverController;
	@Autowired
	private RaceController raceController;
	@Autowired
	private TeamController teamController;
	@Autowired
	private TrackController trackController;

	@Test
	void contextLoads() {
		assertThat(homeController).isNotNull();
		assertThat(carController).isNotNull();
		assertThat(championshipController).isNotNull();
		assertThat(championshipRegistrationController).isNotNull();
		assertThat(driverContractController).isNotNull();
		assertThat(driverController).isNotNull();
		assertThat(raceController).isNotNull();
		assertThat(teamController).isNotNull();
		assertThat(trackController).isNotNull();
	}

}
