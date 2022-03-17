package lucapi_cucumber.lucapi_cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;

public class crud_empleados {
	
	private static final String BASE_URL = "https://prueba-fake-api-2.herokuapp.com";
	private static Response response;
	private static String jsonString;
	
	//ESCENARIO: CARGO LA LISTA COOMPLETA DE EMPLEADOS
	
	@Given("Usuario que selecciona get a la api empleados")
	public void usuario_que_selecciona_get_a_la_api_empleados() {
		//aquí se podría meter el usuario y token de la web si lo hubiera
		RestAssured.baseURI = BASE_URL;
	}
	@When("Solicita la peticion get a empleados")
	public void solicita_la_peticion_get_a_empleados() {
		RequestSpecification request = RestAssured.given();
		response = request.get("/empleados");
	}
	@Then("Recibo un HTTP response code doscientos")
	public void recibo_un_http_response_code_doscientos() {
		assertThat(response.getStatusCode()).isEqualTo(200);
		
	}
	@Then("Devuelve un json con diez empleados")
	public void devuelve_un_json_con_diez_empleados() {
		jsonString = response.asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		assertThat(empleados.size()).isEqualTo(10);
	}
	
	//ESCENARIO: FALLO AL CARGAR LA LISTA COMPLETA DE EMPLEADOS PORQUE LA URL ESTA MAL
	
		@Given("Usuario que selecciona GET a la api\\/empleado")
		public void usuario_que_selecciona_get_a_la_api_empleado() {
			RestAssured.baseURI = BASE_URL;
		}
		@When("Solicita la peticion get a empleado sin ese final")
		public void solicita_la_peticion_get_a_empleado_sin_ese_final() {
			RequestSpecification request = RestAssured.given();
			response = request.get("/empleado");
		}
		@Then("Recibo un HTTP response code cuatrocerocuatro")
		public void recibo_un_http_response_code_cuatrocerocuatro() {
			assertThat(response.getStatusCode()).isEqualTo(404);
		}
		@Then("Devuelve un json con una bad request")
		public void devuelve_un_json_con_una_bad_request() {
			jsonString = response.asString();
			String empleados = JsonPath.from(jsonString).get("empleados");
			assertThat(empleados).isEqualTo(null);
		}
	
}
