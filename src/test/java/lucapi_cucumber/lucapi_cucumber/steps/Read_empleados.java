package lucapi_cucumber.lucapi_cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Read_empleados {

	private static final String BASE_URL = "https://prueba-fake-api-2.herokuapp.com";
	private static Response response;
	private static String jsonString;

	// ESCENARIO: CARGO LA LISTA COOMPLETA DE EMPLEADOS

	@Given("Usuario que selecciona get a la api empleados")
	public void usuario_que_selecciona_get_a_la_api_empleados() {
		// aquí se podría meter el usuario y token de la web si lo hubiera
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
		assertThat(empleados.size()).isGreaterThan(0);
	}

	// ESCENARIO: FALLO AL CARGAR LA LISTA COMPLETA DE EMPLEADOS PORQUE LA URL ESTA
	// MAL

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

	// ESCENARIO 1(GET 1 EMPLEADO)

	@Given("Usuario que selecciona GET a un empleado en la api")
	public void usuario_que_selecciona_get_a_un_empleado_en_la_api() {
		RestAssured.baseURI = BASE_URL;
	}

	@When("Solicita la peticion GET a empleados\\/uno")
	public void solicita_la_peticion_get_a_empleados_uno() {
		RequestSpecification request = RestAssured.given();
		response = request.get("/empleados/1");
	}

	@Then("Recibo un HTTP rescode doscientos")
	public void recibo_un_http_rescode_doscientos() {
		assertThat(response.getStatusCode()).isEqualTo(200);
	}

	@Then("Devuelve un json con un empleado")
	public void devuelve_un_json_con_un_empleado() {
		jsonString = response.asString();
		String nombres = JsonPath.from(jsonString).get("nombre").toString();
		List<String> empleados = new ArrayList<String>(Arrays.asList(nombres));
		assertThat(empleados.size()).isEqualTo(1);
	}

	// ESCENARIO 2 (GET 1 EMPLEADO URL MAL)

	@Given("Usuario que selecciona GET a un empleado en la api url mal")
	public void usuario_que_selecciona_get_a_un_empleado_en_la_api_url_mal() {
		RestAssured.baseURI = BASE_URL;
	}

	@When("Solicita la peticion GET a empleado\\/uno")
	public void solicita_la_peticion_get_a_empleado_uno() {
		RequestSpecification request = RestAssured.given();
		response = request.get("/empleado/1");
	}

	@Then("Recibo un HTTP rescode cuatrocerocuatro")
	public void recibo_un_http_rescode_cuatrocerocuatro() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}

	@Then("Devuelve un json vacio")
	public void devuelve_un_json_vacio() {
		jsonString = response.asString();
		String nombre = JsonPath.from(jsonString).get("nombre");
		assertThat(nombre).isEqualTo(null);
	}
	// ESCENARIO 3

	@Given("Usuario que selecciona GET a un empleado no existente")
	public void usuario_que_selecciona_get_a_un_empleado_no_existente() {
		RestAssured.baseURI = BASE_URL;
	}
	@When("Solicita la peticion GET a empleados\\/cero")
	public void solicita_la_peticion_get_a_empleados_cero() {
		RequestSpecification request = RestAssured.given();
		response = request.get("/empleados/0");
	}
	@Then("Recibo un HTTP respcode cuatrocerocuatro")
	public void recibo_un_http_respcode_cuatrocerocuatro() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("Devuelve un json muy vacio")
	public void devuelve_un_json_muy_vacio() {
		jsonString = response.asString();
		String nombre = JsonPath.from(jsonString).get("nombre");
		assertThat(nombre).isEqualTo(null);
	}

}
