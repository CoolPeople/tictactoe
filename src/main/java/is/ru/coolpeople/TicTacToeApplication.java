package is.ru.coolpeople;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;

@SpringBootApplication
public class TicTacToeApplication {

	public static void main(String[] args) {
		HashMap<String, Object> props = new HashMap<>();
		props.put("server.port", readPortOrDefault());

		new SpringApplicationBuilder()
				.sources(TicTacToeApplication.class)
				.properties(props)
				.run(args);
	}

	static int readPortOrDefault() {
		ProcessBuilder psb = new ProcessBuilder();
		if (psb.environment().get("PORT") != null) {
			return Integer.parseInt(psb.environment().get("PORT"));
		}
		return 4567;
	}
}
