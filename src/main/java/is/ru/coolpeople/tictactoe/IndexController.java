package is.ru.coolpeople.tictactoe;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by olafur on 22/10/2017.
 */

@RestController
public class IndexController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = "/")
	public String index() {
		return "Hello world";
	}


	@RequestMapping(value = ERROR_PATH)
	public String error() {
		return "Error handling";
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}