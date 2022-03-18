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
	
	// Escenario 1 delete
	
	@Given("Usuario que selecciona delete a la api")
	public void usuario_que_selecciona_delete_a_la_api() {
		RestAssured.baseURI = BASE_URL;
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		numeroEmpleados=empleados.size();
	}
	@When("Solicita la peticion delete a empleados")
	public void solicita_la_peticion_delete_a_empleados() {
		request = RestAssured.given();
		response = request.delete("empleados/999");
	}
	@Then("Recibo un HTTP response code de dos cero cero")
	public void recibo_un_http_response_code_de_dos_cero_cero() {
		assertThat(response.getStatusCode()).isEqualTo(200);
	}
	@Then("el numero se decrementa de uno")
	public void el_numero_se_decrementa_de_uno() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("{}");
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		assertThat(empleados.size()).isEqualTo(numeroEmpleados-1);
	}
	//Escenario 2 delete
	
	@Given("Usuario que selecciona delete a la api con uri mal")
	public void usuario_que_selecciona_delete_a_la_api_con_uri_mal() {
		RestAssured.baseURI = BASE_URL;
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		numeroEmpleados=empleados.size();
	}
	@When("Solicita la peticion delete a empleados con uri mal")
	public void solicita_la_peticion_delete_a_empleados_con_uri_mal() {
		request = RestAssured.given();
		response = request.delete("empleado/999");
	}
	@Then("Recibo un HTTP response code de cuatro cero cuatro por uri mal")
	public void recibo_un_http_response_code_de_cuatro_cero_cuatro_por_uri_mal() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("el numero no cambia")
	public void el_numero_no_cambia() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("{}");
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		assertThat(empleados.size()).isEqualTo(numeroEmpleados);
	}
	
	//Escenario 3 delete
	
	@Given("Usuario que selecciona delete a la api con usuario que no existe")
	public void usuario_que_selecciona_delete_a_la_api_con_usuario_que_no_existe() {
		RestAssured.baseURI = BASE_URL;
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		numeroEmpleados=empleados.size();
	}
	@When("Solicita la peticion delete a empleados con que no existe")
	public void solicita_la_peticion_delete_a_empleados_con_que_no_existe() {
		request = RestAssured.given();
		response = request.delete("empleados/999");
		
	}
	@Then("Recibo un HTTP response code de cuatro cero cuatro porque el usuario no existe")
	public void recibo_un_http_response_code_de_cuatro_cero_cuatro_porque_el_usuario_no_existe() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("el numero no se decrementa")
	public void el_numero_no_se_decrementa() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("{}");
		jsonString = RestAssured.given().get("/empleados").asString();
		List<Map<String, String>> empleados = JsonPath.from(jsonString).get("empleados");
		assertThat(empleados.size()).isEqualTo(numeroEmpleados);
	}
	
	// Escenario update 1
	
	@Given("Usuario que selecciona update a la api")
	public void usuario_que_selecciona_update_a_la_api() {
		RestAssured.baseURI = BASE_URL;
	}
	@Given("Entra los datos a modificar")
	public void entra_los_datos_a_modificar() {
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body("{\r\n"
				+ "  \"id\": \"3\",\r\n"
				+ "  \"nombre\": \"ayoubCambio\",\r\n"
				+ "  \"apellido1\": \"p1\",\r\n"
				+ "  \"apellido2\": \"p2\",\r\n"
				+ "  \"email\": \"ayoub@gmail.com\",\r\n"
				+ "  \"username\": \"prueba\",\r\n"
				+ "  \"password\": \"Prueba01\",\r\n"
				+ "  \"phone\": \"123456789\",\r\n"
				+ "  \"ciudad\": \"Madrid\"\r\n"
				+ "}");
	}
	@When("Solicita la peticion update a empleados")
	public void solicita_la_peticion_update_a_empleados() {
	    response = request.put("/empleados/3");
	}
	@Then("Recibo un HTTP rescode de dos cero cero")
	public void recibo_un_http_rescode_de_dos_cero_cero() {
		assertThat(response.getStatusCode()).isEqualTo(200);
	}
	@Then("Recibo una respuesta con el json con el nombre cambiado")
	public void Recibo_una_respuesta_con_el_json_con_el_nombre_cambiado() {
		
		assertThat(response.asString()).contains("ayoubCambio");
	}
	
	// Escenario update 2 
	
	@Given("Usuario que selecciona put a la api")
	public void usuario_que_selecciona_put_a_la_api() {
		RestAssured.baseURI = BASE_URL;
	}
	@Given("Mete en el body un json de los datos del empleado")
	public void mete_en_el_body_un_json_de_los_datos_del_empleado() {
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body("{\r\n"
				+ "  \"id\": \"3\",\r\n"
				+ "  \"nombre\": \"ayoubCambio\",\r\n"
				+ "  \"apellido1\": \"p1\",\r\n"
				+ "  \"apellido2\": \"p2\",\r\n"
				+ "  \"email\": \"ayoub@gmail.com\",\r\n"
				+ "  \"username\": \"prueba\",\r\n"
				+ "  \"password\": \"Prueba01\",\r\n"
				+ "  \"phone\": \"123456789\",\r\n"
				+ "  \"ciudad\": \"Madrid\"\r\n"
				+ "}");
	}
	@When("Solicita la peticion put a empleados\\/numero")
	public void solicita_la_peticion_put_a_empleados_numero() {
		response = request.put("/empleados/20");
	}
	@Then("Recibo un HTTP status code de cuatro cero cuatro")
	public void recibo_un_http_status_code_de_cuatro_cero_cuatro() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("Recibo una respuesta con un json vacio")
	public void recibo_una_respuesta_con_un_json_vacio() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("{}");
	}
	
	// Escenario put 3
	
	@Given("Usuario que selecciona put a la api con url mal")
	public void usuario_que_selecciona_put_a_la_api_con_url_mal() {
		RestAssured.baseURI = BASE_URL;
	}
	@Given("Mete dentro del body un json de los datos del empleado")
	public void mete_dentro_del_body_un_json_de_los_datos_del_empleado() {
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body("{\r\n"
				+ "  \"id\": \"3\",\r\n"
				+ "  \"nombre\": \"ayoubCambio\",\r\n"
				+ "  \"apellido1\": \"p1\",\r\n"
				+ "  \"apellido2\": \"p2\",\r\n"
				+ "  \"email\": \"ayoub@gmail.com\",\r\n"
				+ "  \"username\": \"prueba\",\r\n"
				+ "  \"password\": \"Prueba01\",\r\n"
				+ "  \"phone\": \"123456789\",\r\n"
				+ "  \"ciudad\": \"Madrid\"\r\n"
				+ "}");
	}
	@When("Solicita la peticion put a empleado\\/numero")
	public void solicita_la_peticion_put_a_empleado_numero() {
		response = request.put("/empleado/3");
	}
	@Then("Recibo un HTTP status code de cuatro cero cuatro porque el url esta mal")
	public void recibo_un_http_status_code_de_cuatro_cero_cuatro_porque_el_url_esta_mal() {
		assertThat(response.getStatusCode()).isEqualTo(404);
	}
	@Then("Recibo una res con un json vacio")
	public void recibo_una_res_con_un_json_vacio() {
		jsonString = response.asString();
		assertThat(jsonString).isEqualTo("{}");
	}

}
