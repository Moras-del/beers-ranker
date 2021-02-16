package pl.moras.beersapi;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import pl.moras.beersapi.controllers.BeerController;
import pl.moras.beersapi.models.Style;
import pl.moras.beersapi.models.dtos.BeerDto;
import pl.moras.beersapi.models.dtos.StyleDto;
import pl.moras.beersapi.services.BeerService;
import pl.moras.beersapi.services.ProducerService;
import pl.moras.beersapi.services.StyleService;

@SpringBootApplication
@AllArgsConstructor
@EnableDiscoveryClient
@EnableFeignClients
public class BeersapiApplication {

	private StyleService styleService;
	private ProducerService producerService;
	private BeerService beerService;

	public static void main(String[] args) {
		SpringApplication.run(BeersapiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			Style style = styleService.save(new StyleDto("lager"));
			producerService.save("gosciszewo");
			beerService.addBeer(new BeerDto("bock", style.getId(), "gosciszewo", 10), 1);
			beerService.addBeer(new BeerDto("porter", style.getId(), "gosciszewo", 8), 1);
			beerService.addBeer(new BeerDto("szeryf", style.getId(), "gosciszewo", 5), 1);
		};
	}

}
