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

public class Crud_empleados {
	
	private static final String BASE_URL = "https://prueba-fake-api-2.herokuapp.com";
	private static Response response;
	private static String jsonString;
	private static RequestSpecification request;
	private static int numeroEmpleados;
	
	//ESCENARIO 1 POST
	
	@Given("Usuario que selecciona POST a la api")
	public void usuario_que_selecciona_post_a_la_api() {
		RestAssured.baseURI = BASE_URL;
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		numeroEmpleados=empleados.size();
	}
	@Given("Mete en el body un JSON con los datos del empleado")
	public void mete_en_el_body_un_json_con_los_datos_del_empleado() {
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body("{\r\n"
				+ "  \"id\": \"999\",\r\n"
				+ "  \"nombre\": \"prueba\",\r\n"
				+ "  \"apellido1\": \"p1\",\r\n"
				+ "  \"apellido2\": \"p2\",\r\n"
				+ "  \"email\": \"prueba@gmail.com\",\r\n"
				+ "  \"username\": \"prueba\",\r\n"
				+ "  \"password\": \"Prueba01\",\r\n"
				+ "  \"phone\": \"123456789\",\r\n"
				+ "  \"ciudad\": \"Madrid\"\r\n"
				+ "}");
	}
	@When("Solicita la peticion POST a empleados")
	public void solicita_la_peticion_post_a_empleados() {
		response = request.post("/empleados");
	}
	@Then("Recibo un HTTP response code de doscientos")
	public void recibo_un_http_response_code_de_doscientos() {
		assertThat(response.getStatusCode()).isEqualTo(201);
	}
	@Then("El numero de empleados se ha modificado en mas uno")
	public void el_numero_de_empleados_se_ha_modificado_en_mas_uno() {
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		assertThat(empleados.size()).isEqualTo(numeroEmpleados+1);
	}
	
	//ESCENARIO 2 POST DUPLICADO
	
	@Given("Usuario que selecciona POST a la api con datos duplicados")
	public void usuario_que_selecciona_post_a_la_api_con_datos_duplicados() {
		RestAssured.baseURI = BASE_URL;
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		numeroEmpleados=empleados.size();
	}
	@Given("Mete en el body un JSON con los datos del empleado duplicado")
	public void mete_en_el_body_un_json_con_los_datos_del_empleado_duplicado() {
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body("{\r\n"
				+ "  \"id\": \"999\",\r\n"
				+ "  \"nombre\": \"prueba\",\r\n"
				+ "  \"apellido1\": \"p1\",\r\n"
				+ "  \"apellido2\": \"p2\",\r\n"
				+ "  \"email\": \"prueba@gmail.com\",\r\n"
				+ "  \"username\": \"prueba\",\r\n"
				+ "  \"password\": \"Prueba01\",\r\n"
				+ "  \"phone\": \"123456789\",\r\n"
				+ "  \"ciudad\": \"Madrid\"\r\n"
				+ "}");
	}
	@When("Solicita la peticion POST a empleados duplicado")
	public void solicita_la_peticion_post_a_empleados_duplicado() {
		response = request.post("/empleados");
	}
	@Then("Recibo un HTTP response code de cuatrocerocuatro")
	public void recibo_un_http_response_code_de_cuatrocerocuatro() {
		assertThat(response.getStatusCode()).isEqualTo(500);
	}
	@Then("Recibe un json vacio")
	public void recibe_un_json_vacio() {
		jsonString = response.asString();
		assertThat(jsonString).contains("Error: Insert failed, duplicate id");
	}
	
	//ESCENARIO 3 POST URL MAL
	
	@Given("Usuario que selecciona POST a la api con url mal")
	public void usuario_que_selecciona_post_a_la_api_con_url_mal() {
		RestAssured.baseURI = BASE_URL;
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		numeroEmpleados=empleados.size();
	}
	@Given("Mete en el body un JSON con los datos del empleado x")
	public void mete_en_el_body_un_json_con_los_datos_del_empleado_x() {
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body("{\r\n"
				+ "  \"id\": \"9999\",\r\n"
				+ "  \"nombre\": \"prueba\",\r\n"
				+ "  \"apellido1\": \"p1\",\r\n"
				+ "  \"apellido2\": \"p2\",\r\n"
				+ "  \"email\": \"prueba@gmail.com\",\r\n"
				+ "  \"username\": \"prueba\",\r\n"
				+ "  \"password\": \"Prueba01\",\r\n"
				+ "  \"phone\": \"123456789\",\r\n"
				+ "  \"ciudad\": \"Madrid\"\r\n"
				+ "}");
	}
	@When("Solicita la peticion POST a empleado")
	public void solicita_la_peticion_post_a_empleado() {
		response = request.post("/empleado");
	}
	@Then("Recibo un HTTP response code de cuatrocerocuatro por url mal")
	public void recibo_un_http_response_code_de_cuatrocerocuatro_por_url_mal() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("Recibe json vacio")
	public void recibe_json_vacio() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("{}");
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		assertThat(empleados.size()).isEqualTo(numeroEmpleados);
	}

}
