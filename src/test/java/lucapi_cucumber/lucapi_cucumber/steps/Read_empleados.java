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
	private static RequestSpecification request;

	// ESCENARIO: CARGO LA LISTA COOMPLETA DE EMPLEADOS

	@Given("Usuario que selecciona get a la api empleados")
	public void usuario_que_selecciona_get_a_la_api_empleados() {
		// aquí se podría meter el usuario y token de la web si lo hubiera
		RestAssured.baseURI = BASE_URL;
	}

	@When("Solicita la peticion get a empleados")
	public void solicita_la_peticion_get_a_empleados() {
		request = RestAssured.given();
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
		request = RestAssured.given();
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
		request = RestAssured.given();
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
		request = RestAssured.given();
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
		request = RestAssured.given();
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
	
	// Escenario 1 de peticion get con filtro(nombre)
	
	@Given("Usuario que selecciona get a la api con nombre como param")
	public void usuario_que_selecciona_get_a_la_api_con_nombre_como_param() {
		RestAssured.baseURI = BASE_URL;
	}
	@When("Solicita la peticion get a empleados\\/nombre")
	public void solicita_la_peticion_get_a_empleados_nombre() {
		request = RestAssured.given();
		response = request.get("/empleados?nombre=Adrian");
	}
	@Then("Recibo un HTTP status code de Doscientos")
	public void recibo_un_http_status_code_de_doscientos() {
		assertThat(response.getStatusCode()).isEqualTo(200);
	}
	@Then("Devuelve un json con los datos del empleado")
	public void devuelve_un_json_con_los_datos_del_empleado() {
		jsonString = response.asString();
		assertThat(jsonString.contains("Adrian")).isEqualTo(true);
	}
	
	// Escenario 2 get by nombre
	
	@Given("Usuario que selecciona get a la api con nombre y url mal")
	public void usuario_que_selecciona_get_a_la_api_con_nombre_y_url_mal() {
		RestAssured.baseURI = BASE_URL;
	}
	@When("Solicita la peticion get a empleado\\/nombre")
	public void solicita_la_peticion_get_a_empleado_nombre() {
		request = RestAssured.given();
		response = request.get("/empleado?nombre=Adrian");
	}
	@Then("Recibo un HTTP statusCode de cuatro cero cuatro")
	public void recibo_un_http_status_code_de_cuatro_cero_cuatro() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("Devuelve un json vacio porque el url esta mal")
	public void devuelve_un_json_vacio_porque_el_url_esta_mal() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("{}");
	}
	
	// Escenario 3 get by nombre
	
	@Given("Usuario que selecciona get a la api con nombre que no existe")
	public void usuario_que_selecciona_get_a_la_api_con_nombre_que_no_existe() {
		RestAssured.baseURI = BASE_URL;
	}
	@When("Solicita la peticion get a empleado\\/nombre que no existe")
	public void solicita_la_peticion_get_a_empleado_nombre_que_no_existe() {
		request = RestAssured.given();
		response = request.get("/empleados?nombre=test1");
	}
	@Then("Recibo un HTTP statusCode de cuatro cero cuatro porque el nombre no existe")
	public void recibo_un_http_status_code_de_cuatro_cero_cuatro_porque_el_nombre_no_existe() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("Devuelve un json vacio porque el empleado no existe")
	public void devuelve_un_json_vacio_porque_el_empleado_no_existe() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("[]");
	}
	
	// Escenario 1 filtro por ciudad
	
	@Given("Usuario que selecciona get a la api con ciudad")
	public void usuario_que_selecciona_get_a_la_api_con_ciudad() {
		RestAssured.baseURI = BASE_URL;
	}
	@When("Solicita la peticion get a empleados?ciudad")
	public void solicita_la_peticion_get_a_empleados_ciudad() {
		request = RestAssured.given();
		response = request.get("/empleados?ciudad=Madrid");
	}
	@Then("Recibo un HTTP statusCode de dos cero cero")
	public void recibo_un_http_status_code_de_dos_cero_cero() {
		assertThat(response.getStatusCode()).isEqualTo(200);
	}
	@Then("Devuelve un json con los empleados que viven en esta ciudad")
	public void devuelve_un_json_con_los_empleados_que_viven_en_esta_ciudad() {
		jsonString = response.asString();
		assertThat(jsonString.contains("Madrid")).isEqualTo(true);
	}
	
	// Escenario 2 filtro por ciudad
	
	@Given("Usuario que selecciona get a la api con ciudad con url mal")
	public void usuario_que_selecciona_get_a_la_api_con_ciudad_con_url_mal() {
		RestAssured.baseURI = BASE_URL;
		
	}
	@When("Solicita la peticion get a empleado?ciudad con url mal")
	public void solicita_la_peticion_get_a_empleado_ciudad_con_url_mal() {
		request = RestAssured.given();
		response = request.get("/empleado?ciudad=Madrid");
	}
	@Then("Recibo un HTTP statusCode de cuatro cero cuatro porque url esta mal")
	public void recibo_un_http_status_code_de_cuatro_cero_cuatro_porque_url_esta_mal() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("Devuelve json vacio porque el url esta mal")
	public void devuelve_json_vacio_porque_el_url_esta_mal() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("{}");
	}
	
	// Escenario 3 filtro por ciudad
	
	@Given("Usuario que selecciona get a la api con ciudad que no existe")
	public void usuario_que_selecciona_get_a_la_api_con_ciudad_que_no_existe() {
		RestAssured.baseURI = BASE_URL;
	}
	@When("Solicita la peticion get a empleado?ciudad que no existe")
	public void solicita_la_peticion_get_a_empleado_ciudad_que_no_existe() {
		request = RestAssured.given();
		response = request.get("/empleados?ciudad=Paris");
	}
	@Then("Recibo un HTTP statusCode de cuatro cero cuatro porque la ciudad no existe")
	public void recibo_un_http_status_code_de_cuatro_cero_cuatro_porque_la_ciudad_no_existe() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("Devuelve un json vacio porque la ciudad no existe")
	public void devuelve_un_json_vacio_porque_la_ciudad_no_existe() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("[]");
	}
	
	// Escenario de busqueda con atributo que no existe
	
	@Given("Usuario que selecciona get a la api con un atributo que no existe")
	public void usuario_que_selecciona_get_a_la_api_con_un_atributo_que_no_existe() {
		RestAssured.baseURI = BASE_URL;
	}
	@When("Solicita la peticion get a empleado?atributo que no existe")
	public void solicita_la_peticion_get_a_empleado_atributo_que_no_existe() {
		request = RestAssured.given();
		response = request.get("/empleados?edad=14");
	}
	@Then("Recibo un HTTP statusCode de cuatro cero cuatro porque el atributo no existe")
	public void recibo_un_http_status_code_de_cuatro_cero_cuatro_porque_el_atributo_no_existe() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("Devuelve un json vacio porque el atributo no existe")
	public void devuelve_un_json_vacio_porque_el_atributo_no_existe() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("{}");
	}
}
